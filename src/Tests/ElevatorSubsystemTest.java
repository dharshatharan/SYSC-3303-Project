package Tests;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Elevator.Elevator;
import Elevator.ElevatorSubsystem;

/**
 * Test the last input if all threads have the right value
 * @author Quinn
 *
 */
public class ElevatorSubsystemTest {
	
	static private ElevatorSubsystem elevatorSubsystem;
	
	@BeforeClass
	public static void setUp() {
		elevatorSubsystem = new ElevatorSubsystem(4);
		elevatorSubsystem.run();
    }
	
	@Test
    public void elevatorInitialCountTest() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        assertEquals(elevatorSubsystem.getElevators().size(), 4);
        
        for(Elevator elevator: elevatorSubsystem.getElevators().values()) {
        	assertEquals(elevator.getOperationalStatus(),  true);
        }
	}

}