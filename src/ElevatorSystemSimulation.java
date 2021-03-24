//import Elevator.Elevator;
//import Floor.FloorSubsystem;
//import Scheduler.Scheduler;
//
///**
// * Generates the instances of the schedule, floorsubsystem and the elevator. Runs the simulation to demonstraigh the comunication between threads
// * 
// * @author Quinn
// *
// */
//public class ElevatorSystemSimulation {
//
//	/**
//	 * Main method of the program. runs the simulation
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		Scheduler scheduler = new Scheduler();
//		Elevator elevator = new Elevator(scheduler);
//		FloorSubsystem floorSub = new FloorSubsystem(scheduler);
//		
//		Thread schedularThread = new Thread(scheduler, "SchedularThread");
//		Thread elevatorThread = new Thread(elevator, "ElevatorThread");
//		Thread floorThread = new Thread(floorSub, "FloorThread");
//		
//		System.out.println("Starting....");
//		
//		schedularThread.start();
//		elevatorThread.start();
//		floorThread.start();
//	}
//
//}
