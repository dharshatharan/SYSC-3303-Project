package Elevator;
import java.lang.String;
import Constants.Direction;

public class ElevatorInfo {

    private String time;
    private int currentfloornumber;
    private Direction direction;
    private int destinationfloornumber;

    public ElevatorInfo(String time, int currentFloor, Direction direction, int destinationFloor) {
        this.time = time;
        this.currentfloornumber = currentFloor;
        this.direction = direction;
        this.destinationfloornumber = destinationFloor;
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
		return time + " " + currentfloornumber + " " + direction + " " + destinationfloornumber + " ARRIVED";
	}

}