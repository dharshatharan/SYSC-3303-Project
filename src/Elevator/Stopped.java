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
		
		System.out.println("Doors open");
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
		long time1 = 0;
		long time2 = 0;
		if(!elevator.getJob().hasFault()){
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e7) {
			}	
		}
		
		}
		
		
		
		openDoors();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
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
		
}
	
	


