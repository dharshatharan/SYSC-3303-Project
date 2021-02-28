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
    	if (elevator.getPreJob() == null && elevator.getJob() == null ) {
	    	Scheduler scheduler = elevator.getScheduler();
	    	this.job = scheduler.getNextJob();
    	} else if (elevator.getPreJob() == null && elevator.getJob() != null ) {
    		this.job = elevator.getJob();
    	}
	    
		startJob();
		exit();
    }

    public void startJob() {
    	System.out.println(job);
    	
    	if(elevator.getCurFloor() != job.getCurrentfloornumber()) {
    		elevator.setPreJob(new RequestElevatorEvent("0", elevator.getCurFloor(), checkToGoDirection(elevator.getCurFloor(), job.getCurrentfloornumber()), job.getCurrentfloornumber(), 0));
    		elevator.setTimer(elevator.getPreJob().getTime());				
            elevator.setDirection(elevator.getPreJob().getDirection());
            elevator.setcurFlor(elevator.getPreJob().getCurrentfloornumber());
            elevator.setDestination(elevator.getPreJob().getDestinationfloornumber());
            elevator.setJob(job);
    	} else {
    		elevator.setJob(job);
    		elevator.setTimer(elevator.getJob().getTime());				
            elevator.setDirection(elevator.getJob().getDirection());
            elevator.setcurFlor(elevator.getJob().getCurrentfloornumber());
            elevator.setDestination(elevator.getJob().getDestinationfloornumber());
    	}
        
    }
    
    private Direction checkToGoDirection(int fromFloor, int toFloor) {
    	if (fromFloor < toFloor) return Direction.UP;
    	return Direction.DOWN;
    }

    @Override
    public void exit() {
        elevator.setState(new Moving(elevator));
        elevator.getState().enter();
        notifyAll();
        

    }

}