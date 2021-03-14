/**
 * 
 */
package Scheduler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Elevator.ElevatorInfo;

/**
 * @author Dharsh
 *
 */
public class SchedulerElevatorCommunicator {
	private Pattern elevatorJobRequestPattern = Pattern.compile("^03 [1-9] ");
	
	private Scheduler scheduler;
	
	private DatagramPacket elevatorJobSendPacket, elevatorInfoReceivePacket;
	private DatagramSocket elevatorJobRequestSocket, elevatorInfoReceiveSocket;

	/**
	 * 
	 */
	public SchedulerElevatorCommunicator(Scheduler scheduler) {
		this.scheduler = scheduler;
		try {
			elevatorJobRequestSocket = new DatagramSocket(69);
			elevatorInfoReceiveSocket = new DatagramSocket(96);
	      } catch (SocketException se) {
	         se.printStackTrace();
	         System.exit(1);
	      } 
	}
	
	public void receiveElevatorJobRequest() {
		   byte msg[] = new byte[17];
		   elevatorJobSendPacket = new DatagramPacket(msg, msg.length);
		   System.out.println("Host: Waiting for Packet.\n");
		   
		   try {        
			   System.out.println("Waiting..."); // so we know we're waiting
			   elevatorJobRequestSocket.receive(elevatorJobSendPacket);
		   } catch (IOException e) {
			   System.out.print("IO Exception: likely:");
			   System.out.println("Receive Socket Timed Out.\n" + e);
			   e.printStackTrace();
			   System.exit(1);
		   }
		   
//		   printDetails(false, false, jobSendPacket);
		   
		   System.out.println("Received data request from Server");
		   System.out.println();
		   
		   String received = new String(msg,0,elevatorJobSendPacket.getLength());   
		   System.out.println(received + "\n");
		   
		   Matcher matcher = elevatorJobRequestPattern.matcher(received);
		   if (matcher.find()) {
			   msg = scheduler.getNextReadyJob().getByteArray("04");
		      
			   try {
				   elevatorJobSendPacket = new DatagramPacket(msg, msg.length,
						   elevatorJobSendPacket.getAddress(), elevatorJobSendPacket.getPort());
			   } catch (IllegalArgumentException e) {
				   e.printStackTrace();
				   System.exit(1);
			   }
		      
	//		   printDetails(true, true, requestDataPacket);
			   System.out.println("Sent data to Server");
			   System.out.println();
			   
			   try {
				   elevatorJobRequestSocket.send(elevatorJobSendPacket);
			   } catch (IOException e) {
				   e.printStackTrace(); 
				   System.exit(1);
			   }
		   }
	   }
	
	public void recieveElevatorInfo() {
		   byte msg[] = new byte[17];
		   elevatorInfoReceivePacket = new DatagramPacket(msg, msg.length);
		   System.out.println("Host: Waiting for Response from Server.\n");
		   
		   try {        
			   System.out.println("Waiting..."); // so we know we're waiting
			   elevatorInfoReceiveSocket.receive(elevatorInfoReceivePacket);
		   } catch (IOException e) {
			   System.out.print("IO Exception: likely:");
			   System.out.println("Receive Socket Timed Out.\n" + e);
			   e.printStackTrace();
			   System.exit(1);
		   }
		   
//		   printDetails(false, false, receiveAckPacket);
		   
		   String received = new String(msg,0,elevatorInfoReceivePacket.getLength());   
		   System.out.println(received + "\n");
		   
		   try {
			   scheduler.addElevatorInfo(new ElevatorInfo(msg));
		   } catch (Exception e) {
			   System.out.println("Invalid byte array!");
			   return;
		   }

		   msg = "06 ".getBytes();
		   
		   try {
			   elevatorInfoReceivePacket = new DatagramPacket(msg, msg.length,
					   elevatorInfoReceivePacket.getAddress(), elevatorInfoReceivePacket.getPort());
		   } catch (IllegalArgumentException e) {
			   e.printStackTrace();
			   System.exit(1);
		   }
	      
//		   printDetails(true, true, receiveAckPacket);
		   System.out.println("Sent data request from Server");
		   System.out.println();
		   
		   try {
			   elevatorInfoReceiveSocket.send(elevatorInfoReceivePacket);
		   } catch (IOException e) {
			   e.printStackTrace(); 
			   System.exit(1);
		   }
	   }
}
