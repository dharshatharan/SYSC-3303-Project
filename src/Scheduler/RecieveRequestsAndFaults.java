/**
 * 
 */
package Scheduler;

/**
 * @author Dharsh
 *
 */
public class RecieveRequestsAndFaults extends SchedulerState {

	/**
	 * 
	 */
	public RecieveRequestsAndFaults(Scheduler context) {
		super(context);
	}

	@Override
	public void enter() {
		System.out.println("---------------------Scheduler State changed to: RECIEVE REQUESTS AND FAULTS---------------------");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true) {
			if (context.faultExists()) {
				context.setState(new ProcessFault(context));
				break;
			}
			if (context.jobRequestExists()) {
				context.setState(new ScheduleRequest(context));
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
