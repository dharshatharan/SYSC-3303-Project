/**
 * 
 */
package Scheduler;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import Elevator.ElevatorInfo;
import Elevator.ElevatorJob;

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
	                wait();
	            } catch (InterruptedException e)  {
	                Thread.currentThread().interrupt(); 
	            }
			}
			ElevatorInfo nextInfo = elevetorInfoProcessed.remove(0);
			notifyAll();
			return nextInfo;
		}
	}
	
	private void processInfo(ElevatorInfo info) {
		// TODO: Redo this logic
		List<ElevatorJob> elevatorJobs = scheduler.getElevatorJobDatabase().get(info.getElevatorID());
		for(ElevatorJob job: elevatorJobs) {
			if (info.getIsArriving() && job.getToFloor() == info.getCurrentfloor()) {
				elevatorJobs.remove(elevatorJobs.indexOf(job));
			}
		}
		scheduler.updateElevatorInfo(info.getElevatorID(), info);
		elevetorInfoProcessed.add(info);
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
