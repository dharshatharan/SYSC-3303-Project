package Elevator;
import Constants.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Floor.RequestElevatorEvent;
import java.util.Date;
import java.util.List;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A state of the elevator for when it is stoped at a floor. Opens and closes the doors, changes to move or idle
 * 
 * @version 2/27/2021
 * @author Quinn Sondermeyer
 *
 */
public class Stopped extends ElevatorState{
	
	/**
	 * Default Constructor for the Stopped state
	 * @param elevator
	 */
	public Stopped(Elevator elevator) {
		super(elevator);
	}

	Direction d = Direction.UP;
	Date date;

	
	/**
	 * Simulates arrival and opens door. Runs set task, wait, closedoor
	 */
	public void openDoors() {
		System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": Elevator " + elevator.getElevatorId() + " Doors opening...");
		Timer timer = new Timer(true);
		TimerTask openTask = new TimerTask() {
	        public void run() {
	            if(elevator.getDoorState() != DoorStatus.open) {
	            	System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": Failed to open doors, retrying...");
	            	retryOpen();
	            }
	        }
	    };
		
		timer.schedule(openTask, 1100);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		List<ElevatorJob> removeJobs = elevator.startFinishAllJobsInCurFloor();
		for(ElevatorJob job: removeJobs) {
			if (job.getFault() == 1) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		elevator.setDoorState(true);
		
	}
	
	private void retryOpen() {
		elevator.setDoorState(false);
		for(int i=0; i < 2; i++) {
			System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": Retrying to open door: " + (i + 1) + " time");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Shutting down elevator " + elevator.getElevatorId() + " due to door fault>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		elevator.getElevatorSubsystem().addElevatorInfoList(new ElevatorInfo(true, elevator.getElevatorId(), elevator.getCurrentFloor(), elevator.getDirection(), 2));
		elevator.setOperationalStatus(false);
		elevator.interrupt();
	}
	
	private void retryClose() {
		for(int i=0; i < 2; i++) {
			System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": Retrying to close door: " + i + 1 + " time");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("Shutting down elevator " + elevator.getElevatorId());
		// communicate shutdown
	}
	
	/**
	 * Simulates the closing doors. starts to process to change state to idel or moving
	 */
	public void closeDoors() {
		System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": Elevator " + elevator.getElevatorId() + " Doors closing...");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		elevator.setDoorState(false);
		
	}
	
	/**
	 * Sets state to Move when there are jobs
	 */
	public void moving() {
		//elevator.changeState();
		elevator.setState(new Moving(elevator));
		exit();
	}
	


	/**
	 * Exits State of being stoped at the floor
	 */
	@Override
	public void exit() {

	}

	/**
	 * Starts task for Stopped state, notifies scheduler that the elevator has arrived
	 */
	@Override
	public void enter() {
		System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": ---------------------Elevator State changed to: STOPPED-STATE---------------------");
		notifyElevatorArrival();
		 openDoors();
		 if (elevator.getDoorState() == DoorStatus.open) closeDoors();

		exit();
			
	}

	/**
	 * Notifies The Scheduler that the elevator has completed a job or moving the elevator to the floor of the first job
	 */
	private void notifyElevatorArrival() {
		elevator.getElevatorSubsystem().addElevatorInfoList(new ElevatorInfo(true, elevator.getElevatorId(), elevator.getCurrentFloor(), elevator.getDirection(), 1));
	}
		
}
	
	


