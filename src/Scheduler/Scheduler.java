package Scheduler;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import Constants.Direction;
import Elevator.ElevatorInfo;
import Elevator.ElevatorJob;
import Floor.RequestElevatorEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Receives input from the floors and the elevator(s), then sends information to the aprorpiate thread and waits for more information to come in
 * 
 * @author Darsh
 * @version 02/06/2021
 */

public class Scheduler implements Runnable {
	private final int NO_OF_ELEVATORS = 4;
	
	private Map<String, List<ElevatorJob>> elevatorJobDatabase;
	private Map<String, ElevatorInfo> elevatorInfoDatabase;
	
	private ProcessJobRequestsThread processJobRequestsThread;
	private ProcessElevatorInfoThread processElevatorInfoThread;
	private SchedulerElevatorCommunicator schedulerElevatorCommunicator;
	private SchedulerFloorCommunicator schedulerFloorCommunicator;
	private Fault fault;
	private Map<String, HashMap<String, ElevatorTimer>> ElevatorFalutTimers;
	private LocalDateTime startTime;
	
	/**
	 * Default constructor
	 */	
	public Scheduler() {
		this.processJobRequestsThread = new ProcessJobRequestsThread(this);
		this.processElevatorInfoThread = new ProcessElevatorInfoThread(this);
		this.schedulerElevatorCommunicator = new SchedulerElevatorCommunicator(this);
		this.schedulerFloorCommunicator = new SchedulerFloorCommunicator(this);
		
		this.elevatorJobDatabase = Collections.synchronizedMap(new HashMap<String, List<ElevatorJob>>());
		this.elevatorInfoDatabase = Collections.synchronizedMap(new HashMap<String, ElevatorInfo>());
		this.ElevatorFalutTimers = Collections.synchronizedMap(new HashMap<String, HashMap<String, ElevatorTimer>>());
		for (int i = 0; i < NO_OF_ELEVATORS; i++) {
			LinkedList<ElevatorJob> jobList = new LinkedList<ElevatorJob>();
			HashMap<String, ElevatorTimer> timerList = new HashMap<String,ElevatorTimer>();
			this.elevatorJobDatabase.put(String.valueOf(i + 1), jobList);
			this.elevatorInfoDatabase.put(String.valueOf(i + 1), new ElevatorInfo(true, String.valueOf(i + 1), 1, Direction.Idle, 1));
			this.ElevatorFalutTimers.put(String.valueOf(i + 1), timerList);
		}
	}
	/**
	 * Checks for fault
	 * @return True if there is a fault
	 */	
	public synchronized boolean faultExists() {
		return fault != null;
	}
	
	public LocalDateTime getStartTime() {
		return startTime;
	}
	
	public LocalDateTime setStartTime() {
		startTime = LocalDateTime.now();
		return startTime;
	}

	public Map<String, List<ElevatorJob>> getElevatorJobDatabase() {
		return elevatorJobDatabase;
	}
	
	public Map<String, ElevatorInfo> getElevatorInfoDatabase() {
		return elevatorInfoDatabase;
	}
	
	public void addJobToElevatorQueue(String elevatorID, ElevatorJob job) {
		synchronized (elevatorJobDatabase) {
			if (elevatorJobDatabase.containsKey(elevatorID)) {
				elevatorJobDatabase.get(elevatorID).add(job);
			}
		}
	}
	
	public ElevatorJob getNextJobFromElevatorQueue(String elevatorID) {
		synchronized (elevatorJobDatabase) {
			if (elevatorJobDatabase.containsKey(elevatorID)) {
				while (elevatorJobDatabase.get(elevatorID).isEmpty()){
					try {
		                wait();
		            } catch (InterruptedException e)  {
		                Thread.currentThread().interrupt(); 
		            }
				}
				ElevatorJob nextJob = elevatorJobDatabase.get(elevatorID).remove(0);
				notifyAll();
				return nextJob;
			} else {
				return null;
			}
		}
	}
	
