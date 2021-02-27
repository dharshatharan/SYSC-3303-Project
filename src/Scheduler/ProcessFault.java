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
		System.out.println("Process Fault State");
		processFault(context.getFault());
		exit();
	}
	
	public void processFault(Fault fault) {
		
	}

	@Override
	public void exit() {
		context.setState(new RecieveRequestFaults(context));
	}

}
