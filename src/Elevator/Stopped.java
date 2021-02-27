package Elevator;
import Constants.*;

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
	/**
	 * Simulates arrival and opens door. Runs set task, wait, closedoor
	 */
	public void openDoors() {
		System.out.println("Doors open");
		// Sleep or wait for new job or for set time
		closeDoors();
		
	}
	
	/**
	 * Simulates the closing doors. starts to process to change state to idel or moving
	 */
	public void closeDoors() {
		System.out.println("Doors closed");
		if (elevator.getJob().equals(null)) {
			idel();
		}
		else {
			moving();		// TODO Desides to change state to idel or moving
		}
		exit();
	}
	
	/**
	 * Sets state to idel when there are no more jobs
	 */
	public void idel() {
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
		notifyAll();
		elevator.getState().enter();
		
	}


	@Override
	public void enter() {
		openDoors();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		closeDoors();
			
			
	}
		
}
	
	


