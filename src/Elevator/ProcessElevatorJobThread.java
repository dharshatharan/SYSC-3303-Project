/**
 * 
 */
package Elevator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import Elevator.ElevatorInfo;
import Elevator.ElevatorJob;

/**
 * @author Dharsh
 *
 */
public class ProcessElevatorJobThread extends Thread {
	private Elevator elevator;
	private List<ElevatorJob> elevetorJobUnprocessed;
	private List<ElevatorJob> elevetorJobProcessed;

	/**
	 * 
	 */
	public ProcessElevatorJobThread(Elevator elevator) {
		this.elevator = elevator;
		this.elevetorJobUnprocessed = Collections.synchronizedList(new LinkedList<ElevatorInfo>());
		this.elevetorJobProcessed = Collections.synchronizedList(new LinkedList<ElevatorInfo>());
	}
	
	public final void enqueueInfo(ElevatorInfo request) {
		synchronized (elevetorJobUnprocessed) {
			elevetorJobUnprocessed.add(request);
			elevetorJobUnprocessed.notify();
		}
	}
	
	public ElevatorInfo dequeueProcessedInfo() {
		synchronized (elevetorJobProcessed) {
			while (elevetorJobProcessed.isEmpty()) {
				try {
	                wait();
	            } catch (InterruptedException e)  {
	                Thread.currentThread().interrupt(); 
	            }
			}
			ElevatorInfo nextInfo = elevetorJobProcessed.remove(0);
			notifyAll();
			return nextInfo;
		}
	}
	
	private void processInfo(ElevatorInfo info) {
		
		elevetorJobProcessed.add(info);
	}
	
	@Override
	public void run() {
		ElevatorInfo request = null;
		while(true) {
			synchronized (elevetorJobUnprocessed) {
				 try {
					 while (elevetorJobUnprocessed.isEmpty())
						 elevetorJobUnprocessed.wait();
				 } catch (InterruptedException e) {
					 return;
				 }
				 request = elevetorJobUnprocessed.remove(0);
			 } // end of critical section
			processInfo(request);
		}
	}

}
