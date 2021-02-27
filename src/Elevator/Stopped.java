package Elevator;

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
		//elevator.changeState();   	// TODO Desides to change state to idel or moving
	
	}
	
	/**
	 * Sets state to idel when there are no more jobs
	 */
	public void idel() {
		//elevator.changeState();
		exit();
	}


	/**
	 * Exits State of being stoped at the floor
	 */
	@Override
	public void exit() {
		notifyAll();
		
	}


	@Override
	public void enter() {
		//TODO
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
