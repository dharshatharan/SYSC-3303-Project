package Elevator;

import Constants.Direction;
import Floor.RequestElevatorEvent;
import Scheduler.Scheduler;

/**
 * An elevator that cycles through states completing Jobs
 * 
 * @author Darsh, Quinn
 * @version 02/27/2021
 */
public class Elevator implements Runnable {
	
	private int currentFloor;
	
	private String timer;
    private Direction direction;
    private int curFlor, destination;
    private ElevatorState state;
    
    private ElevatorJob preJob;
    private ElevatorJob job;
	
	/**
	 * Default constructor
	 * @param scheduler
	 */
	public Elevator() {
		this.curFlor = 1;
		this.state = new Idle(this);
	}
	
	/**
	 * Runs the elevator pram constantly requesting  then exicuting jobs
	 */
	@Override
	public void run() {
		System.out.println("Starting floor elevator");
		while(true) {
            state.enter();
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
    
}
