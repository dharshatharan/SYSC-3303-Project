package Elevator;

/**
 * 
 * An abstarct class for the states of an elevator
 * 
 * @version 2/27/2021
 * @author Quinn Sondermeyer
 *
 */
public abstract class ElevatorState {
	Elevator elevator;
	
	/**
	 * Default constructor for the Abstarct parent state class
	 * @param elevator
	 */
	public ElevatorState(Elevator elevator) {
		this.elevator = elevator;
	}
	
	/**
	 * Start Task for elevator for state
	 */
	public abstract void enter();
	
	/**
	 * End Task for elevator for state and switch state
	 */
	public abstract void exit();
	
}
