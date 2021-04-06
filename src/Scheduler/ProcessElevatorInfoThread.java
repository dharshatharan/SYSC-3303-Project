/**
 * 
 */
package Scheduler;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import Elevator.ElevatorInfo;
import Elevator.ElevatorJob;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Dharsh
 *
 */
public class ProcessElevatorInfoThread extends Thread {
	private Scheduler scheduler;
	private List<ElevatorInfo> elevetorInfoUnprocessed;
	private List<ElevatorInfo> elevetorInfoProcessed;

	/**
	 * 
	 */
	public ProcessElevatorInfoThread(Scheduler scheduler) {
		this.scheduler = scheduler;
		this.elevetorInfoUnprocessed = Collections.synchronizedList(new LinkedList<ElevatorInfo>());
		this.elevetorInfoProcessed = Collections.synchronizedList(new LinkedList<ElevatorInfo>());
	}
	
	public final void enqueueInfo(ElevatorInfo request) {
		synchronized (elevetorInfoUnprocessed) {
			elevetorInfoUnprocessed.add(request);
			elevetorInfoUnprocessed.notify();
		}
	}
	
	public ElevatorInfo dequeueProcessedInfo() {
		synchronized (elevetorInfoProcessed) {
			while (elevetorInfoProcessed.isEmpty()) {
				try {
					elevetorInfoProcessed.wait();
	            } catch (InterruptedException e)  {
	                Thread.currentThread().interrupt(); 
	            }
			}
			ElevatorInfo nextInfo = elevetorInfoProcessed.remove(0);
			elevetorInfoProcessed.notify();
			return nextInfo;
		}
	}
	
	private void processInfo(ElevatorInfo info) {
		System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": Processing Elevator Info");
		List<ElevatorJob> elevatorJobs = scheduler.getElevatorJobDatabase().get(info.getElevatorID());
		if (!scheduler.getFaults().get(info.getElevatorID()).isEmpty()&& !(scheduler.getFaults().get(info.getElevatorID()).get("ElevatorbtwFloor") == null)) {
			scheduler.getFaults().get(info.getElevatorID()).get("ElevatorbtwFloor").getTimer().cancel();
		}
       
		if (info.getIsArriving() == true) {
			for(ElevatorJob job: elevatorJobs) {
				if (info.getIsArriving() && job.getToFloor() == info.getCurrentfloor()) {
					elevatorJobs.remove(elevatorJobs.indexOf(job));
				}
			}
		} else {
			scheduler.startTimer(scheduler, info.getElevatorID(), new Fault("ElevatorbtwFloor"), 5);
		}
		if (info.getOperational() == 2) {
			scheduler.getElevatorJobDatabase().remove(info.getElevatorID());
			scheduler.getElevatorInfoDatabase().remove(info.getElevatorID());
		}
		
		scheduler.updateElevatorInfo(info.getElevatorID(), info);
		synchronized (elevetorInfoProcessed) {
			elevetorInfoProcessed.add(info);
			elevetorInfoProcessed.notify();
		}
	}
	
	@Override
	public void run() {
		ElevatorInfo request = null;
		while(true) {
			synchronized (elevetorInfoUnprocessed) {
				 try {
					 while (elevetorInfoUnprocessed.isEmpty())
						 elevetorInfoUnprocessed.wait();
				 } catch (InterruptedException e) {
					 return;
				 }
				 request = elevetorInfoUnprocessed.remove(0);
			 } // end of critical section
			processInfo(request);
		}
	}

}
