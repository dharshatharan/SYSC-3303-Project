package Tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import Constants.*;
import Elevator.*;

/**
 * 
 * @author Quinn Sondermeyer
 *
 */
public class DoorFaultTest {

	static private Elevator elevator;
	static private ElevatorSubsystem elevatorSubsystem;
	
	@BeforeClass
	public static void setUp() {
		elevatorSubsystem = new ElevatorSubsystem(1);
		elevator = new Elevator(elevatorSubsystem,"1");
    }
	
	@Test
    public void FaultTriggered() {
		Stopped state = new Stopped(elevator);
		elevator.setState(state);
		state.openDoors();
		elevator.setDoorState(false);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		assertEquals(elevator.getDoorState(),DoorStatus.closed);
		assertEquals(elevator.getOperationalStatus(),false);
	}
	
	@Test
    public void FaultNotTriggered() {    
		Stopped state = new Stopped(elevator);
		elevator.setState(state);
		state.openDoors();
		
		state.closeDoors();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		assertEquals(elevator.getDoorState(),DoorStatus.closed);
		assertEquals(elevator.getOperationalStatus(),true);
	}
}
