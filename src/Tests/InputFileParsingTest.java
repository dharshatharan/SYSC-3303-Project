package Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import Constants.Direction;
import Floor.FloorSubsystem;
import Floor.RequestElevatorEvent;
import Scheduler.Scheduler;

/**
 * Test the input file and pasing
 * @author Dharsh, Alex
 * @Version 02/27/2021
 */
public class InputFileParsingTest {
	static private FloorSubsystem floor;
	
	@BeforeClass
	public static void setUp() {
		floor = new FloorSubsystem(".//src//test_input.txt");
    }
	

	@Test
	public void parseInputFileTest() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<RequestElevatorEvent> floorJobs = floor.getFloorJobs();
		assertTrue(floorJobs.get(0).equals(new RequestElevatorEvent("00:00:1", 2, Direction.UP, 7, 1, 0)));
		assertTrue(floorJobs.get(0).equals(new RequestElevatorEvent("00:00:2", 3, Direction.UP, 8, 2, 0)));
	}

}
