package Floor;
import java.lang.String;
import Constants.Direction;

//A class that takes info from the text info and allows the info to be read by scheduling
public class RequestElevatorEvent {

    private String time;
    private int currentfloornumber;
    private Direction direction;
    private int destinationfloornumber;

	//Constructor for RequestElevatorEvent class
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
	
	//Returns time
	public String getTime() {
		return time;
	}

	//Returns current floor number
	public int getCurrentfloornumber() {
		return currentfloornumber;
	}

	//Returns current direction of the elevator
	public Direction getDirection() {
		return direction;
	}
	
	
	//Pulls the destination floor number
	public int getDestinationfloornumber() {
		return destinationfloornumber;
	}
	
	//Creates a string and returns it with the elevator event information encoded.
	@Override
	public String toString() {
		return time + " " + currentfloornumber + " " + direction + " " + destinationfloornumber;
	}

}
