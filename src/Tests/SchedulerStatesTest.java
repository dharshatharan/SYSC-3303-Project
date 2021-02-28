package Tests;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import Scheduler.ProcessFault;
import Scheduler.ReceiveRequestsAndFaults;
import Scheduler.ScheduleRequest;
import Scheduler.Scheduler;

/**
 * Test States for Scheduler
 * @author Darsh, Alex
 * @Version 02/27/2021
 */
public class SchedulerStatesTest {
	static private Scheduler scheduler;
	
	@BeforeClass
	public static void setUp() {
		scheduler = new Scheduler();
    }
	
	@Test
	public void testElevatorInitalState() {
		assertTrue(scheduler.getState() instanceof  ReceiveRequestsAndFaults);
	}
	
	@Test
	public void testElevatorSetState() {
		scheduler.setState(new ScheduleRequest(scheduler));
		assertTrue(scheduler.getState() instanceof  ScheduleRequest);
		
		scheduler.setState(new ProcessFault(scheduler));
		assertTrue(scheduler.getState() instanceof  ProcessFault);
		
		scheduler.setState(new ReceiveRequestsAndFaults(scheduler));
		assertTrue(scheduler.getState() instanceof  ReceiveRequestsAndFaults);
	}
}
