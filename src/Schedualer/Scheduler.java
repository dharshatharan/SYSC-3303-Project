package Schedualer;

import Elevator.Elevator;
import Elevator.ElevatorInfo;
import Floor.FloorSubsystem;
import Floor.RequestElevatorEvent;

/**
 * Receives input from the floors and the elevator(s), then sends information to the aprorpiate thread and waits for more information to come in
 * 
 * @author Darsh
 * @version 02/06/2021
 */

public class Scheduler implements Runnable {
	private RequestElevatorEvent activeJob;
	private ElevatorInfo activeInfo;
//	private Elevator elevator;
//	private FloorSubsystem floorSubsystem;

//	public Scheduler(Elevator elevator, FloorSubsystem floorSub) {
//		this.elevator = elevator;
//		this.floorSubsystem = floorSub;
//	}
	
	/**
	 * ??
	 */
	public synchronized void requestElevator(RequestElevatorEvent job) {
		while (activeJob != null) {
            try { 
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt(); 
            }
        }
		activeJob = job;
//        System.out.println(ingredients + " placed on the table by the agent");
        notifyAll();
	}
	
	/**
	 * Waits for elevator to notify that it has arived at the floor. sets the next job for the floor and clears the task
	 */
	public synchronized RequestElevatorEvent getJob() {
		while (activeJob == null){
			try {
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt(); 
            }
		}
		RequestElevatorEvent nextJob = activeJob;
		activeJob = null;
		notifyAll();
		return nextJob;
	}
	
	/**
	 * Sends the information the schelduler recived from the floorsubsystem
	 * @param job
	 */
	public synchronized void sendElevatorInfo(ElevatorInfo job) {
		while (activeInfo != null) {
            try { 
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt(); 
            }
        }
		activeInfo = job;
//        System.out.println(ingredients + " placed on the table by the agent");
        notifyAll();
	}
	/**
	 * Waits until the infoarmtion from the floor is updated. the schedualer creates next elevator job and clears the input data.
	 */
	public synchronized ElevatorInfo getElevatorInfo() {
		while (activeInfo == null){
			try {
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt(); 
            }
		}
		ElevatorInfo nextJob = activeInfo;
		activeInfo = null;
		notifyAll();
		return nextJob;
	}
	
	/**
	 * Runs the thread to start the sheduling
	 * currently only prints that the system has sated
	 */
	@Override
	public void run() {
		System.out.println("Starting floor scheduler");
		
	}
	
//	public static void main(String[] args) {
//		
//    }

}
