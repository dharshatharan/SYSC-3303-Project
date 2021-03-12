package Elevator;

import Constants.Direction;
import Floor.RequestElevatorEvent;
import Scheduler.Scheduler;


/**
 * Notifies the scheduler that is is waiting for a job and starts the elevator
 * @author Colin
 * @Version 02/27/2021
 */
public class Idle extends ElevatorState{


    private RequestElevatorEvent job;

     /**
     * Default Constructor calls parent class
     * @param elevator
     */
    public Idle(Elevator elevator) {
        super(elevator);


    }
    /**
     * Starts task for Idle state
     */
    @Override
    public void enter() {
    	System.out.println("---------------------Elevator State changed to: IDLE-STATE---------------------");
    	requestJob();
        startJob();
    	exit();

    }
    /**
     * Request Job from scheduler then exits when it receives the job
     */
    public void requestJob() {
    	if (elevator.getPreJob() == null && elevator.getJob() == null ) {
	    	Scheduler scheduler = elevator.getScheduler();
	    	this.job = scheduler.getNextJob();
    	} else if (elevator.getPreJob() == null && elevator.getJob() != null ) {
    		this.job = elevator.getJob();
    	}
	    
		startJob();
//		exit();
    }
    /**
     * Determins if the elevator has to move to start the next job, stores a job to get to the floor or gets all thge information for the job 
     */
    public void startJob() {
    	
    	if(elevator.getCurFloor() != job.getCurrentfloornumber()) {
    		elevator.setPreJob(new RequestElevatorEvent("0", elevator.getCurFloor(), checkToGoDirection(elevator.getCurFloor(), job.getCurrentfloornumber()), job.getCurrentfloornumber(), 0));
    		elevator.setTimer(elevator.getPreJob().getTime());				
            elevator.setDirection(elevator.getPreJob().getDirection());
            elevator.setcurFlor(elevator.getPreJob().getCurrentfloornumber());
            elevator.setDestination(elevator.getPreJob().getDestinationfloornumber());
            elevator.setJob(job);
    	} else {
    		System.out.println("Next Job:" + job);
    		elevator.setJob(job);
    		elevator.setTimer(elevator.getJob().getTime());				
            elevator.setDirection(elevator.getJob().getDirection());
            elevator.setcurFlor(elevator.getJob().getCurrentfloornumber());
            elevator.setDestination(elevator.getJob().getDestinationfloornumber());
    	}
        
    }
    /**
     * checks the direction the elevator has to go to get to the job
     */    
    private Direction checkToGoDirection(int fromFloor, int toFloor) {
    	if (fromFloor < toFloor) return Direction.UP;
    	return Direction.DOWN;
    }
    /**
     * Switches stare to moving and notfiesAll
     */
    @Override
    public void exit() {
        elevator.setState(new Moving(elevator));
        elevator.getState().enter();
        notifyAll();
        

    }

}
