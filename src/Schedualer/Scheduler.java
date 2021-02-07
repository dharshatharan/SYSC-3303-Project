package Schedualer;
import Elevator.Elevator;
import Elevator.ElevatorJob;
import Constants.ArrivalSensor;
import Constants.Direction;
import Constants.FloorNumber;
import Shared.*;

import java.util.*;

/**
 * This class is in charge of Receiving information from the parser and sending information to the floor asnd the Elevator
 * 
 * @author Quinn Sondermeyer
 * @version 02/06/2021
 */

public class Scheduler implements Runnable, RequestElevatorEvent, RequestFloorEvent{
	List<Elevator> elevators;
	List<ArrivalSensor> arrivalSensors;
	List<Elevator> idelElevators;
	List<ElevatorJob> elevatorsJobs;
	
	/**
	 * Default constructor
	 * @param elevators
	 * @param arrivalSensors
	 */
	public Scheduler(List<Elevator> elevators,List<ArrivalSensor> arrivalSensors) {
		this.elevators = elevators;
		this.arrivalSensors = arrivalSensors;
		this.elevatorsJobs = new ArrayList<ElevatorJob>();
	}
	

	/**
	 * Notify elevators of the current task
	 * @param job
	 */
	public void notifyElevators(ElevatorJob job) {
		for (Elevator e : elevators) {
			//TODO check if elevator Idle then assign task
			
		}
	}
	
	

	
	private void checkForJob() {
		if (elevatorsJobs.size() > 0) {
			for (ElevatorJob job : elevatorsJobs) {
				if (job.getElevator() == null) {
					elevators.get(0).addJob(job);
					elevatorsJobs.remove(job);
				}
				
			}
		}
		
	}
	
	
	
	@Override
	public void run() {
		boolean go = true;
		while(go) {
			checkForJob();
			
			
		}
		
	}
	
	/**
	 * Handle the event when a floor request an elevator
	 * @param time
	 * @param floorNum
	 * @param direction
	 */
	@Override
	public void handelRequestElevatorEvent(String time, FloorNumber floorNum, Direction direction) {
		this.elevatorsJobs.add(new ElevatorJob(floorNum,direction,null));
	}


	/**
	 * Handle the event when a floor request an elevator
	 * @param time
	 * @param floorNum
	 * @param elevator
	 */
	@Override
	public void handelRequestFloorEvent(String time, FloorNumber floorNum, Elevator elevator) {
		this.elevatorsJobs.add(new EventJob(floorNum,direction,null));
		
	}
	
	


	public static void main(String[] args) {
		

    }





}
