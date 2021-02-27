package Elevator;

import Schedualer.Scheduler;
import Constants.Direction;
import Floor.RequestElevatorEvent;

/**
 * 
 * @author 
 * @version 02/06/2021
 */
public class Elevator implements Runnable {
	
	private Scheduler scheduler;
	
	private String timer;
    private Direction direction;
    private int curFlor, destination;
	
	public Elevator(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
	
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

	public String getTimer(){
        return timer;
    }

    public Direction getdirection() {
        return direction;
    }

    public int getcurFlor(){
        return curFlor;
    }

    public int getdestination() {
        return destination;
    }
}
