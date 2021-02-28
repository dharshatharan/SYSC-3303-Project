package Scheduler;

/**
 * Parent class for scheduler states
 * @author Dharsh
 * @Version 02/27/2021
 */
public abstract class SchedulerState {
	protected Scheduler context;

	/**
	 * Default Constructor
	 */
	public SchedulerState(Scheduler context) {
		this.context = context;
	}
	/**
	 * Start Task for elevator for state
	 */
	public abstract void enter();
	/**
	 * End Task for elevator for state and switch state
	 */
	public abstract void exit();

}
