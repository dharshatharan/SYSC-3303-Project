package Elevator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Constants.Direction;
import Floor.RequestElevatorEvent;
import Scheduler.Scheduler;

/**
 * An elevator that cycles through states completing Jobs
 * 
 * @author Darsh, Quinn
 * @version 03/16/2021
 */
public class Elevator extends Thread {
	private String id;
	private int currentFloor;
	private ElevatorSubsystem elevatorSubsystem;
	private String timer;
    private Direction direction;
    private int curFlor, destination;
    private ElevatorState state;
    
    private ElevatorJob preJob;
    private ElevatorJob job;
    
    private List<ElevatorJob> jobs;
    private List<ElevatorJob> activeJobs;
    
    private int nextDestinationFloor;
	
	/**
	 * Default constructor
	 * @param scheduler
	 */
	public Elevator(ElevatorSubsystem elevatorSubsystem, String id) {
		this.id = id;
		this.elevatorSubsystem = elevatorSubsystem;
		this.curFlor = 1;
		this.state = new Idle(this);
		this.jobs = Collections.synchronizedList(new LinkedList<ElevatorJob>());
		this.activeJobs = Collections.synchronizedList(new LinkedList<ElevatorJob>());
	}
	
	/**
	 * Runs the elevator pram constantly requesting  then exicuting jobs
	 */
	@Override
	public void run() {
//		ElevatorJob request = null;
		System.out.println("Starting floor elevator");
		while(!isInterrupted()) {
			synchronized (jobs) {
				synchronized(activeJobs) {
					 try {
						 while (jobs.isEmpty() && activeJobs.isEmpty())
							 jobs.wait();
					 } catch (InterruptedException e) {
						 System.out.println(e);
						 return;
					 }
//					 request = jobs.get(0);
					 nextDestinationFloor = getNextBestElevatorDestination();
				}
			 }
//			processInfo(request);
			state = new Moving(this);
			state.enter();
		}
//            state.enter();
	}
	
	public int getNextBestElevatorDestination() {
		synchronized (jobs) {
			ElevatorJob bestJob = jobs.get(0);
			for(ElevatorJob job: jobs) {
				if(job.getDirectionSeeking() == direction) {
					if(job.getDirectionSeeking() == Direction.UP && job.getFromFloor() > curFlor && job.getFromFloor() < bestJob.getFromFloor()) {
						
					}
				}
			}
		}
	}

	/**
	 * Simulates closing Doors
	 */
	public void closeDoors() {
		System.out.println("Doors Closing");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("Doors Closed");
	}
	
	/**
	 * Simulates closing Doors
	 */
	public void openDoors() {
		System.out.println("Doors Opening");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("Doors opened");
	}
	
	
	public void updateJob(ElevatorJob job) {
		this.job = job;
	}
	
	
	
	/**
	 * Getters and seeters for terms in elevator below
	 */
	public String getTimer(){
        return timer;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getCurFloor(){
        return curFlor;
    }

    public int getDestination() {
        return destination;
    }
    
    public ElevatorJob getJob() {
        return job;
    }
    
    public ElevatorJob getPreJob() {
        return preJob;
    }
    
    public ElevatorState getState() {
        return state;
    }
    
    public ElevatorSubsystem getElevatorSubsystem() {
    	return elevatorSubsystem;
    }
    
    public void setJob(ElevatorJob job) {
        this.job = job;
    }
    
    public void setPreJob(ElevatorJob job) {
        this.preJob = job;
    }

    public void setTimer(String timer){
        this.timer = timer;
    }

    public void setDirection(Direction direction) {
        this.direction =  direction;
    }

    public void setcurFlor(int curFlor){
        this.curFlor =  curFlor;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    } 
    
    public void setFloor(int floorNumber){
    	this.curFlor = floorNumber;
    }
    
    
    public void setState(ElevatorState state) {
    	this.state = state;
    }

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
    
}