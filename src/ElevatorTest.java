/**
 * Test the last input if all threads have the right value
 * @author Quinn
 *
 */
public class ElevatorTest {

	@Test
	public void test() {
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
		
		assertEquals(elevator.gettimer(), "19:05:15.0");
		assertEquals(elevator.getDirection(), Direction.DOWN);
		assertEquals(elevator.getCurrentfloornumber(), 2);
		assertEquals(elevator.getDestinationfloornumber(), 0);
		
		assertEquals(floorSub.gettimer(), "19:05:15.0");
		assertEquals(floorSub.getDirection(), Direction.DOWN);
		assertEquals(floorSub.getCurrentfloornumber(), 2);
		assertEquals(floorSub.getDestinationfloornumber(), 0);
		
		assertEquals(scheduler.gettimer(), "19:05:15.0");
		assertEquals(scheduler.getDirection(), Direction.DOWN);
		assertEquals(scheduler.getCurrentfloornumber(), 2);
		assertEquals(scheduler.getDestinationfloornumber(), 0);
	
	}

}
