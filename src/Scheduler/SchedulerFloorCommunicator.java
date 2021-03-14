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
import Floor.RequestElevatorEvent;

/**
 * @author Dharsh
 *
 */
public class SchedulerFloorCommunicator {
	private Pattern elevatorInfoRequestPattern = Pattern.compile("^07 [1-9] ");
	
	private Scheduler scheduler;
	
	private DatagramPacket elevatorRequestReceivePacket, elevatorInfoSendPacket;
	private DatagramSocket elevatorRequestJobReceiveSocket, elevatorInfoRSendSocket;

	/**
	 * 
	 */
	public SchedulerFloorCommunicator(Scheduler scheduler) {
		this.scheduler = scheduler;
		try {
			elevatorRequestJobReceiveSocket = new DatagramSocket(69);
			elevatorInfoRSendSocket = new DatagramSocket(96);
	      } catch (SocketException se) {
	         se.printStackTrace();
	         System.exit(1);
	      } 
	}
	
	public void recieveElevatorRequest() {
		   byte msg[] = new byte[17];
		   elevatorRequestReceivePacket = new DatagramPacket(msg, msg.length);
		   System.out.println("Host: Waiting for Response from Server.\n");
		   
		   try {        
			   System.out.println("Waiting..."); // so we know we're waiting
			   elevatorRequestJobReceiveSocket.receive(elevatorRequestReceivePacket);
		   } catch (IOException e) {
			   System.out.print("IO Exception: likely:");
			   System.out.println("Receive Socket Timed Out.\n" + e);
			   e.printStackTrace();
			   System.exit(1);
		   }
		   
//		   printDetails(false, false, receiveAckPacket);
		   
		   String received = new String(msg,0,elevatorRequestReceivePacket.getLength());   
		   System.out.println(received + "\n");
		   
		   try {
			   scheduler.addElevatorRequest(new RequestElevatorEvent(msg));
		   } catch (Exception e) {
			   System.out.println("Invalid byte array!");
			   return;
		   }

		   msg = "02 ".getBytes();
	      
		   try {
			   elevatorRequestReceivePacket = new DatagramPacket(msg, msg.length,
					   elevatorRequestReceivePacket.getAddress(), elevatorRequestReceivePacket.getPort());
		   } catch (IllegalArgumentException e) {
			   e.printStackTrace();
			   System.exit(1);
		   }
	      
//		   printDetails(true, true, receiveAckPacket);
		   System.out.println("Sent data request from Server");
		   System.out.println();
		   
		   try {
			   elevatorRequestJobReceiveSocket.send(elevatorRequestReceivePacket);
		   } catch (IOException e) {
			   e.printStackTrace(); 
			   System.exit(1);
		   }
	   }
	
	public void receiveElevatorInfoRequest() {
		   byte msg[] = new byte[17];
		   elevatorInfoSendPacket = new DatagramPacket(msg, msg.length);
		   System.out.println("Host: Waiting for Packet.\n");
		   
		   try {        
			   System.out.println("Waiting..."); // so we know we're waiting
			   elevatorInfoRSendSocket.receive(elevatorInfoSendPacket);
		   } catch (IOException e) {
			   System.out.print("IO Exception: likely:");
			   System.out.println("Receive Socket Timed Out.\n" + e);
			   e.printStackTrace();
			   System.exit(1);
		   }
		   
//		   printDetails(false, false, jobSendPacket);
		   
		   System.out.println("Received data request from Server");
		   System.out.println();
		   
		   String received = new String(msg,0,elevatorInfoSendPacket.getLength());   
		   System.out.println(received + "\n");
		   
		   Matcher matcher = elevatorInfoRequestPattern.matcher(received);
		   if (matcher.find()) {
			   msg = scheduler.getNextReadyJob().getByteArray("08");
		      
			   try {
				   elevatorInfoSendPacket = new DatagramPacket(msg, msg.length,
						   elevatorInfoSendPacket.getAddress(), elevatorInfoSendPacket.getPort());
			   } catch (IllegalArgumentException e) {
				   e.printStackTrace();
				   System.exit(1);
			   }
		      
	//		   printDetails(true, true, requestDataPacket);
			   System.out.println("Sent data to Server");
			   System.out.println();
			   
			   try {
				   elevatorInfoRSendSocket.send(elevatorInfoSendPacket);
			   } catch (IOException e) {
				   e.printStackTrace(); 
				   System.exit(1);
			   }
		   }
	   }
}