	public void updateElevatorInfo(String elevatorID, ElevatorInfo info) {
		synchronized (elevatorInfoDatabase) {
			if (elevatorInfoDatabase.containsKey(elevatorID)) {
				elevatorInfoDatabase.put(elevatorID, info);
			}
		}
	}
	
	public ElevatorInfo getInfoForElevator(String elevatorID) {
		synchronized (elevatorInfoDatabase) {
			if (elevatorInfoDatabase.containsKey(elevatorID)) {
				return elevatorInfoDatabase.get(elevatorID);
			} else {
				return null;
			}
		}
	}
	
	public ProcessJobRequestsThread getProcessJobThread() {
		return processJobRequestsThread;
	}
	
	/**
	 *  Sends information for the floorsubsystem
	 */
	public synchronized void addElevatorRequest(RequestElevatorEvent job) {
		processJobRequestsThread.enqueueJobRequest(job);
	}
	
	/**
	 * Waits for elevator to notify that it has arrived at the floor. sets the next job for the floor and clears the task
	 */
	public ElevatorJob getNextReadyJob() {
		return processJobRequestsThread.dequeueReadyJob();
	}
	
	/**
	 * Adds elevator info to the queue that will be sent to the floor
	 * @param job
	 */
	public void addElevatorInfo(ElevatorInfo info) {
		processElevatorInfoThread.enqueueInfo(info);
	}
	
	/**
	 * Gets the next elevator info from the queue that will be sent to the floor
	 */
	public ElevatorInfo getNextElevatorInfo() {
		return processElevatorInfoThread.dequeueProcessedInfo();
	}
	
	/**
	 * Runs the thread to start the scheduling
	 * currently only prints that the system has sated
	 */
	@Override
	public void run() {
		System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": Starting floor scheduler");
		processJobRequestsThread.start();
		processElevatorInfoThread.start();
		
		Thread recieveElevatorRequestThread = new Thread(){
		   public void run(){
			   while(true) {
			   		schedulerFloorCommunicator.recieveElevatorRequest();
			   }
		   }
	   };
	   Thread receiveElevatorInfoThread = new Thread(){
		   public void run(){
			   while(true) {
			   		schedulerFloorCommunicator.receiveElevatorInfoRequest();
			   }
		   }
	   };
	   Thread recieveElevatorInfoThread = new Thread(){
		   public void run(){
			   while(true) {
			   		schedulerElevatorCommunicator.recieveElevatorInfo();
			   }
		   }
	   };
	   Thread receiveElevatorJobRequestThread = new Thread(){
		   public void run(){
			   while(true) {
				   schedulerElevatorCommunicator.receiveElevatorJobRequest();
			   }
		   }
	   };
	   
	   recieveElevatorRequestThread.start();
	   receiveElevatorInfoThread.start();
	   recieveElevatorInfoThread.start();
	   receiveElevatorJobRequestThread.start();
	   
	}
		
	/**
	 * Create and Start timmer
	 * @param scheduler
	 * @param elevatorID
	 * @param faultType
	 * @param seconds
	 */
	public void startTimer(Scheduler scheduler, String elevatorID,Fault faultType, int seconds) {
		ElevatorFalutTimers.get(elevatorID).put(faultType.getFault(), new ElevatorTimer(scheduler, elevatorID, faultType));
		ElevatorFalutTimers.get(elevatorID).get(faultType.getFault()).startTimer(seconds);
	}
	
	/**
	 * Getter for Faults list
	 * @return
	 */
	public Map<String, HashMap<String, ElevatorTimer>> getFaults(){
		return ElevatorFalutTimers;
	}
	
	public static void main(String[] args) {
		Scheduler s = new Scheduler();
		Thread schedularThread = new Thread(s, "SchedularThread");
		schedularThread.start();
	}

}
