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
	
	//private ArrayList<Thread> elevatorThreads;
	
	//ElevatorSchedulerCommunicator comunicator;
	
	public ElevatorSubsystem(int numElevators) {
		this.numElevators = numElevators;
		elevators = new HashMap<String,Elevator>();
		//elevatorThreads = new ArrayList<Thread>();
		//comunicator = new ElevatorSchedulerCommunicato();
		
	}
	
	
	
	/**
	 * sends the new job to the given elevator
	 * @param elevatorID
	 * @param job
	 */
	public void receiveJob(int elevatorID, ElevatorJob job) {
		elevators.get(elevatorID).updateJob(job);
	}



	/**
	 * creates the elevators calsses based on the number of given eleveators and starts the loop to look for jobs 
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for (int i =0; i<numElevators; i++) {
			Elevator temp = new Elevator();
			Thread elevatorThread = new Thread(temp, "ElevatorThread-" +(i+1));
			elevatorThread.start();
			
			elevators.put(i+"",temp);
			
//			elevatorThreads.add(elevatorThread);
		}
		
		
		
		while(true) {
			
			try {
				//comunicator.requestJob();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (comunicator.){//TODO if there is a job run following
				receiveJob();
				jobs.put(null, null);
				
			}
		}
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
	
	
	
	
	
}
