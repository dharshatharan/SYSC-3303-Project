package Floor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Constants.Direction;
import Scheduler.Scheduler;

/**
 * A class to handle the input from a file listing all the floors the elevator(s) have to visit
 * @author Alexander
 * @version 02/06/2021
 *
 */
public class FloorSubsystem implements Runnable{

    List<RequestElevatorEvent> floorjobs;
    Scheduler schedular;
    
    private String timer;
    private Direction direction;
    private int curFlor, destination;

    /**
     * Default Constructor
     * @param schedular
     */
    public FloorSubsystem(Scheduler schedular) {
    	this.schedular = schedular;
    	this.floorjobs = new ArrayList<>();
        parse();
    }

    /**
     * Runs a thread that retrives the jobs and sends them to the scheduler
     */
    @Override
    public void run() {
    	System.out.println("Starting floor run");
    	int count = 0;
    	while(true) {
    		for(RequestElevatorEvent job: floorjobs) {
    			if(count == job.getSecondsSinceMidnight()) {
    				schedular.requestElevator(job);
    				System.out.println("Floor has been notified that the Elevator for Job " + schedular.getElevatorInfo().toString());
    			}
    		}
    		if(count == 86400) {
    			count = 0;
    		} else {
    		count++;
    		}
    		try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    }

    /**
     * Retrives the information from a txt file
     */
    public void parse() {
        File simulation = new File("./src/input.txt");
        try {
            Scanner sc = new Scanner(simulation);
            while(sc.hasNextLine()) {
            	floorjobs.add(new RequestElevatorEvent(sc.nextLine()));
            }
            sc.close();
            }
        catch (FileNotFoundException ex) {
            System.out.println("Error: File not found.");
        }
    }
    
    public String getTimer(){
        return timer;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getCurFloor(){
        return curFlor;
    }

    public int getDestination() {
        return destination;
    }
    
    public List<RequestElevatorEvent> getFloorJobs() {
    	return floorjobs;
    }

}
