package Scheduler;

import Floor.RequestElevatorEvent;

/**
 * Scheduler State for Schedule Requests
 * @author Dharsh
 * @Version 02/27/2021
 */
public class ScheduleRequest extends SchedulerState {

	/**
	 * Default Constructor
	 */
	public ScheduleRequest(Scheduler context) {
		super(context);
	}

	/**
	 * Starts Task for Scheduling Requests
	 */
	@Override
	public void enter() {
		System.out.println("---------------------Scheduler State changed to: REQUEST ELEVATOR EVENT---------------------");
		scheduleJob(context.getJobRequest());
		exit();
	}
	/**
	 * Adds next job to the scheduler
	 * @param job
	 */	
	public void scheduleJob(RequestElevatorEvent job) {
		context.putNextJob(job);
	}
	/**
	 * Exits state and switches to RecieveRequestsAndFaults
	 */
	@Override
	public void exit() {
		context.setState(new ReceiveRequestsAndFaults(context));
		context.getState().enter();
	}

}
