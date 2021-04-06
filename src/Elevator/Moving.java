package Elevator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Constants.Direction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A state of the elevator, while moving between floors simulates the downtime.
 * 
 * @ Author Bailey Lyster 101115419
 * @Version 02/27/2021
 */

public class Moving extends ElevatorState{	
	
	/**
	 * Enum for checking if the elevator is at max speed
	 *
	 * @Author Bailey Lyster 101115419
	 */
	public enum elevatorSpeed{
		maxSpeed,
		stopped
	}
	private elevatorSpeed state = elevatorSpeed.stopped;
	
	
	/**
	 * Default Constructo calls partent class
	 * @param elevator
	 */	
	public Moving(Elevator elevator) {
        super(elevator);
    }
	
	
	/**
	 * Starts task for Moving state
	 * When the move state is entered, it accelerates, moves at a constant speed, and deccelerates. 
	 * If there's only a single floor, it only moves for the duration of a single floor. 
	 */
	public void enter() {
		System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": ---------------------Elevator State changed to: MOVING-STATE---------------------");
				
		while(!elevator.isInterrupted() && elevator.getOperationalStatus()) {
			int nextDestination = elevator.getNextBestElevatorDestination();
			elevator.setDirection((nextDestination > elevator.getCurrentFloor()) ? Direction.UP : Direction.DOWN);
			if((elevator.getDirection() == Direction.UP && nextDestination == elevator.getCurrentFloor() + 1) || 
					(elevator.getDirection() == Direction.DOWN && nextDestination == elevator.getCurrentFloor() - 1)) {
				if(state.equals(elevatorSpeed.stopped)) {
					accelerate();
				}
				deccelerate();
				break;
			} else if ((elevator.getDirection() == Direction.UP && nextDestination >= elevator.getCurrentFloor() + 1) || 
					(elevator.getDirection() == Direction.DOWN && nextDestination <= elevator.getCurrentFloor() - 1)) {
				if(state.equals(elevatorSpeed.stopped)) {
					accelerate();
					moveAtMaxSpeed();
				} else {
					moveAtMaxSpeed();
				}
			}
		}
		
		exit();
	}
	
	
	/**
	 * Emulates accelerates the elevator to max speed and continues to next floor
	 */
	public void accelerate() {
		if(!state.equals(elevatorSpeed.maxSpeed)) {
			try {
				System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": The elevator " + elevator.getElevatorId() + " is accelerating "+ elevator.getDirection());
				Thread.sleep(300);
			}catch (InterruptedException e){
			}
			System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": Elevator " + elevator.getElevatorId() + " has now accelerated to max speed");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			state = elevatorSpeed.maxSpeed;
		}
				
		}
	
	
	/**
	 * Emulates moving between floors this function will be called
	 */
	public void moveAtMaxSpeed() {
		try {
			Thread.sleep(1000);
		}catch (InterruptedException e) {
			//Will include arrival sensors HERE in the future!
		}
		MoveFloors();
	}
	
	
	/**
	 * Increments Floor number and prints results
	 */
	private void MoveFloors() {
		if(elevator.getDirection().equals(Direction.UP)) {
			elevator.setCurrentFloor( elevator.getCurrentFloor() +1);
			System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": Elevator " + elevator.getElevatorId() + " went from " + (elevator.getCurrentFloor() - 1) + " to " + elevator.getCurrentFloor() );
			
		}
		if (elevator.getDirection().equals(Direction.DOWN)) {
			elevator.setCurrentFloor(elevator.getCurrentFloor() -1);
			System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": Elevator " + elevator.getElevatorId() + " went from " + (elevator.getCurrentFloor() + 1) + " to " + elevator.getCurrentFloor() );
		}
		elevator.getElevatorSubsystem().addElevatorInfoList(new ElevatorInfo(false, elevator.getElevatorId(), elevator.getCurrentFloor(), elevator.getDirection(), 1));

	}

	/**
	 * Emulates deccelerating the elevator to a stop
	 */
	public void deccelerate() {
		if(state.equals(elevatorSpeed.maxSpeed)) {
			try {
				Thread.sleep(1000);
			}catch (InterruptedException e) {
			}
			System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": Elevator " + elevator.getElevatorId() + " has deccelerated to a stop. Direction: "+ elevator.getDirection());
			MoveFloors();
			state = elevatorSpeed.stopped;
		}
		
	}
	/**
	 * Changes to Stop State and ends tasks
	 */	
	public void exit() {
		elevator.setState(new Stopped(elevator));
		elevator.getElevatorState().enter();
	}
}
