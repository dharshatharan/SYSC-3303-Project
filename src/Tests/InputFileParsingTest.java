/**
 * 
 */
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
 * @author Dharsh
 *
 */
public class InputFileParsingTest {
	static private Scheduler scheduler;
	static private FloorSubsystem floor;
	
	@BeforeClass
	public static void setUp() {
		scheduler = new Scheduler();
		floor = new FloorSubsystem(scheduler);
    }
	

	@Test
	public void parseInputFileTest() {
		floor.parse();
		List<RequestElevatorEvent> floorJobs = floor.getFloorJobs();
		assertTrue(floorJobs.get(0).equals(new RequestElevatorEvent("00:00:15", 1, Direction.UP, 4, 15)));
		assertTrue(floorJobs.get(1).equals(new RequestElevatorEvent("00:01:15", 7, Direction.DOWN, 3, 75)));
		assertTrue(floorJobs.get(2).equals(new RequestElevatorEvent("00:03:15", 4, Direction.UP, 6, 195)));
		assertTrue(floorJobs.get(3).equals(new RequestElevatorEvent("00:05:15", 0, Direction.UP, 6, 315)));
		assertTrue(floorJobs.get(4).equals(new RequestElevatorEvent("00:06:15", 6, Direction.DOWN, 2, 375)));
		assertTrue(floorJobs.get(5).equals(new RequestElevatorEvent("00:06:15", 2, Direction.DOWN, 0, 375)));
	}

}
