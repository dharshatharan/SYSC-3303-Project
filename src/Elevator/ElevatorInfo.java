package Elevator;
import java.lang.String;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Constants.Direction;

/**
 * Stores the informatoin about elevator job 
 * @author Dharsh
 * @version 02/06/21
 *
 */
public class ElevatorInfo {
	private Pattern elevatorInfoPattern = Pattern.compile("^0[1-9] [1-2] [1-9] [1-9] [1-3] ");
	
	private Boolean arrived;
	private String elevatorID;
    private int currentFloor;
    private Direction direction;
    private int operationalStatus; //1 operational, 2 non-operational

    /**
     * Default constructor
     * @param time
     * @param currentFloor
     * @param direction
     * @param destinationFloor
     */
    public ElevatorInfo(Boolean arrived, String elevatorID, int currentFloor, Direction direction, int operationalStatus) {
        this.arrived = arrived;
        this.elevatorID = elevatorID;
    	this.currentFloor = currentFloor;
        this.direction = direction;
        this.operationalStatus = operationalStatus;
    }
    
    public ElevatorInfo(byte[] data) throws Exception {
    	String s = new String(data);
		Matcher matcher = elevatorInfoPattern.matcher(s);
		if (matcher.find()) {
			String[] sa = s.split(" ");
			this.arrived = sa[1].equals("1");
	    this.elevatorID = sa[2];
	    this.currentFloor = Integer.parseInt(sa[3]);
	    this.direction = sa[4].equals("1") ? Direction.UP : sa[4].equals("2") ? Direction.DOWN : Direction.Idle;
	    this.operationalStatus = Integer.parseInt(sa[5]);
		} else {
			throw new Exception("Invalid byte array for ElevatorInfo!");
		}
    }
    
    public byte[] getByteArray(String byteCode) {
    	String s = byteCode + " " + this.toStringForByteArray();
    	return s.getBytes();
    }
    
    /**
     * Getter for is arriving
     * @return isArriving
     */
    public Boolean getIsArriving() {
    	return arrived;
    }
    
    /**
     * Getter for elevator ID
     * @return elevatorID
     */
    public String getElevatorID() {
    	return elevatorID;
    }

    /**
     * Getter for the current floor number of the call
     * @return currentfloornumber
     */
	public int getCurrentfloor() {
		return currentFloor;
	}
    /**
     * Getter for the direction of the call
     * @return direction
     */
	public Direction getDirection() {
		return direction;
	}
	
	public int getOperational() {
		return operationalStatus;
	}
	
	/**
	 * Returns the informatioon about the elevator in a string 
	 * @return strElevatorInfo
	 */
	public String toStringForByteArray() {
		return (arrived ? "1" : "2")+ " " + elevatorID + " " + currentFloor + " " + (direction == Direction.Idle ? "3" : (direction == Direction.UP ? "1" : "2")) + " " + operationalStatus + " " ;
	}
	
	/**
	 * Returns the informatioon about the elevator in a string 
	 * @return strElevatorInfo
	 */
	@Override
	public String toString() {
		return elevatorID + " " + currentFloor + " " + direction + " " + (arrived ? "ARRIVED" : " ") + operationalStatus + " " ;
	}

}