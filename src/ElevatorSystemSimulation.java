import Elevator.Elevator;
import Floor.FloorSubsystem;
import Schedualer.Scheduler;

/**
 * 
 */

/**
 * 
 * @author Dharsh
 *
 */
public class ElevatorSystemSimulation {

	/**
	 * Mainb method of the program. runs the simulation
	 * Generates the instances of the schedule, floor subsystem and the elevator
	 * @param args
	 */
	public static void main(String[] args) {
		Scheduler scheduler = new Scheduler();
		Elevator elevator = new Elevator(scheduler);
		FloorSubsystem floorSub = new FloorSubsystem(scheduler);
		
		Thread schedularThread = new Thread(scheduler, "SchedularThread");
		Thread elevatorThread = new Thread(elevator, "ElevatorThread");
		Thread floorThread = new Thread(floorSub, "FloorThread");
		
		System.out.println("Starting....");
		
		schedularThread.start();
		elevatorThread.start();
		floorThread.start();
	}

}
