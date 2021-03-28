package Elevator;
import Constants.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Floor.RequestElevatorEvent;
import java.util.Date;
import java.util.List;

/**
 * A state of the elevator for when it is stoped at a floor. Opens and closes the doors, changes to move or idle
 * 
 * @version 2/27/2021
 * @author Quinn Sondermeyer
 *
 */
public class Stopped extends ElevatorState{
	
	/**
	 * Default Constructor for the Stopped state
	 * @param elevator
	 */
	public Stopped(Elevator elevator) {
		super(elevator);
	}

	Direction d = Direction.UP;
	Date date;

	
	/**
	 * Simulates arrival and opens door. Runs set task, wait, closedoor
	 */
	public void openDoors() {
		System.out.println("Doors opening...");
		Timer timer = new Timer(true);
		TimerTask openTask = new TimerTask() {
	        public void run() {
	            if(elevator.getDoorState() != DoorStatus.open) {
	            	System.out.println("Failed to open doors, retrying...");
	            	retryOpen();
	            }
	        }
	    };
		
		timer.schedule(openTask, 1100);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		List<ElevatorJob> removeJobs = elevator.startFinishAllJobsInCurFloor();
		for(ElevatorJob job: removeJobs) {
			if (job.getFault() == 1) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		elevator.setDoorState(true);
		
	}
	
	private void retryOpen() {
		elevator.setDoorState(false);
		for(int i=0; i < 2; i++) {
			System.out.println("Retrying to open door: " + (i + 1) + " time");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("Shutting down elevator " + elevator.getElevatorId());
		elevator.getElevatorSubsystem().addElevatorInfoList(new ElevatorInfo(true, elevator.getElevatorId(), elevator.getCurrentFloor(), elevator.getDirection(), 2));
		elevator.setOperationalStatus(false);
		elevator.interrupt();
	}
	
	private void retryClose() {
		for(int i=0; i < 2; i++) {
			System.out.println("Retrying to close door: " + i + 1 + " time");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("Shutting down elevator " + elevator.getElevatorId());
		// communicate shutdown
	}
	
	/**
	 * Simulates the closing doors. starts to process to change state to idel or moving
	 */
	public void closeDoors() {
//		if(elevator.getOperationalStatus() && elevator.getDoorState().equals(DoorStatus.open)) {
//			
//			System.out.println("Doors closed");
//			elevator.setDoorState(false);
//			
//		} else {
//			
//			System.out.println("Doors unable to close, attempting to correct...");
//			correctFault(false);
//		}
		System.out.println("Doors closing...");
		
//		Timer timer = new Timer(true);
//		TimerTask closeTask = new TimerTask() {
//	        public void run() {
//	            if(elevator.getDoorState() != DoorStatus.closed) {
//	            	System.out.println("Failed to close doors, retrying...");
//	            	retryClose();
//	            }
//	        }
//	    };
//		
//		timer.schedule(closeTask, 1100);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		elevator.setDoorState(false);
		
//		List<ElevatorJob> removeJobs = elevator.startFinishAllJobsInCurFloor();
//		for(ElevatorJob job: removeJobs) {
//			if (job.getFault() == 1) {
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//			}
//		}
		
	}
	
	/**
	 * Sets state to idel when there are no more jobs
	 */
//	public void idle() {
//		//elevator.changeState();
//		elevator.setState(new Idle(elevator));
//		exit();
//	}
	
	/**
	 * Sets state to Move when there are jobs
	 */
	public void moving() {
		//elevator.changeState();
		elevator.setState(new Moving(elevator));
		exit();
	}
	


	/**
	 * Exits State of being stoped at the floor
	 */
	@Override
	public void exit() {
//		if (elevator.getCurFloor() == elevator.getJob().getFromFloor()) {
//			elevator.setDestination(elevator.getJob().getToFloor());
//			elevator.setDirection(elevator.getJob().getDirectionSeeking());
//			elevator.setPreJob(null);
//			elevator.setState(new Moving(elevator));
//		}
//		else {
//			elevator.setState(new Idle(elevator));
//			elevator.setJob(null);
//		}
//		elevator.setState(new Idle(elevator));
//		elevator.getElevatorState().enter();
	}

	/**
	 * Starts task for Stopped state, notifies scheduler that the elevator has arrived
	 */
	@Override
	public void enter() {
		System.out.println("---------------------Elevator State changed to: STOPPED-STATE---------------------");
		notifyElevatorArrival();
		 openDoors();
		 if (elevator.getDoorState() == DoorStatus.open) closeDoors();
//		List<ElevatorJob> removeJobs = elevator.startFinishAllJobsInCurFloor();
		
		
//		Date d0 = new Date();
//		long t0 = d0.getTime();
//		long t1;
//		
//		if(!checkForFaults()){
//			try {
//				Thread.sleep(1000);
//				Date d1 = new Date();
//				t1 = d1.getTime();
//			}catch(InterruptedException e7) {
//			}	
//		} else {
//			try {
//				Thread.sleep(5000);
//				Date d2 = new Date();
//				t1 = d2.getTime();
//			}catch(InterruptedException e7) {
//			}
//		}
//		
//		tryOpenCloseDoor(t0, t1, 2000, true); // timeout set for 2s
//		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
//		
//		Date dc0 = new Date();
//		long tc0 = dc0.getTime();
//		long tc1;
//		
//		if(!checkForFaults()){
//			try {
//				Thread.sleep(1000);
//				Date dc1 = new Date();
//				tc1 = dc1.getTime();
//			}catch(InterruptedException e7) {
//			}	
//		} else {
//			try {
//				Thread.sleep(5000);
//				Date dc2 = new Date();
//				tc1 = dc2.getTime();
//			}catch(InterruptedException e7) {
//			}
//		}
//		
//		tryOpenCloseDoor(tc0, tc1, 2000, false);

		exit();
			
	}

	/**
	 * Notifies The Scheduler that the elevator has completed a job or moving the elevator to the floor of the first job
	 */
	private void notifyElevatorArrival() {
//		if (elevator.getPreJob() == null && elevator.getJob() != null) {
//			ElevatorJob job = elevator.getJob();
			elevator.getElevatorSubsystem().addElevatorInfoList(new ElevatorInfo(true, elevator.getElevatorId(), elevator.getCurrentFloor(), elevator.getDirection(), 1));
//		}
	}
//	
//	public boolean checkForFaults() {
//		
//		return elevator.getJob().getFault();
//		
//	}
//	
//	public void tryOpenCloseDoor(long initTime, long endTime, long timeout, boolean toOpen) {
//		
//		if(endTime < (initTime + timeout)){
//			elevator.setOperationalStatus(true);
//			if(toOpen) {
//				openDoors();
//			}
//			else {
//				closeDoors();
//			}
//		} else {
//			elevator.setOperationalStatus(false);
//			if(toOpen) {
//				openDoors();
//			} else {
//				closeDoors();
//			}
//		}
//		
//	}
//	
//	//Method to try to correct the open door fault
//	public void correctFault(boolean toOpen) {
//		Random random = new Random();
//		for(int i=0; i<2; i++) {
//			int checkFix = random.nextInt(99) + 1;
//			if(checkFix % 2 == 0) {
//				elevator.setOperationalStatus(true);
//			}
//		}
//		if(elevator.getOperationalStatus()) {
//			System.out.println("Fault corrected!");
//			if(toOpen) {
//				try {
//					Thread.sleep(1000);
//					openDoors();
//				}catch(InterruptedException e7) {
//				}
//			} else {
//				try {
//					Thread.sleep(1000);
//					closeDoors();
//				}catch(InterruptedException e7) {
//				}
//			}
//		} else {
//			if (!elevator.getOperationalStatus()){ //if true ?
//				
//				System.out.println("This Elevator should be suspended as the door is Faulty.");
//				this.wait();
//			}
//		}
//	}
		
}
	
	


