/**
 * 
 */
package Elevator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Constants.Direction;
import Floor.RequestElevatorEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Stores the informatoin about each job created for the elevator to exicute
 * @author Dharsh
 * @version 02/06/21
 *
 */
public class ElevatorJob implements java.lang.Comparable<ElevatorJob> {
	private Pattern elevatorInfoPattern = Pattern.compile("^0[1-9] [1-9] [0-9]{1,2} [1-2] [0-9]{1,2} [0-9]");
	
	private String elevatorID;
	private int fromFloor;
	private int toFloor;
	private Direction directionSeeking;
	private int fault;
	
	

	/**
	 * Default constructor
	 * @param floorNumber 
	 * @param directionSeeking
	 * 
	 */
	public ElevatorJob(String elevatorID, int fromFloor, int toFloor, Direction directionSeeking, int fault) {
		this.elevatorID = elevatorID;
		this.fromFloor = fromFloor;
		this.toFloor = toFloor;
		this.directionSeeking = directionSeeking;
		this.fault = fault;
	}
	
	public ElevatorJob(RequestElevatorEvent event, String elevatorID) {
		this.elevatorID = elevatorID;
		this.fromFloor = event.getCurrentfloornumber();
		this.toFloor = event.getDestinationfloornumber();
		this.directionSeeking = event.getDirection();
		this.fault = event.getFault();
		
	}
	
	public ElevatorJob(byte[] data) throws Exception {
    	String s = new String(data);
    	System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": " + s);
		Matcher matcher = elevatorInfoPattern.matcher(s);
		if (matcher.find()) {
			String[] sa = s.split(" ");
			this.elevatorID = sa[1];
	        this.fromFloor = Integer.parseInt(sa[2]);
	    	this.directionSeeking = sa[3].equals("1") ? Direction.UP : Direction.DOWN;
	        this.toFloor = Integer.parseInt(sa[4]);
	        this.fault = Integer.parseInt(sa[5]);
		} else {
			throw new Exception("Invalid byte array for ElevatorJob!");
		}
    }
	
	public byte[] getByteArray(String byteCode) {
    	String s = byteCode + " " + this.toStringForByteArray();
    	return s.getBytes();
    }


	/**
	 * Getter for the direction of the job
	 * @return directionSeeking
	 */
	public Direction getDirectionSeeking() {
		return directionSeeking;
	}


	/**
	 * Compares job with the job passed in
	 * @param comparison
	 * @return difference
	 */
	@Override
	public int compareTo(ElevatorJob o) {
		if (directionSeeking == Direction.UP)
			return o.getToFloor() - this.getToFloor();
		return this.getToFloor() - o.getToFloor();
	}
	
	/**
	 * @return the elevatorID
	 */
	public String getElevatorID() {
		return elevatorID;
	}
	
	/**
	 * @return the fromFloor
	 */
	public int getFromFloor() {
		return fromFloor;
	}
	
	/**
	 * @return the toFloor
	 */
	public int getToFloor() {
		return toFloor;
	}
	
	/**
	 * Getter for the fault boolean(if this job has a fault- returns true)
	 */
	public int getFault() {
		return fault;
	}
	
	
	/**
	 * prepares a string to be printed to represen the data transferd for the byte array
	 * @return Information about the job
	 */
	public String toStringForByteArray() {
		return elevatorID + " " + fromFloor + " " + (directionSeeking == Direction.UP ? "1" : "2") + " " + toFloor + " " + fault + " ";
	}

	/**
	 * prepares a string to be printed to represen the data transferd
	 * @return Information about the job
	 */
	@Override
	public String toString() {
		return elevatorID + " " + fromFloor + " " + (directionSeeking == Direction.UP ? "1" : "2") + " " + toFloor + " ";
	}
	
}
