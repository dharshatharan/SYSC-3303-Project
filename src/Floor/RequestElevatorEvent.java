package Floor;
import java.lang.String;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Constants.Direction;

/**
 * Stores the informatoin about each Elevator call for the schedulaer to use
 * @author Alexander
 * @version 02/06/21
 *
 */
public class RequestElevatorEvent {
	private Pattern elevatorRequestPattern = Pattern.compile("^0[1-9] [1-9] [1-2] [1-9] ");

    private String time;
    private int currentfloornumber;
    private Direction direction;
    private int destinationfloornumber;
    private int secondsSinceMidnight;
    private boolean fault;

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
        this.fault = false;
        if(inputs[3] == 2) {
        	this.fault = true;
        }
        
        
        String time[] = inputs[0].split(":");
        
        int hrs = Integer.parseInt(time[0]);
        int minutes = Integer.parseInt(time[1]);
        int secs = Integer.parseInt(time[2]);
        
        this.secondsSinceMidnight = hrs*3600 + minutes*60 + secs;
    }
    
    /**
     * Test constructor
     * @param job
     */
    public RequestElevatorEvent(String time, int currentFloorNumber, Direction direction, int destinationFloorNumber, int secondsSinceMidnight) {
        this.time = time;
        this.direction = direction;
        this.currentfloornumber = currentFloorNumber;
        this.destinationfloornumber = destinationFloorNumber;
        this.secondsSinceMidnight = secondsSinceMidnight;
    }
    
    public RequestElevatorEvent(byte[] data) throws Exception {
    	String s = new String(data);
    	System.out.println(s);
		Matcher matcher = elevatorRequestPattern.matcher(s);
		if (matcher.find()) {
			String[] sa = s.split(" ");
	        this.currentfloornumber = Integer.parseInt(sa[1]);
	    	this.direction = sa[2].equals("1") ? Direction.UP : Direction.DOWN;
	        this.destinationfloornumber = Integer.parseInt(sa[3]);
		} else {
			throw new Exception("Invalid byte array for RequestElevatorEvent!");
		}
    }
	
	public byte[] getByteArray(String byteCode) {
    	String s = byteCode + " " + this.toStringForByteArray();
    	return s.getBytes();
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
	
	public String toStringForByteArray() {
		return  currentfloornumber + " " + (direction == Direction.UP ? "1" : "2") + " " + destinationfloornumber + " ";
	}
	
	
	/**
	 * Returns the string of information to be printed
	 * @return ElevatorInfromation
	 */
	@Override
	public String toString() {
		return time + " " + currentfloornumber + " " + direction + " " + destinationfloornumber;
	}
	
	// Overriding equals() to compare two RequestElevatorEvent objects
    @Override
    public boolean equals(Object o) { 
    	
    	if (o == this) { 
            return true; 
        } 
  
    	if (!(o instanceof RequestElevatorEvent)) { 
            return false; 
        } 
    	
    	RequestElevatorEvent c = (RequestElevatorEvent) o;
    	

    	if (!this.time.equals(c.time)) return false;
    	if (this.currentfloornumber != c.currentfloornumber) return false;
    	if (!this.direction.equals(c.direction)) return false;
    	if (this.destinationfloornumber != c.destinationfloornumber) return false;
    	if (this.secondsSinceMidnight != c.secondsSinceMidnight) return false;
    	
    	return true;
    } 

}
