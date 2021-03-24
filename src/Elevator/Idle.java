package Elevator;

import Constants.Direction;
import Floor.RequestElevatorEvent;
import Scheduler.Scheduler;


/**
 * Notifies the scheduler that is is waiting for a job and starts the elevator
 * @author Colin
 * @Version 03/16/2021
 */
public class Idle extends ElevatorState{


//    private RequestElevatorEvent job;
	private ElevatorJob job;

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
    	while (elevator.getJob() == null) {
    		try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	
//    	if (elevator.getCurFloor() == elevator.getJob().getFromFloor()) {
//			elevator.setDestination(elevator.getJob().getToFloor());
//			elevator.setDirection(elevator.getJob().getDirectionSeeking());
//			elevator.setPreJob(null);
//			elevator.setState(new Moving(elevator));
//		}
//		else {
//			elevator.setState(new Idle(elevator));
//			elevator.setJob(null);
//		}
	    
    	
//    	if (elevator.getPreJob() == null && elevator.getJob() == null ) {
//	    	Scheduler scheduler = elevator.getScheduler();
//	    	this.job = scheduler.getNextJob();
//    	} else if (elevator.getPreJob() == null && elevator.getJob() != null ) {
//    		this.job = elevator.getJob();
//    	}
    	
		startJob();
		exit();
    }

    /**
     * Determins if the elevator has to move to start the next job, stores a job to get to the floor or gets all thge information for the job 
     */
    public void startJob() {
    	if (elevator.getCurFloor()== elevator.getJob().getFromFloor()) {
    		elevator.setDirection(elevator.getJob().getDirectionSeeking());
            elevator.setDestination(elevator.getJob().getToFloor());
    	}
    	else {
            elevator.setDestination(elevator.getJob().getToFloor());
            elevator.setDirection(getPreJobDirection());
    	}
    	
        
    }

    
    private Direction getPreJobDirection() {
    	if ((elevator.getCurFloor() - elevator.getJob().getToFloor()) < 0) {
    		return Direction.UP;
    	}
		return Direction.DOWN;
		
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