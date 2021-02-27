/**
 * 
 */
package Scheduler;

/**
 * @author Dharsh
 *
 */
public abstract class SchedulerState {
	protected Scheduler context;

	/**
	 * 
	 */
	public SchedulerState(Scheduler context) {
		this.context = context;
	}
	
	public abstract void enter();
	
	public abstract void exit();

}
