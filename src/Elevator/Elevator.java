package Elevator;

import Constants.Direction;
import Floor.RequestElevatorEvent;
import Scheduler.Scheduler;

/**
 * An elevator that cycles through states completing Jobs
 * 
 * @author Darsh
 * @version 02/27/2021
 */
public class Elevator implements Runnable {
	
	private Scheduler scheduler;
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
		this.scheduler = scheduler;
		
	}
	
	/**
	 * Runs the elevator pram constantly requesting  then exicuting jobs
	 */
	@Override
	public void run() {
		System.out.println("Starting floor elevator");
		while(true) {
//			RequestElevatorEvent job = scheduler.getNextJob();
//			System.out.println(Thread.currentThread() + " is serving job " + job.toString());
//			scheduler.sendElevatorInfo(new ElevatorInfo(job.getTime(), job.getCurrentfloornumber(), job.getDirection(), job.getDestinationfloornumber()));timer = job.getTime();
//		    direction =  job.getDirection();
//		    curFlor =  job.getCurrentfloornumber();
//		    destination = job.getDestinationfloornumber();
//		}

            this.state = new Idle(this);
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
    
    public ElevatorState getState() {
        return state;
    }
    
    public void setJob(RequestElevatorEvent job) {
        this.job = job;
    }

    public void setTimer(){
        timer = job.getTime();
    }

    public void setDirection() {
        direction =  job.getDirection();
    }

    public void setcurFlor(){
        curFlor =  job.getCurrentfloornumber();
    }

    public void setDestination() {
        destination = job.getDestinationfloornumber();
    } 
    
    public void setFloor(int floorNumber){
    	curFlor = floorNumber;
    }
    
    
    public void setState(ElevatorState state) {
    	this.state = state;
    	state.enter();
    }
    
    
    

    
    
    
}
