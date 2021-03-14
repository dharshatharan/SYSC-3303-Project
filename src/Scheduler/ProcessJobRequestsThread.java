/**
 * 
 */
package Scheduler;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import Elevator.ElevatorJob;
import Floor.RequestElevatorEvent;

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
		this.scheduler = scheduler;
		this.jobRequestQueue = Collections.synchronizedList(new LinkedList<RequestElevatorEvent>());
		this.readyJobQueue = Collections.synchronizedList(new LinkedList<ElevatorJob>());
		this.start();
	}
	
	public final void enqueueJobRequest(RequestElevatorEvent request) {
		synchronized (jobRequestQueue) {
			jobRequestQueue.add(request);
			jobRequestQueue.notify();
		}
	}
	
	public ElevatorJob dequeueReadyJob() {
		synchronized (readyJobQueue) {
			while (readyJobQueue.isEmpty()) {
				try {
	                wait();
	            } catch (InterruptedException e)  {
	                Thread.currentThread().interrupt(); 
	            }
			}
			ElevatorJob nextJob = readyJobQueue.remove(0);
			notifyAll();
			return nextJob;
		}
	}
	
	private void processRequest(RequestElevatorEvent request) {
		// TODO: Redo this logic
		ElevatorJob job;
		if (scheduler.getElevatorJobDatabase().get("2").size() >= scheduler.getElevatorJobDatabase().get("1").size()) {
			job = new ElevatorJob(request, "1");
			scheduler.addJobToElevatorQueue("1", job);
		} else {
			job = new ElevatorJob(request, "2");
			scheduler.addJobToElevatorQueue("2", job);
		}
		readyJobQueue.add(job);
	}
	
	@Override
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
