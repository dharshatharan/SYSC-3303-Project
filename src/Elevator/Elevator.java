package Elevator;

import java.util.*;
import Constants.*;

/**
 * 
 * @author 
 * @version 02/06/2021
 */
public class Elevator implements Runnable{
	List<ElevatorJob> elevatorJobs;
	Direction direction;
	ElevatorState state;
	
	public Elevator() {
		elevatorJobs = new ArrayList<ElevatorJob>();
		state = ElevatorState.IDLE;
		direction = null;
		
	}
	
	public void addJob(ElevatorJob job) {
		elevatorJobs.add(job);
		
	}
	
	
	
	
	private void checkForJobs()  {
		
		
	}
	
	@Override
	public void run() {
		boolean go = true;
		while(go) {
			checkForJobs();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}



	

}
