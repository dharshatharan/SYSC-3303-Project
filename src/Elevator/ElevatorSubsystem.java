package Elevator;

import java.util.*;

import Constants.Direction;

/**
 * receives and sends task to given elevators after the information is paresed fomr the scheduler
 * @author Quinn Sondermeyer
 * @version 03/13/2021
 */
public class ElevatorSubsystem implements Runnable{	
	private Map<String, Elevator> elevators;
	private int numElevators;
	
	private Map<String, List<ElevatorJob>> jobs;
	
	private List<ElevatorInfo> notifyElevatorInfoList;
	
	//private ArrayList<Thread> elevatorThreads;
	
	private ElevatorSchedulerComminicator comunicator;
	
	public ElevatorSubsystem(int numElevators) {
		this.numElevators = numElevators;
		elevators = new HashMap<String,Elevator>();
		comunicator = new ElevatorSchedulerComminicator(this);
		this.jobs = Collections.synchronizedMap(new HashMap<String, List<ElevatorJob>>());
		for (int i = 0; i < numElevators; i++) {
			LinkedList<ElevatorJob> jobList = new LinkedList<ElevatorJob>();
			this.jobs.put(String.valueOf(i + 1), jobList);
		}
		notifyElevatorInfoList = Collections.synchronizedList(new LinkedList<ElevatorInfo>());
		this.run();
		
	}
	
	
	
	/**
	 * sends the new job to the given elevator
	 * @param elevatorID
	 * @param job
	 */
	public void receiveJob(String elevatorID, ElevatorJob job) {
		elevators.get(elevatorID).updateJob(job);
	}



	/**
	 * creates the elevators calsses based on the number of given eleveators and starts the loop to look for jobs 
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for (int i =0; i<numElevators; i++) {
			Elevator temp = new Elevator(this, i + 1 + "");
			Thread elevatorThread = new Thread(temp, "ElevatorThread-" +(i+1));
			elevatorThread.start();
			
			elevators.put(i + 1 +"",temp);
		}
		StartJobs sendJob = new StartJobs(this);
		Thread startJob = new Thread(sendJob, "Start Job");
		startJob.start();
		
		Thread receiveElevatorJobThread = new Thread(){
			   public void run(){
				   while(true) {
					   comunicator.recieveElevatorJob();
				   }
			   }
		   };
		   
		   Thread sendElevatorInfoThread = new Thread(){
			   public void run(){
				   while(true) {
					   comunicator.sendElevatorInfo();
				   }
			   }
		   };
		   
		   receiveElevatorJobThread.start();
		   sendElevatorInfoThread.start();
	}
	
	public void addJob(ElevatorJob job) {
		jobs.get(job.getElevatorID()).add(job);
	}
	
	
	/**
	 * Getter for the list of Jobs
	 * @return jobs
	 */
	public Map<String, List<ElevatorJob>> getJobList(){
		return jobs;
	}
	
	/**
	 * Getter for the list of elevators
	 * @return elevators
	 */
	public  Map<String, Elevator> getElevators(){
		return elevators;
	}
	
	public ElevatorSchedulerComminicator getElevatorSchedulerComminicator() {
		return getElevatorSchedulerComminicator();
	}
	
	public ElevatorInfo getNextElevatorInfo() {
		synchronized (notifyElevatorInfoList) {
			while (notifyElevatorInfoList.isEmpty()) {
				try {
					notifyElevatorInfoList.wait();
	            } catch (InterruptedException e)  {
	                Thread.currentThread().interrupt(); 
	            }
			}
			ElevatorInfo nextInfo = notifyElevatorInfoList.remove(0);
			notifyElevatorInfoList.notify();
			return nextInfo;
		}
	}
	
	public void addElevatorInfoList(ElevatorInfo info) {
		synchronized (notifyElevatorInfoList) {
			notifyElevatorInfoList.add(info);
			notifyElevatorInfoList.notify();
		}
	}
	
	
	public static void main(String[] args) {
		new ElevatorSubsystem(2);
	}
	
	
}