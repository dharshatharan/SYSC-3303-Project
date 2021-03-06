package Scheduler;

import java.lang.String;

/**
 * Stores the faults
 * @author Dharsh
 * @Version 02/27/2021
 */
public class Fault {

    private String fault;

    /**
     * Default constructor
     * @param fault
     */
    public Fault(String fault) {
        this.fault = fault;
    }

    /**
     * Getter for the fault
     * @return fault
     */
	public String getFault() {
		return fault;
	}
	
	/**
	 * Returns the string of information to be printed
	 * @return ElevatorInfromation
	 */
	@Override 
	public String toString() {
		return fault;
	}

}
