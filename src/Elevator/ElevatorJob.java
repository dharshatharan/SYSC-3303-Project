/**
 * 
 */
package Elevator;

import Constants.Direction;
import Constants.FloorNumber;

/**
 * Stores the informatoin about each job created for the elevator to execute
 * @author Dharsh
 * @version 02/06/21
 *
 */
public class ElevatorJob implements java.lang.Comparable<ElevatorJob> {
	
	private FloorNumber floorNumber;
	private Direction directionSeeking;
	

	/**
	 * Default constructor
	 * @param floorNumber 
	 * @param directionSeeking
	 * 
	 */
	public ElevatorJob(FloorNumber floorNumber, Direction directionSeeking) {
		this.floorNumber = floorNumber;
		this.directionSeeking = directionSeeking;
	}


	/**
	 * Getter for the direction of the job
	 * @return directionSeeking
	 */
	public Direction getDirectionSeeking() {
		return directionSeeking;
	}

	/**
	 * Getter for the floorNumber of the job
	 * @return floorNumber
	 */
	public FloorNumber getFloorNumber() {
		return floorNumber;
	}


	/**
	 * Compares job with the job passed in
	 * @param comparison
	 * @return difference
	 */
	@Override
	public int compareTo(ElevatorJob o) {
		return this.getFloorNumber().number - o.getFloorNumber().number;
	}
	
	/**
	 * prepares a string to be printed to represen the data transferd
	 * @return Information about the job
	 */
	@Override
	public String toString() {
		return floorNumber + directionSeeking.toString();
	}
	
}
