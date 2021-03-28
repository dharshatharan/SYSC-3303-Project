package Scheduler;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Timer for the Scheduler to use to detect faults in the elevator
 * @author Quinn Sondermeyer
 * @version 03/28/2021
 *
 */
public class ElevatorTimer{

	Timer time;
	Fault faultType;
	String elevatorID;
	Scheduler scheduler;
	
	/**
	 * Used to create notifications when a timer is completed
	 * @author Quinn Sondermeyer
	 * @version 03/28/2021
	 *
	 */
	class RemindTask extends TimerTask {
		String elevatorID;
		Fault faultType;
		Scheduler scheduler;
		
		public RemindTask(Scheduler scheduler, String elevatorID, Fault faultType) {
			this.elevatorID = elevatorID;
			this.faultType = faultType;
			this.scheduler = scheduler;
		}
        public void run() {
            System.out.println("The elevator timer has trigered");
        }
    }
	
	public ElevatorTimer(Scheduler scheduler, String elevatorID, Fault faultType) {
		this.elevatorID = elevatorID;
		this.faultType = faultType;
		this.scheduler = scheduler;
	}
	
	
	/**
	 * getter for timer
	 */
	public Timer getTimer() {
		return time;
	}
	
	/**
	 * Starts timer
	 * @param elevatorID
	 */
	public void startTimer(int seconds) {
		time.schedule(new RemindTask(scheduler, elevatorID, faultType), seconds*1000);
	}

}
