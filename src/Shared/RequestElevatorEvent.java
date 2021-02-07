package Shared;
import Constants.*;
import Elevator.Elevator;

/**
 * 
 * @author 
 * @version 02/06/2021
 */
public interface RequestElevatorEvent {

	void handelRequestElevatorEvent(String time,FloorNumber floorNum ,Direction direction);
}
