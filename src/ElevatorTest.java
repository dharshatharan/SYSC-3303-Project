import static org.junit.Assert.*;

import org.junit.Test;

import Elevator.Elevator;
import Floor.FloorSubsystem;
import Schedualer.Scheduler;
import Constants.Direction;

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
        
        try {
        	Thread.sleep(4000);
        } catch(Exception e) {
        	System.out.println("Error");
        }

        assertEquals(elevator.getTimer(), "19:05:15.0");
        assertEquals(elevator.getdirection(), Direction.DOWN);
        assertEquals(elevator.getcurFlor(), 2);
        assertEquals(elevator.getdestination(), 0);
        
        try {
        	Thread.sleep(4000);
        } catch(Exception e) {
        	System.out.println("Error");
        }
        

        assertEquals(floorSub.getTimer(), "19:05:15.0");
        assertEquals(floorSub.getdirection(), Direction.DOWN);
        assertEquals(floorSub.getcurFlor(), 2);
        assertEquals(floorSub.getdestination(), 0);

    }

}