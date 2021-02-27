package Elevator;

/**
 * A state of the elevator for when it is stoped at a floor. Opens and closes the doors, changes to move or idle
 * @version 2/27/2021
 * @author Quinn Sondermeyer
 *
 */
public class Stopped extends ElevatorState{
	Elevator elevator;
	public Stopped() {
		super();
	}
	
	
	public void openDoors() {
		System.out.println("Doors open");
		
	}
	
	public void CloseDoors() {
		System.out.println("Doors closed");
		//elevator.changeState();   	// TODO Desides to change state to idel or moving
	
	}
	
	
	public void idel() {
		//elevator.changeState();
	}


	@Override
	public void exti() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void enter() {
		// TODO Auto-generated method stub
		
	}
	
	

}
