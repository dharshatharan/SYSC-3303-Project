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
	private Elevator elevator;
	
	public ElevatorState() {
	}
	
	public abstract void enter();
	
	public abstract void exti();
	
}
