/**
 * 
 */
package Elevator;

import Constants.Direction;
import Constants.FloorNumber;

/**
 * @author Dharsh
 *
 */
public class ElevatorJob implements java.lang.Comparable<ElevatorJob> {
	
	private FloorNumber floorNumber;
	private Direction directionSeeking;
	

	public ElevatorJob(FloorNumber floorNumber, Direction directionSeeking) {
		this.floorNumber = floorNumber;
		this.directionSeeking = directionSeeking;
	}


	public Direction getDirectionSeeking() {
		return directionSeeking;
	}


	public FloorNumber getFloorNumber() {
		return floorNumber;
	}


	@Override
	public int compareTo(ElevatorJob o) {
		return this.getFloorNumber().number - o.getFloorNumber().number;
	}
	
	@Override
	public String toString() {
		return floorNumber + directionSeeking.toString();
	}
	
}
