/**
 * 
 */
package Scheduler;

/**
 * Scheduler State for Recieve Requests And Faults sent form the SloorSubsystem system and the elevator
 * @author Dharsh
 * @Version 02/27/2021
 */
public class ReceiveRequestsAndFaults extends SchedulerState {

	/**
	 * Default Constructor
	 */
	public ReceiveRequestsAndFaults(Scheduler context) {
		super(context);
	}

	/**
	 * Starts Task for Recieve Requests and Faults
	 * Switches to process fault or request based on whic one comes first
	 */
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

	/**
	 * Will exit the state
	 */
	@Override
	public void exit() {
		context.getState().enter();
	}

}
