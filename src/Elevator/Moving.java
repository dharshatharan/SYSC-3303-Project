package Elevator;
import Constants.Direction;

/**
 * A state of the elevator, while moving between floors simulates the downtime.
 * 
 * @ Author Bailey Lyster 101115419
 * @Version 02/27/2021
 */

public class Moving extends ElevatorState{	
	
	//Assumes that going accelerating to maxspeed going downwards is the same as going up.
	public enum elevatorSpeed{
		maxSpeed,
		stopped
	}
	private elevatorSpeed state = elevatorSpeed.stopped;
	private Direction direction;
	
	
	
	public Moving(Elevator elevator) {
        super(elevator);
    }
	
	
	/*
	 * When the move state is entered, it accelerates, moves at a constant speed, and deccelerates. 
	 * If there's only a single floor, it only moves for the duration of a single floor. 
	 */
	public void enter() {
		System.out.println("---------------------Elevator State changed to: MOVING-STATE---------------------");
		accelerate();
		while(Math.abs(elevator.getCurFloor() - elevator.getDestination()) >1) { //While the floor is less  than one away	
			moveAtMaxSpeed();
		}
		if(Math.abs(elevator.getCurFloor() - elevator.getDestination()) == 1) {
			moveAtMaxSpeed();
			deccelerate();
		}
		exit();
	}
	
	
	//Sleeps to mimic the acceleration period
	public void accelerate() {
		if(!state.equals(elevatorSpeed.maxSpeed)) {
			try {
				System.out.println("The elevator is accelerating "+ elevator.getDirection());
				Thread.sleep(300);
			}catch (InterruptedException e){
			}
			System.out.println("Elevator has now accelerated to max speed");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			MoveFloors();
			state = elevatorSpeed.maxSpeed;
		}
				
		}
	
	
	
	//To emulate moving between floors this function will be called
	public void moveAtMaxSpeed() {
		try {
			Thread.sleep(1000);
		}catch (InterruptedException e) {
			//Will include arrival sensors HERE in the future!
		}
		MoveFloors();
	}
	
	
	/**
	 * 
	 */
	private void MoveFloors() {
		if(elevator.getDirection().equals(Direction.UP)) {
			elevator.setFloor( elevator.getCurFloor() +1);
			System.out.println("Elevator went from " + (elevator.getCurFloor() - 1) + " to " + elevator.getCurFloor() );
			
		}
		if (elevator.getDirection().equals(Direction.DOWN)) {
			elevator.setFloor(elevator.getCurFloor() -1);
			System.out.println("Elevator went from " + (elevator.getCurFloor() + 1) + " to " + elevator.getCurFloor() );
		}
	}

	//Sleeps to mimic the deceleration period
	public void deccelerate() {
		if(state.equals(elevatorSpeed.maxSpeed)) {
			try {
				Thread.sleep(1000);
			}catch (InterruptedException e) {
			}
			System.out.println("Elevator has deccelerated to a stop. Direction: "+ elevator.getDirection());
			state = elevatorSpeed.stopped;
		}
		
	}
	
	public void exit() {
		elevator.setState(new Stopped(elevator));
		elevator.getState().enter();
	}
	
	
	
	
	
	

}
