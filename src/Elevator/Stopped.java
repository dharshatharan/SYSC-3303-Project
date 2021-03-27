package Elevator;
import Constants.*;
import Floor.RequestElevatorEvent;
import java.util.Date;

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
		
		if(elevator.getOperationalStatus()) {
			
			System.out.println("Doors open");
			elevator.setDoorState(true);
			
		} else {
			
			System.out.println("Doors unable to open");
			
		}
		
		System.out.println("Doors open");
		elevator.setDoorState(true);
		// Sleep or wait for new job or for set time
		
		
	}
	
	/**
	 * Simulates the closing doors. starts to process to change state to idel or moving
	 */
	public void closeDoors() {
		System.out.println("Doors closed");
		idle();
	}
	
	/**
	 * Sets state to idel when there are no more jobs
	 */
	public void idle() {
		//elevator.changeState();
		elevator.setState(new Idle(elevator));
		exit();
	}
	
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
		elevator.setState(new Idle(elevator));
		elevator.getState().enter();
	}

	/**
	 * Starts task for Stopped state, notifies scheduler that the elevator has arrived
	 */
	@Override
	public void enter() {
		System.out.println("---------------------Elevator State changed to: STOPPED-STATE---------------------");
		notifyElevatorArrival();
		Date d0 = new Date();
		long t0 = d0.getTime();
		long t1;
		
		if(!checkForFaults()){
			try {
				Thread.sleep(1000);
				Date d1 = new Date();
				t1 = d1.getTime();
			}catch(InterruptedException e7) {
			}	
		} else {
			try {
				Thread.sleep(5000);
				Date d2 = new Date();
				t1 = d2.getTime();
			}catch(InterruptedException e7) {
			}
		}
		
		tryOpenCloseDoor(t0, t1, 2000, true) // timeout set for 2s
		
		
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		tryOpenCloseDoor(tc0, tc1, 2000, false)
		
		closeDoors();
		exit();
			
	}

	/**
	 * Notifies The Scheduler that the elevator has completed a job or moving the elevator to the floor of the first job
	 */
	private void notifyElevatorArrival() {
		if (elevator.getPreJob() == null && elevator.getJob() != null) {
			ElevatorJob job = elevator.getJob();
			elevator.getElevatorSubsystem().addElevatorInfoList(new ElevatorInfo(true, job.getElevatorID(), job.getToFloor(), job.getDirectionSeeking()));
		}
	}
	
	public boolean checkForFaults() {
		
		return elevator.getJob().hasFault();
		
	}
	
	public void tryOpenCloseDoor(long initTime, long endTime, long timeout, boolean toOpen) {
		
		if(endTime < (initTime + timeout)){
			if(toOpen) {
				openDoors();
			}
			else {
				closeDoors();
			}
			
		} else {
			elevator.setOperationalStatus(false);
			if(toOpen) {
				
				openDoors();
				
			} else {
				closeDoors();
			}
		}
		
	}
		
}
	
	


