package Floor;
import java.lang.String;
import Constants.Direction;

public class RequestElevatorEvent {

    private String time;
    private int currentfloornumber;
    private Direction direction;
    private int destinationfloornumber;
    private int secondsSinceMidnight;

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

	public String getTime() {
		return time;
	}

	public int getCurrentfloornumber() {
		return currentfloornumber;
	}

	public Direction getDirection() {
		return direction;
	}

	public int getDestinationfloornumber() {
		return destinationfloornumber;
	}
	
	public int getSecondsSinceMidnight() {
		return secondsSinceMidnight;
	}
	
	@Override
	public String toString() {
		return time + " " + currentfloornumber + " " + direction + " " + destinationfloornumber;
	}

}