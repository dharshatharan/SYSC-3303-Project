package Floor;
import java.lang.String;
import Constants.Direction;

public class RequestElevatorEvent {

    private String time;
    private int currentfloornumber;
    private Direction direction;
    private int destinationfloornumber;

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
	
	@Override
	public String toString() {
		return time + " " + currentfloornumber + " " + direction + " " + destinationfloornumber;
	}

}