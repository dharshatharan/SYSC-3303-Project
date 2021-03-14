/**
 * 
 */
package Elevator;

import Constants.Direction;
import Constants.FloorNumber;

/**
 * Stores the informatoin about each job created for the elevator to exicute
 * @author Dharsh
 * @version 02/06/21
 *
 */
public class ElevatorJob implements java.lang.Comparable<ElevatorJob> {
	
	private int floorNumber;
	private Direction directionSeeking;
	

	/**
	 * Default constructor
	 * @param floorNumber 
	 * @param directionSeeking
	 * 
	 */
	public ElevatorJob(int floorNumber, Direction directionSeeking) {
		this.floorNumber = floorNumber;
		this.directionSeeking = directionSeeking;
	}


	/**
	 * Getter for the direction of the job
	 * @return directionSeeking
	 */
	public Direction getDirection() {
		return directionSeeking;
	}

	/**
	 * Getter for the floorNumber of the job
	 * @return floorNumber
	 */
	public int getFloorNumber() {
		return floorNumber;
	}


	/**
	 * Compares job with the job passed in
	 * @param comparison
	 * @return difference
	 */
	@Override
	public int compareTo(ElevatorJob o) {
		return this.getFloorNumber() - o.getFloorNumber();
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
