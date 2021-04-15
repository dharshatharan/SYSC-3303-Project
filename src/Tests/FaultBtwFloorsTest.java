package Tests;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import Constants.Direction;
import Scheduler.ProcessElevatorInfoThread;
import Scheduler.Scheduler;
import Elevator.ElevatorInfo;
import Elevator.ElevatorJob;

/**
* Test the fault if the elevator is stuck between floors
* @author Quinn
*
*/
public class FaultBtwFloorsTest {

	static private Scheduler scheduler;
	private static ProcessElevatorInfoThread ElevatorInfoThread;
	
	@BeforeClass
	public static void setUp() {
		scheduler = new Scheduler();
		
    }
	
	@Test
    public void FaultTriggered() {
		
		ElevatorInfoThread = new ProcessElevatorInfoThread(scheduler);
		ElevatorInfo elevatorInfo = new ElevatorInfo(false, String.valueOf(1), 1, Direction.UP, 5);
		
		ElevatorInfoThread.enqueueInfo(elevatorInfo);
		ElevatorInfoThread.start();
		
		try {
			Thread.sleep(6500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ElevatorJob finishedJob = scheduler.getProcessJobThread().dequeueReadyJob();
		
		assertEquals(finishedJob.getElevatorID(), "1");
		assertEquals(finishedJob.getDirectionSeeking(), Direction.Idle);
		assertEquals(finishedJob.getFromFloor(), 1);
		assertEquals(finishedJob.getToFloor(), 1);
		assertEquals(finishedJob.getFault(), 9);
		ElevatorInfoThread.stop();
	}
	
	@Test
    public void FaultNotTriggered() {    
		
		ElevatorInfoThread = new ProcessElevatorInfoThread(scheduler);
		ElevatorInfo elevatorInfo = new ElevatorInfo(false, String.valueOf(1), 1, Direction.UP, 5);
		
		ElevatorInfoThread.enqueueInfo(elevatorInfo);
		ElevatorInfoThread.start();
		
		ElevatorInfoThread.enqueueInfo(new ElevatorInfo(true, String.valueOf(1), 1, Direction.UP, 9));
		System.out.println("test_1");
		try {
			Thread.sleep(6500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(scheduler.getProcessJobThread().hasJob());
		ElevatorInfoThread.interrupt();
	}
	
	}
