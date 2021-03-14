//package Tests;
//
//import static org.junit.Assert.assertTrue;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import Elevator.Elevator;
//import Elevator.Idle;
//import Elevator.Moving;
//import Elevator.Stopped;
//import Scheduler.Scheduler;
//
///**
// * Test the Diffrent states of the elevators.
// * @author Darsh Alex
// * @Version 02/27/2021
// */
//public class ElevatorStatesTest {
//	static private Elevator elevator;
//	static private Scheduler scheduler;
//	
//	@BeforeClass
//	public static void setUp() {
//		scheduler = new Scheduler();
//		elevator = new Elevator(scheduler);
//    }
//	
//	@Test
//	public void testElevatorInitalState() {
//		assertTrue(elevator.getState() instanceof  Idle);
//	}
//	
//	@Test
//	public void testElevatorSetState() {
//		elevator.setState(new Moving(elevator));
//		assertTrue(elevator.getState() instanceof  Moving);
//		
//		elevator.setState(new Stopped(elevator));
//		assertTrue(elevator.getState() instanceof  Stopped);
//		
//		elevator.setState(new Idle(elevator));
//		assertTrue(elevator.getState() instanceof  Idle);
//	}
//}
