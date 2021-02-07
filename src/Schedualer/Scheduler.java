package Schedualer;

import Elevator.Elevator;
import Elevator.ElevatorInfo;
import Floor.FloorSubsystem;
import Floor.RequestElevatorEvent;

/**
 * 
 * @author 
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
	
	@Override
	public void run() {
		System.out.println("Starting floor scheduler");
		
	}
	
//	public static void main(String[] args) {
//		
//    }

}
