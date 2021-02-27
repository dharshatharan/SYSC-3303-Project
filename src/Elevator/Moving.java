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
	elevatorSpeed state = elevatorSpeed.stopped;
	Direction direction;
	
	
	
	public Moving(Elevator elevator) {
        super(elevator);
    }
	
	
	
	
	
	//Sleeps to mimic the acceleration period
	public void accelerate() {
		if(!state.equals(elevatorSpeed.maxSpeed)) {
			try {
				Thread.sleep(250);
			}catch (InterruptedException e){
			}
			System.out.println("Elevator has now accelerated to max speed");
			state = elevatorSpeed.maxSpeed;
		}
		else {
			System.out.println("The elevator is already at max speed. Going: "+ elevator.getDirection());
		}	
		}
	
	
	
	//To emulate moving between floors this function will be called
	public void move() {
		try {
			Thread.sleep(250);
		}catch (InterruptedException e) {
			//Will include arrival sensors HERE in the future!
		}
		if(elevator.getDirection().equals(Direction.UP)) {
			elevator.setFloor( elevator.getCurFloor() +1);
		}
		if (elevator.getDirection().equals(Direction.DOWN)) {
			elevator.setFloor(elevator.getCurFloor() -1);
		}
	}

	//Sleeps to mimic the deceleration period
	public void deccelerate() {
		if(state.equals(elevatorSpeed.maxSpeed)) {
			try {
				Thread.sleep(250);
			}catch (InterruptedException e) {
			}
			System.out.println("Elevator has deccelerated to a stop. Going: "+ elevator.getDirection());
			state = elevatorSpeed.stopped;
		}
		
	}

	/*
	 * When the move state is entered, it accelerates, moves at a constant speed, and deccelerates. 
	 * If there's only a single floor, it only moves for the duration of a single floor. 
	 */
	public void enter() {
		accelerate();
		while(Math.abs(elevator.getCurFloor() - elevator.getDestination()) >1) { //While the floor is less  than one away	
			move();
		}
		if(Math.abs(elevator.getCurFloor() - elevator.getDestination()) == 1) {
			move();
			deccelerate();
		}
		exit();
	}
	
	public void exit() {
		elevator.setState(new Stopped(elevator));
		elevator.getState().enter();
	}
	
	
	
	
	
	

}
