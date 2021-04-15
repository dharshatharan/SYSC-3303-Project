package Floor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Constants.Direction;
import Elevator.ElevatorInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit; 


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
    private int completedJobs = 0;
    private LocalDateTime startTime;

    /**
     * Default Constructor
     * @param schedular
     */
    public FloorSubsystem(String filename) {
    	this.floorComm = new FloorSchedulerCommunicator(this);
    	this.floorjobs = new ArrayList<>();
        parse(filename);
    }

    /**
     * Runs a thread that retrives the jobs and sends them to the scheduler based on the start time in the txt document
     */
    @Override
    public void run() {
    	System.out.println( DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": Starting floor run");
    	int count = 0;
    	
		Thread requestElevatorInfoThread = new Thread(){
		   public void run(){
			   while(true) {
				   floorComm.requestElevatorInfo();
			   }
		   }
	   };
	   
	   requestElevatorInfoThread.start();
	   
	   try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	   startTime = LocalDateTime.now();
	   System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(startTime) + ": First request made!");
    	
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
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
    		
    }

    /**
     * Retrieves the information from a txt file
     */
    public void parse(String filename) {
        File simulation = new File(filename);
        try {
            Scanner sc = new Scanner(simulation);
            while(sc.hasNextLine()) {
            	floorjobs.add(new RequestElevatorEvent(sc.nextLine()));
            }
            sc.close();
            }
        catch (FileNotFoundException ex) {
            System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": Error: File not found!");
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
    
    public List<RequestElevatorEvent> getFloorJobs() {
    	return floorjobs;
    }
    
    public void addElevatorInfo(ElevatorInfo info) {
		System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": Floor Subsytem has noticed: " + info.toString());
		if (info.getIsArriving()) {
			completedJobs++;
			if(completedJobs == floorjobs.size()) {
				System.out.println( DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now())
						+ "The system took a total of" +  ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()));
			}
		}
	}
    
    public static void main(String[] args) {
		FloorSubsystem f = new FloorSubsystem(".\\src\\input.txt");
		f.run();
	}

}
