package Elevator;
import java.lang.String;
import Constants.Direction;

/**
 * Stores the informatoin about elevator job 
 * @author Dharsh
 * @version 02/06/21
 *
 */
public class ElevatorInfo {

    private String time;
    private int currentfloornumber;
    private Direction direction;
    private int destinationfloornumber;

    /**
     * Default constructor
     * @param time
     * @param currentFloor
     * @param direction
     * @param destinationFloor
     */
    public ElevatorInfo(String time, int currentFloor, Direction direction, int destinationFloor) {
        this.time = time;
        this.currentfloornumber = currentFloor;
        this.direction = direction;
        this.destinationfloornumber = destinationFloor;
    }

    /**
     * Getter for the time of the call
     * @return time
     */
	public String getTime() {
		return time;
	}
    /**
     * Getter for the current floor number of the call
     * @return currentfloornumber
     */
	public int getCurrentfloornumber() {
		return currentfloornumber;
	}
    /**
     * Getter for the direction of the call
     * @return direction
     */
	public Direction getDirection() {
		return direction;
	}
    /**
     * Getter for the destination floor number of the call
     * @return destinationfloornumber
     */
	public int getDestinationfloornumber() {
		return destinationfloornumber;
	}
	
	/**
	 * Returns the informatioon about the elevator in a string 
	 * @return strElevatorInfo
	 */
	@Override
	public String toString() {
		return time + " " + currentfloornumber + " " + direction + " " + destinationfloornumber + " ARRIVED";
	}

}