package Elevator;
import java.io.*;
import java.net.*;
import Constants.Direction;
import Constants.FloorNumber;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Bailey Lyster
 * 
 *
 */


public class ElevatorSchedulerCommunicator {
	
	Elevator elevator;
	FloorNumber floors;
	DatagramSocket sendSocket, receiveSocket;
	DatagramPacket sendPacket, receivePacket;
	
	private String elevatorID;
	private int fromFloor;
	private int toFloor;
	private Direction directionSeeking;
	private Boolean arrived;
	private String elevatorID;
    private int currentFloor;
    private Direction direction;

    private Pattern elevatorInfoPattern = Pattern.compile("^0[1-9] [1-2] [1-9] [1-9] [1-2] ");
	private Pattern elevatorJobPattern = Pattern.compile("^0[1-9] [1-9] [1-9] [1-2] [1-9] ");
	
	byte data[];
	
	
	//Queues for receiving and Sending data
    Queue<byte[]> sendQueue = new LinkedList<byte[]>();
    Queue<byte[]> receiveQueue = new LinkedList<byte[]>();
    
	
	
	public ElevatorSchedulerCommunicator() {
		try {
	         sendSocket = new DatagramSocket(); //Port of the Scheduler Receive
	         receiveSocket = new DatagramSocket(); //Port of this receive Socket
	      } catch (SocketException se) {

	         se.printStackTrace();
	         System.exit(1);
	      } 	
	}
	
	
	//Crawls the sendQueue for the most recent 
	public void sendToScheduler() {
		while(sendQueue.peek() != null) {
			try {
	           sendPacket = new DatagramPacket(sendQueue.remove(), sendQueue.remove().length, InetAddress.getLocalHost(), 12); //change this later
		       } catch (UnknownHostException e) {
		           e.printStackTrace();
		       }
	
	       try {
	         sendSocket.send(sendPacket);
		      } catch (IOException e) {
		         e.printStackTrace();
		         System.exit(1);
		      }  
	}
	}
	
	
	//Receives data from scheduler and places it in a queue to be parsed and processed.
	public void receiveFromScheduler() {
		byte reply[] = new byte[100];
		receivePacket = new DatagramPacket(reply, reply.length);
		try {
	           System.out.println("Waiting...");
	           receiveSocket.receive(receivePacket);
	       } catch (IOException e) {
	           System.out.print("IO Exception: likely:");
	           System.out.println("Receive Socket Timed Out.\n" + e);
	           e.printStackTrace();
	           System.exit(1);
	       }
		if(receivePacket.getData() != null) {
			receiveQueue.add(receivePacket.getData());
		}
	}

	
	
	
	//This method adds the Requests of anysort meant for the Scheduler into a QUEUE to be send.
	//Assumes Data is properly formatted
	public void addToSendQueue(byte data[]) {
		if(data != null) {
			sendQueue.add(data);
		}
	}
	
	

		
	

	
	
	
	
	//This method checks the FRONT of the RECEIVED(from SCHEDULER) queue for the most recent datagram and PARSES it then sends it where it needs to go.
	public void process(){
		try {
			byte data[] = receiveQueue.remove();
		}catch{
			(InterruptedException e)
		}
		
		if(data != null){
			String s = new String(data);
			System.out.println(s);
			Matcher matcherInfo = elevatorJobPattern.matcher(s);
			Matcher matcherJob = elevatorInfoPattern.matcher(s);
			if (matcherInfo.find()) {
				String[] sa = s.split(" ");
				this.arrived = sa[1].equals("1");
		        this.elevatorID = sa[2];
		    	this.currentFloor = Integer.parseInt(sa[3]);
		        this.direction = sa[4].equals("1") ? Direction.UP : Direction.DOWN;
		        
		        //Call method that sends the info to the object
			}
			if (matcherJob.find()) {
				String[] sa = s.split(" ");
				this.elevatorID = sa[1];
		        this.fromFloor = Integer.parseInt(sa[2]);
		    	this.directionSeeking = sa[3].equals("1") ? Direction.UP : Direction.DOWN;
		        this.toFloor = Integer.parseInt(sa[4]);
		        
		        //Call method that sends the job to the object
			}
		}
	}

	
	

	
	
	
	
	
	
	public static void main( String args[] ) {
		ElevatorSchedulerCommunicator x = new ElevatorSchedulerCommunicator();
		Thread receiving = new Thread() {
			public void run() {
				while(true) {
					x.sendToScheduler();
				}
			}
		};
		//Thread that sends data from the queue
		Thread sending = new Thread() {
			public void run() {
				while(true) {
					x.receiveFromScheduler();
				}
			}
		};
		//Thread that processes data from the receive Queue to distribute it to the rest of the System
		Thread processesing = new Thread() {
			public void run() {
				while(true) {
					x.process();			
				}
			}
		};
		receiving.start();
		sending.start();
		processesing.start();
		while(true) {
			//Main Loop will handle either communicating with other classes or just be 'Processing'
		}
	}
}