/**
 * 
 */
package Scheduler;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import Constants.Direction;
import Elevator.ElevatorInfo;
import Elevator.ElevatorJob;
import Floor.RequestElevatorEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Dharsh
 *
 */
public class ProcessJobRequestsThread extends Thread {
	private Scheduler scheduler;
	private List<RequestElevatorEvent> jobRequestQueue;
	private List<ElevatorJob> readyJobQueue;

	/**
	 * 
	 */
	public ProcessJobRequestsThread(Scheduler scheduler) {
		super();
		this.scheduler = scheduler;
		this.jobRequestQueue = Collections.synchronizedList(new LinkedList<RequestElevatorEvent>());
		this.readyJobQueue = Collections.synchronizedList(new LinkedList<ElevatorJob>());
	}
	
	public final void enqueueJobRequest(RequestElevatorEvent request) {
		synchronized (jobRequestQueue) {
			jobRequestQueue.add(request);
			jobRequestQueue.notify();
		}
	}
	
	public final void addJobQueue(ElevatorJob job) {
		synchronized (readyJobQueue) {
			readyJobQueue.add(job);
			readyJobQueue.notify();
		}
	}
	
	public ElevatorJob dequeueReadyJob() {
		synchronized (readyJobQueue) {
			while (readyJobQueue.isEmpty()) {
				try {
					readyJobQueue.wait();
	            } catch (InterruptedException e)  {
	                Thread.currentThread().interrupt(); 
	            }
			}
			ElevatorJob nextJob = readyJobQueue.remove(0);
			readyJobQueue.notify();
			return nextJob;
		}
	}
	
	public boolean hasJob() {
		synchronized (readyJobQueue) {
			if (readyJobQueue.isEmpty()) {
				return false;
			}
			return true;
		}
	}
	
	
	private void processRequest(RequestElevatorEvent request) {
		if (scheduler.getStartTime() == null) {
			scheduler.setStartTime();
		}
		System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": Processing Elevator Request");
		ElevatorJob job;
		for (ElevatorInfo el: scheduler.getElevatorInfoDatabase().values()) {
			if (scheduler.getElevatorJobDatabase().get(el.getElevatorID()).isEmpty()) {
				job = new ElevatorJob(request, el.getElevatorID());
				scheduler.addJobToElevatorQueue(el.getElevatorID(), job);
				synchronized (readyJobQueue) {
					readyJobQueue.add(job);
					readyJobQueue.notify();
				}
				//scheduler.startTimer(scheduler, job.getElevatorID(), new Fault("ElevatorbtwFloor"), 120);
				return;
			}
		}
		
		for (ElevatorInfo el: scheduler.getElevatorInfoDatabase().values()) {
			if (el.getDirection() == request.getDirection()
					&& ((el.getDirection() == Direction.UP && el.getCurrentfloor() < request.getCurrentfloornumber() - 1)
					|| (el.getDirection() == Direction.DOWN && el.getCurrentfloor() > request.getCurrentfloornumber() + 1))) {
				job = new ElevatorJob(request, el.getElevatorID());
				scheduler.addJobToElevatorQueue(el.getElevatorID(), job);
				synchronized (readyJobQueue) {
					readyJobQueue.add(job);
					readyJobQueue.notify();
				}
				return;
			}
		}
		
		String bestElevator = (String) scheduler.getElevatorInfoDatabase().keySet().toArray()[0];
		for (ElevatorInfo el: scheduler.getElevatorInfoDatabase().values()) {
			if (scheduler.getElevatorJobDatabase().get(el.getElevatorID()).size() < scheduler.getElevatorJobDatabase().get(bestElevator).size())
				bestElevator = el.getElevatorID();
		}
		job = new ElevatorJob(request, bestElevator);
		scheduler.addJobToElevatorQueue(bestElevator, job);
		synchronized (readyJobQueue) {
			readyJobQueue.add(job);
			readyJobQueue.notify();
		}
	}
	
	public void run() {
		RequestElevatorEvent request = null;
		while(true) {
			synchronized (jobRequestQueue) {
				 try {
					 while (jobRequestQueue.isEmpty())
						 jobRequestQueue.wait();
				 } catch (InterruptedException e) {
					 return;
				 }
				 request = jobRequestQueue.remove(0);
			 } // end of critical section
			processRequest(request);
		}
	}

}
