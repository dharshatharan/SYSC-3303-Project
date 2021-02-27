package Floor;
import java.lang.String;
import Constants.Direction;

/**
 * Stores the informatoin about each Elevator call for the schedulaer to use
 * @author Alexander
 * @version 02/06/21
 *
 */
public class RequestElevatorEvent {

    private String time;
    private int currentfloornumber;
    private Direction direction;
    private int destinationfloornumber;

    /**
     * Default constructor
     * @param job
     */
    public RequestElevatorEvent(String job) {
        String inputs[] = job.split(" ");

        this.time = inputs[0];

        this.currentfloornumber = Integer.parseInt(inputs[1]);
        
        if(inputs[2].equals("Up")){
            this.direction = Direction.UP;
        } else {
            this.direction = Direction.DOWN;
        }
        
        this.destinationfloornumber = Integer.parseInt(inputs[3]);
    }

    /**
     * Getter for the time
     * @return time
     */
	public String getTime() {
		return time;
	}

    /**
     * Getter for the currentfloornumber
     * @return currentfloornumber
     */
	public int getCurrentfloornumber() {
		return currentfloornumber;
	}

    /**
     * Getter for the direction
     * @return direction
     */
	public Direction getDirection() {
		return direction;
	}

    /**
     * Getter for the destinationfloornumber
     * @return destinationfloornumber
     */
	public int getDestinationfloornumber() {
		return destinationfloornumber;
	}
	
	/**
	 * Returns the string of information to be printed
	 * @return ElevatorInfromation
	 */
	@Override 
	public String toString() {
		return time + " " + currentfloornumber + " " + direction + " " + destinationfloornumber;
	}

}
