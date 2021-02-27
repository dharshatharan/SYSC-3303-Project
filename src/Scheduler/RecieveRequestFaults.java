/**
 * 
 */
package Scheduler;

/**
 * @author Dharsh
 *
 */
public class RecieveRequestFaults extends SchedulerState {

	/**
	 * 
	 */
	public RecieveRequestFaults(Scheduler context) {
		super(context);
	}

	@Override
	public void enter() {
		System.out.println("Reacieve Request Faults State");
		while(true) {
			if (context.faultExists()) {
				context.setState(new ScheduleRequest(context));
			}
			if (context.jobRequestExists()) {
				context.setState(new ScheduleRequest(context));
			}
		}
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
