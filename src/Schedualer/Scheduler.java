package Schedualer;
import Elevator.Elevator;
import Constants.ArrivalSensor;
import Shared.*;

import java.util.*;

/**
 * 
 * @author 
 * @version 02/06/2021
 */

public class Scheduler implements Runnable, RequestElevatorEvent{
	List<Elevator> elevators;
	List<ArrivalSensor> arrivalSensors;
	List<Elevator> idelElevators;
	
	public Scheduler(List<Elevator> elevators,List<ArrivalSensor> arrivalSensors) {
		this.elevators = elevators;
		this.arrivalSensors = arrivalSensors;
	}
	

	
	public void notifyElevators() {
		for (Elevator e : elevators) {
			//TODO check if elevator Idle then assign task
			ElevatorJob job = new ElevatorJob()
		}
	}
	
	public void handleRequestElevatorsEvent() {
		for (Elevator e : elevators) {
			//TODO check if elevator Idle then assign task
			ElevatorJob job = new ElevatorJob()
		}
	}
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public static void main(String[] args) {

    }

}
