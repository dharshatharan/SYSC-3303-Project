package Tests;
import static org.junit.Assert.*;

import org.junit.Test;

import Elevator.Elevator;
import Floor.FloorSubsystem;
import Scheduler.Scheduler;
import Constants.Direction;

/**
 * Test the last input if all threads have the right value
 * @author Quinn
 *
 */
public class ElevatorTest {
	
//	@Test
//    public void communicationTest() {
//		Scheduler scheduler = new Scheduler();
//        Elevator elevator = new Elevator(scheduler);
//        FloorSubsystem floorSub = new FloorSubsystem(scheduler);
//        floorSub.parse();
//        
//        scheduler.req
//	}

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
        
        try {
        	Thread.sleep(4000);
        } catch(Exception e) {
        	System.out.println("Error");
        }

        assertEquals(elevator.getTimer(), "19:05:15.0");
        assertEquals(elevator.getDirection(), Direction.DOWN);
        assertEquals(elevator.getCurFloor(), 2);
        assertEquals(elevator.getDestination(), 0);
        
        try {
        	Thread.sleep(4000);
        } catch(Exception e) {
        	System.out.println("Error");
        }
        

        assertEquals(floorSub.getTimer(), "19:05:15.0");
        assertEquals(floorSub.getDirection(), Direction.DOWN);
        assertEquals(floorSub.getCurFloor(), 2);
        assertEquals(floorSub.getDestination(), 0);

    }

}