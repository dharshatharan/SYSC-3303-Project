package Tests;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Elevator.ElevatorInfo;
import Floor.FloorSubsystem;
import Floor.RequestElevatorEvent;
import Scheduler.Scheduler;
import Constants.Direction;

/**
 * Test the last input if all threads have the right value
 * @author Quinn
 *
 */
public class ElevatorTest {
	
	static private Scheduler scheduler;
	static private FloorSubsystem floorSub;
	
	@BeforeClass
	public static void setUp() {
		scheduler = new Scheduler();
		floorSub = new FloorSubsystem(scheduler);
		floorSub.parse();
    }
	
	@Test
    public void requestCommunicationTest() {
        RequestElevatorEvent job = new RequestElevatorEvent("00:00:15", 1, Direction.UP, 4, 15);
        scheduler.requestElevator(job);
        assertEquals(job, scheduler.getJobRequest());
	}
	
	@Test
    public void jobCommunicationTest() {        
        RequestElevatorEvent job = new RequestElevatorEvent("00:00:15", 1, Direction.UP, 4, 15);
        scheduler.putNextJob(job);
        assertEquals(job, scheduler.getNextJob());
	}
	
	@Test
    public void arrivalCommunicationTest() {   
		RequestElevatorEvent job = new RequestElevatorEvent("00:00:15", 1, Direction.UP, 4, 15);
		ElevatorInfo info = new ElevatorInfo(job.getTime(), job.getCurrentfloornumber(), job.getDirection(), job.getDestinationfloornumber());
        scheduler.sendElevatorInfo(info);
        assertEquals(info, scheduler.getElevatorInfo());
	}

}