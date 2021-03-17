package Floor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Constants.Direction;
import Elevator.ElevatorInfo;

/**
 * A class to handle the input from a file listing all the floors the elevator(s) have to visit
 * @author Alexander
 * @version 02/27/2021
 *
 */
public class FloorSubsystem implements Runnable{

    List<RequestElevatorEvent> floorjobs;
    
    private String timer;
    private Direction direction;
    private int curFlor, destination;
    private FloorSchedulerCommunicator floorComm;

    /**
     * Default Constructor
     * @param schedular
     */
    public FloorSubsystem() {
    	this.floorComm = new FloorSchedulerCommunicator(this);
    	this.floorjobs = new ArrayList<>();
        parse();
        this.run();
    }

    /**
     * Runs a thread that retrives the jobs and sends them to the scheduler based on the start time in the txt document
     */
    @Override
    public void run() {
    	System.out.println("Starting floor run");
    	int count = 0;
    	
		Thread requestElevatorInfoThread = new Thread(){
		   public void run(){
			   while(true) {
				   floorComm.requestElevatorInfo();
			   }
		   }
	   };
    	
    	while(true) {
    		for(RequestElevatorEvent job: floorjobs) {
    			if(count == job.getSecondsSinceMidnight()) {
    				floorComm.sendElevatorRequest(job);
//    				System.out.println("Floor has been notified that the Elevator for Job " + job.toString());
    			}
    		}
    		if(count == 86400) {
    			count = 0;
    		} else {
    		count++;
    		}
    		try {
				Thread.sleep(100);
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
            System.out.println("Error: File not found!");
        }
    }
    
    /**
     * Getters and Setters for the FloorSubsystem Class below
     * 
     */
    
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
    
    public void addElevatorInfo(ElevatorInfo info) {
		System.out.println("Floor Subsytem has noticed: " + info.toString());
	}
    
    public static void main(String[] args) {
		new FloorSubsystem();
	}

}
