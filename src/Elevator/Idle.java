package Elevator;

import Constants.Direction;
import Floor.RequestElevatorEvent;
import Scheduler.Scheduler;

public class Idle extends ElevatorState{


    private RequestElevatorEvent job;

    public Idle(Elevator elevator) {
        super(elevator);


    }

    @Override
    public void enter() {
    	System.out.println("---------------------Elevator State changed to: IDLE-STATE---------------------");
    	requestJob();
        startJob();
    	exit();

    }

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

    public void startJob() {
        elevator.setTimer();				
        elevator.setDirection();
        elevator.setcurFlor();
        elevator.setDestination();
        
    }

    @Override
    public void exit() {
        elevator.setState(new Moving(elevator));
        elevator.getState().enter();
        notifyAll();
        

    }

}