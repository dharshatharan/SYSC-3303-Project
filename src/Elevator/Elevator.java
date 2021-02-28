package Elevator;

import Constants.Direction;
import Floor.RequestElevatorEvent;
import Scheduler.Scheduler;

/**
 * An elevator that cycles through states Requesting and completing Jobs (notifies Scheduler when the job is complete)
 * 
 * @author Quinn
 * @version 02/27/2021
 */
public class Elevator implements Runnable {
	
	private Scheduler scheduler;
	private RequestElevatorEvent preJob;
	private RequestElevatorEvent job;
	
	private String timer;
    private Direction direction;
    private int curFlor, destination;
    private ElevatorState state;
	
	/**
	 * Default constructor
	 * @param scheduler
	 */
	public Elevator(Scheduler scheduler) {
		this.curFlor = 1;
		this.scheduler = scheduler;
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
	 * Getters and seeters for terms in elevator below
	 */
	
	
	public Scheduler getScheduler() {
		return scheduler;
		
	}
	
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
    
    public RequestElevatorEvent getJob() {
        return job;
    }
    
    public RequestElevatorEvent getPreJob() {
        return preJob;
    }
    
    public ElevatorState getState() {
        return state;
    }
    
    public void setJob(RequestElevatorEvent job) {
        this.job = job;
    }
    
    public void setPreJob(RequestElevatorEvent job) {
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
