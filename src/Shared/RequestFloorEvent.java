package Shared;
import Constants.*;
import Elevator.Elevator;

/**
 * 
 * @author 
 * @version 02/06/2021
 */
public interface RequestFloorEvent {

	void handelRequestFloorEvent(String time,FloorNumber floorNum, Elevator elevator);
}
