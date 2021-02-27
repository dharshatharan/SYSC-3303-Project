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
	
	public abstract void enter();
	
	public abstract void exit();
	
}
