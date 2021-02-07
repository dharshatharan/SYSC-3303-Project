package Elevator;
import Constants.*;

/**
 * 
 * 
 * @author Quinn Sondermeyer
 * @version 02/06/2021
 */
public class ElevatorJob {
	private FloorNumber floorNum;
	private Direction direction; 
	private Elevator elevator;
	
	public ElevatorJob(FloorNumber floorNum, Direction direction, Elevator elevator) {
		this.floorNum = floorNum;
		this.direction = direction;
		this.elevator = elevator;
		
	}

	public FloorNumber getFloorNumber() {
		return floorNum;
	}
	public Direction getdirection() {
		return direction;
	}
	public Elevator getElevator() {
		return elevator;
	}
}
