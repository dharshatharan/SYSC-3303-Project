package Tests;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Elevator.ElevatorJob;
import Floor.FloorSubsystem;
import Scheduler.Scheduler;


/**
 * Test for UDP communication.
 * @author Darsh
 */
public class CommunicationTest {
	private static FloorSubsystem floorSubsystem;
	private static Scheduler scheduler;
	private static Thread schedularThread;
	private static Thread floorSubsystemThread;

	
	@BeforeClass
	public static void setUp() {
		floorSubsystem = new FloorSubsystem(".\\src\\test_input.txt");
		scheduler = new Scheduler();
		schedularThread = new Thread(scheduler, "SchedularThread");
		floorSubsystemThread = new Thread(floorSubsystem, "FloorSubsystemThread");
		schedularThread.start();
		floorSubsystemThread.start();
    }
	
	@Test
	public void testCommunication() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(scheduler.getNextReadyJob() instanceof ElevatorJob);
	}
	
	@AfterClass() 
	public static void cleanUp() {
		
	}
}
