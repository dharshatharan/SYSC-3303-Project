package Elevator;
import java.io.*;
import java.net.*;
import Constants.Direction;
import Constants.FloorNumber;
import java.util.*;

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
	
	
	
	byte data[];
	byte zero = 0;
	byte one = 1;
	byte two = 2;
	byte three = 3;
	byte four = 4;
	byte five = 5;
	byte six = 6;
	byte seven = 7;
	byte eight = 8;
	byte nine = 9;
	
	public enum dataTypes{
		requestJob,
		replyJob,
		sendElevatorInfo,
		replyElevatorInfo,
		invalid;
	}
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


	public void replyElevatorInfo(byte data[]) {
		if(parse(data) == dataTypes.replyElevatorInfo) {
			//communicates with whatever thread requested info about an ID
	}
	}
	
	
	//Takes a VALID job and sets it as the next Job.
	public void replyJob(byte data[]) {
		ElevatorJob newJob = null;
		if(data[7] == (byte) 1 ) {
			newJob = new ElevatorJob(floors.checkFloor((int) data[9]), Direction.UP);
		}
		if(data[7]== (byte) 2) {
			newJob = new ElevatorJob(floors.checkFloor((int) data[9]), Direction.DOWN);
		}
		//Placeholder until threading is accounted for.
		elevator.setJob(newJob);
		//Probably going to set return type to Elevator Job which can then become a synchronized method which will check the ID somehow.
	}
	
	
	
	
	
	
	public void process(){
		byte data[] = receiveQueue.remove();
		if(parse(data) == dataTypes.replyJob) {
			replyJob(data);
		}
		if(parse(data) == dataTypes.replyElevatorInfo) {
			//
		}
	}
	
	

	
	//Creates a request Job formatted byte array for sending
	public byte[] createRequestJob(int elevatorId) {
		byte data[] = new byte[6];
		data[0] = zero;
		data[1] = three;
		data[2] = zero;
		data[3] = (byte) elevatorId;
		data[4] = zero;
		if (parse(data) == dataTypes.requestJob) {
			return data;
		}
		return null;
	}

	
	//Creates an elevator info formatted byte array for sending.
	public byte[] createSendElevatorInfo(int type, int elevatorId, int floor, int direction) {
		byte data[] = new byte[11];
		data[0] = zero;
		data[1] = five;
		data[2] = zero;
		data[3] = (byte) type;
		data[4] = zero;
		data[5] = (byte) elevatorId;
		data[6] = zero;
		data[7] = (byte) floor;
		data[8] = zero;
		data[9] = (byte) direction;
		data[10] = zero;
		if (parse(data) == dataTypes.sendElevatorInfo) {
			return data;
		}
		return null;
	}
	
	
	
	
	//Verifies that the byte arrays being received or sent are valid.
	public dataTypes parse(byte data[]) {
		/*
		 * Request Elevator Job from Elevator to Scheduler
			03
			0
			Elevator Id
			0
		 */
		if(data[0] == zero && data[1] == three) {
			if((data[2] == zero) && (data[4] == zero)) {
				return dataTypes.requestJob;
			}
			return dataTypes.invalid;
		}
		
		/*
		Reply Elevator Job from Scheduler to Elevator
		04
		0 
		Elevator Id
		0
		Current Floor
		0
		Direction - (1 -> down, 2 -> up)
		0
		Destination Floor
		0
		*/
		if((data[0]== zero) && (data[1]== four)) {
			if((data[2]== zero) && (data[4]== zero) && (data[6]== zero) && (data[8]== zero) && (data[10] == zero)) {
				return dataTypes.replyJob;
			}
			return dataTypes.invalid;
		}
		
		/*
		 * Send Elevator Info from Elevator to Scheduler
			05
			0
			Type -> 1 (Arrival) -> 2 (Crossing)
			0
			Elevator ID
			0
			Floor
			0 
			Direction - (1 -> down, 2 -> up)
			0
		 */
		if((data[0]== zero) && (data[1]== five)) {
			if((data[2]== zero) && (data[4]== zero) && (data[6]== zero) && (data[8]== zero) && (data[10] == zero)) {
				return dataTypes.sendElevatorInfo;
			}
			return dataTypes.invalid;
		}
		/*
		 * Reply Elevator Info from Scheduler to Elevator
			06
			0
		 */
		if((data[0]== zero) && (data[1]== six)) {
			if((data[2]== zero)) {
				return dataTypes.replyElevatorInfo;
			}
			return dataTypes.invalid;
		}
		return dataTypes.invalid;
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