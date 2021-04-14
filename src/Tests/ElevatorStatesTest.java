package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import Constants.Direction;
import Constants.DoorStatus;
import Elevator.Elevator;
import Elevator.ElevatorSubsystem;
import Elevator.Moving;
import Elevator.Stopped;

/**
 * Test the Diffrent states of the elevators.
 * @author Darsh Alex
 * @Version 02/27/2021
 */
public class ElevatorStatesTest {
	static private Elevator elevator;
	static private ElevatorSubsystem elevatorSubsystem;
	
	@BeforeClass
	public static void setUp() {
		elevatorSubsystem = new ElevatorSubsystem(4);
		elevator = new Elevator(elevatorSubsystem, "1");
    }
	
	@Test
	public void testElevatorInitalStates() {
		assertEquals(elevator.getDoorState(),  DoorStatus.closed);
		assertEquals(elevator.getCurrentFloor(),  1);
		assertEquals(elevator.getDirection(),  Direction.Idle);
		assertEquals(elevator.getElevatorId(),  "1");
		assertEquals(elevator.getOperationalStatus(),  true);
		assertTrue(elevator.getElevatorState() instanceof  Stopped);
	}
	
	@Test
	public void testElevatorSetState() {
		elevator.setState(new Moving(elevator));
		assertTrue(elevator.getElevatorState() instanceof  Moving);
		
		elevator.setState(new Stopped(elevator));
		assertTrue(elevator.getElevatorState() instanceof  Stopped);
	}
	
	@Test
	public void testElevatorDoorSetState() {
		elevator.setDoorState(true);
		assertEquals(elevator.getDoorState(),  DoorStatus.open);
		
		elevator.setDoorState(false);
		assertEquals(elevator.getDoorState(),  DoorStatus.closed);
	}
	
	@Test
	public void testElevatorCurrentFloorSetState() {
		elevator.setCurrentFloor(10);
		assertEquals(elevator.getCurrentFloor(),  10);
	}
	
	@Test
	public void testElevatorDirectionSetState() {
		elevator.setDirection(Direction.UP);
		assertEquals(elevator.getDirection(),  Direction.UP);
		
		elevator.setDirection(Direction.DOWN);
		assertEquals(elevator.getDirection(),  Direction.DOWN);
	}
	
	@Test
	public void testElevatorOperationalSetState() {
		elevator.setOperationalStatus(false);
		assertEquals(elevator.getOperationalStatus(),  false);
	}
}
