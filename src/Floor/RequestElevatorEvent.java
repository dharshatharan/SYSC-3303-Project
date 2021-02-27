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
    private int secondsSinceMidnight;

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
        
        String time[] = inputs[0].split(":");
        
        int hrs = Integer.parseInt(time[0]);
        int minutes = Integer.parseInt(time[1]);
        int secs = Integer.parseInt(time[2]);
        
        this.secondsSinceMidnight = hrs*3600 + minutes*60 + secs;
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
     * Getter for the SecondsSinceMidnight
     * @return SecondsSinceMidnight
     */	
	public int getSecondsSinceMidnight() {
		return secondsSinceMidnight;
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
