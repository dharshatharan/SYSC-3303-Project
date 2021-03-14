package Scheduler;

import Elevator.ElevatorInfo;
import Floor.RequestElevatorEvent;

/**
 * Receives input from the floors and the elevator(s), then sends information to the aprorpiate thread and waits for more information to come in
 * 
 * @author Colin
 * @version 02/06/2021
 */

public class Scheduler implements Runnable {
	private RequestElevatorEvent activeJob;
	private ElevatorInfo activeInfo;
	private RequestElevatorEvent jobRequest;
	private SchedulerState state;
	private Fault fault;
	
	/**
	 * Default constructor
	 */
	public Scheduler() {
		this.state = new RecieveRequestsAndFaults(this);
	}
	
	/**
	 * Checks for fault
	 * @return True if there is a fault
	 */
	public synchronized boolean faultExists() {
		return fault != null;
	}
	
	/**
	 * Checks for Job
	 * @return True if there is a Job
	 */
	public synchronized boolean jobRequestExists() {
		return jobRequest != null;
	}
		
	/**
	 * sets the state of the scheduler
	 * @param state
	 */
	public void setState(SchedulerState state) {
		this.state = state;
		state.enter();
	}
	
	/**
	 *  Sends information for the floorsubsystem
	 */
	public synchronized void reportFault(Fault fault) {
		while (this.fault != null) {
            try { 
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt(); 
            }
        }
		this.fault = fault;
        notifyAll();
	}
	
	/**
	 * Gets a fault;
	 */
	public synchronized Fault getFault() {
		while (this.fault == null){
			try {
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt(); 
            }
		}
		Fault nextFault = this.fault;
		this.fault = null;
		notifyAll();
		return nextFault;
	}
	
	/**
	 *  Sends information for the floorsubsystem
	 */
	public synchronized void requestElevator(RequestElevatorEvent job) {
		while (jobRequest != null) {
            try { 
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt(); 
            }
        }

		jobRequest = job;
        notifyAll();
	}
	
	/**
	 * Waits for elevator to notify that it has arrived at the floor. sets the next job for the floor and clears the task
	 */
	public synchronized RequestElevatorEvent getJobRequest() {
		while (jobRequest == null){
			try {
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt(); 
            }
		}
		RequestElevatorEvent nextJob = jobRequest;
		jobRequest = null;
		notifyAll();
		return nextJob;
	}
	
	/**
	 *  Sends information for the floorsubsystem
	 */
	public synchronized void putNextJob(RequestElevatorEvent job) {
		while (activeJob != null) {
            try { 
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt(); 
            }
        }
		activeJob = job;
        notifyAll();
	}
	
	/**
	 * Waits for elevator to notify that it has arrived at the floor. sets the next job for the floor and clears the task
	 */
	public synchronized RequestElevatorEvent getNextJob() {
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
        notifyAll();
	}
	
	/**
	 * Waits until the information from the floor is updated. the scheduler creates next elevator job and clears the input data.
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
	 * Runs the thread to start the scheduling
	 * currently only prints that the system has sated
	 */
	@Override
	public void run() {
		System.out.println("Starting floor scheduler");
		while (true) {
			state.enter();
		}
		
	}

}
