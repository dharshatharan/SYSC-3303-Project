/**
 * 
 */
package Scheduler;

import Floor.RequestElevatorEvent;

/**
 * @author Dharsh
 *
 */
public class ScheduleRequest extends SchedulerState {

	/**
	 * 
	 */
	public ScheduleRequest(Scheduler context) {
		super(context);
	}

	@Override
	public void enter() {
		System.out.println("Schedule Request State");
		scheduleJob(context.getJobRequest());
		exit();
	}
	
	public void scheduleJob(RequestElevatorEvent job) {
		context.putNextJob(job);
	}

	@Override
	public void exit() {
		context.setState(new RecieveRequestFaults(context));
	}

}
