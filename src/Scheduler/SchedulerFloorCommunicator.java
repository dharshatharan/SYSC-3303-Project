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
	private Pattern elevatorInfoRequestPattern = Pattern.compile("^07 0 ");
	
	private Scheduler scheduler;
	
	private DatagramPacket elevatorRequestReceivePacket, elevatorInfoSendPacket;
	private DatagramSocket elevatorRequestJobReceiveSocket, elevatorInfoRSendSocket;

	/**
	 * 
	 */
	public SchedulerFloorCommunicator(Scheduler scheduler) {
		this.scheduler = scheduler;
		try {
			elevatorRequestJobReceiveSocket = new DatagramSocket(77);
			elevatorInfoRSendSocket = new DatagramSocket(84);
	      } catch (SocketException se) {
	         se.printStackTrace();
	         System.exit(1);
	      } 
	}
	
	public void recieveElevatorRequest() {
		   byte msg[] = new byte[9];
		   elevatorRequestReceivePacket = new DatagramPacket(msg, msg.length);
		   System.out.println("Waiting for RequestElevatorEvent from Floor\n");
		   
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
			   System.out.println(e);
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
		   System.out.println("Recieved RequestElevatorEvent from Floor");
		   System.out.println();
		   
		   try {
			   elevatorRequestJobReceiveSocket.send(elevatorRequestReceivePacket);
		   } catch (IOException e) {
			   e.printStackTrace(); 
			   System.exit(1);
		   }
	   }
	
	public void receiveElevatorInfoRequest() {
		   byte msg[] = new byte[5];
		   elevatorInfoSendPacket = new DatagramPacket(msg, msg.length);
		   System.out.println("Waiting for ElevatorInfo request.\n");
		   
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
		   
		   System.out.println("Received ElevatorInfo request from Floor");
		   System.out.println();
		   
		   String received = new String(msg,0,elevatorInfoSendPacket.getLength());   
		   System.out.println(received + "\n");
		   
		   Matcher matcher = elevatorInfoRequestPattern.matcher(received);
		   if (matcher.find()) {
			   msg = scheduler.getNextElevatorInfo().getByteArray("08");
		      
			   try {
				   elevatorInfoSendPacket = new DatagramPacket(msg, msg.length,
						   elevatorInfoSendPacket.getAddress(), elevatorInfoSendPacket.getPort());
			   } catch (IllegalArgumentException e) {
				   e.printStackTrace();
				   System.exit(1);
			   }
		      
	//		   printDetails(true, true, requestDataPacket);
			   System.out.println("Sent ElevatorInfo to Floor");
			   System.out.println();
			   
			   try {
				   elevatorInfoRSendSocket.send(elevatorInfoSendPacket);
			   } catch (IOException e) {
				   e.printStackTrace(); 
				   System.exit(1);
			   }
		   } else {
			   System.out.println("Invalid ElevatorInfo request from Floor\n");
		   }
	   }
}
