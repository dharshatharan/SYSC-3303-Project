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
				context.setState(new ProcessFault(context));
				System.out.println("test3");
				break;
			}
			if (context.jobRequestExists()) {
				context.setState(new ScheduleRequest(context));
				System.out.println("test2");
				break;
			}
			
		}
		exit();
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
