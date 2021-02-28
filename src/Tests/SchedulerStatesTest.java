package Tests;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import Elevator.Elevator;
import Elevator.Idle;
import Elevator.Moving;
import Elevator.Stopped;
import Scheduler.ProcessFault;
import Scheduler.ReceiveRequestsAndFaults;
import Scheduler.ScheduleRequest;
import Scheduler.Scheduler;

/**
 * 
 * @author 
 *
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