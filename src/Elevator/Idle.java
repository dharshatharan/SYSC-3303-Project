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
    	Scheduler scheduler = elevator.getScheduler();
    	this.job = scheduler.getNextJob();
		elevator.setJob(job);
		//RequestElevatorEvent job = scheduler.getNextJob();
		//System.out.println(Thread.currentThread() + " is serving job " + job.toString());
					
		//scheduler.sendElevatorInfo(new ElevatorInfo(job.getTime(), job.getCurrentfloornumber(), job.getDirection(), job.getDestinationfloornumber()));
	    
		startJob();
		exit();
		
        while (this.job != null) {
            try { 
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt(); 
            }
        }
        this.job = job;
        notifyAll();
    }

    /**
     * gets all thge information for the job
     */
    public void startJob() {
        elevator.setTimer();				
        elevator.setDirection();
        elevator.setcurFlor();
        elevator.setDestination();
        
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