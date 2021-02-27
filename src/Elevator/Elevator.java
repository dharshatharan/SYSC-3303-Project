package Elevator;

import Schedualer.Scheduler;
import Constants.Direction;
import Floor.RequestElevatorEvent;

/**
 * 
 * @author Darsh
 * @version 02/06/2021
 */
public class Elevator implements Runnable {
	
	private Scheduler scheduler;
	
	private String timer;
    private Direction direction;
    private int curFlor, destination;
    private ElevatorState state;
	
	/**
	 * Default constructor
	 * @param scheduler
	 */
	public Elevator(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
	
	/**
	 * Runs the elevator pram constantly requesting  then exicuting jobs
	 */
	@Override
	public void run() {
		System.out.println("Starting floor elevator");
		while(true) {
			RequestElevatorEvent job = scheduler.getJob();
			System.out.println(Thread.currentThread() + " is serving job " + job.toString());
			scheduler.sendElevatorInfo(new ElevatorInfo(job.getTime(), job.getCurrentfloornumber(), job.getDirection(), job.getDestinationfloornumber()));timer = job.getTime();
		    direction =  job.getDirection();
		    curFlor =  job.getCurrentfloornumber();
		    destination = job.getDestinationfloornumber();
		}
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
    
    public RequestElevatorEvent getJob() {
        return scheduler.getJob();
    }
    
    public ElevatorState getState() {
        return state;
    }
    
    public void setDestination(int destination){
    	this.destination = destination;
    }
    
    public void setDirection(Direction direction){
    	this.direction = direction;
    }
    
    public void setFloor(int floorNumber){
    	curFlor = floorNumber;
    }
    
    public void setState(ElevatorState state) {
    	this.state = state;
    	state.enter();
    }
    
    
    

    
    
    
}
