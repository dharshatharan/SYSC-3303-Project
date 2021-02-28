/**
 * 
 */
package Scheduler;

/**
 * @author Dharsh
 *
 */
public class ProcessFault extends SchedulerState {

	/**
	 * 
	 */
	public ProcessFault(Scheduler context) {
		super(context);
	}

	@Override
	public void enter() {
		System.out.println("---------------------Scheduler State changed to: PROCESS FAULT---------------------");
		processFault(context.getFault());
		exit();
	}
	
	public void processFault(Fault fault) {
		
	}

	@Override
	public void exit() {
		context.setState(new ReceiveRequestsAndFaults(context));
		context.getState().enter();
	}

}
