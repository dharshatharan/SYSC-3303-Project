package Floor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Elevator.ElevatorInfo;

/*
 * Class to facilitate the communication between the FloorSubsystem and the Scheduler
 * @author Alex Bhend
 */
public class FloorSchedulerCommunicator {
	
	private DatagramPacket sendElevatorRequestPacket, replyElevatorRequestPacket;
	private DatagramPacket receiveElevatorInfoPacket, replyElevatorInfoPacket;
	private DatagramSocket sendElevatorRequestSocket;
	private DatagramSocket receiveElevatorInfoSocket;
	
	private Pattern elevatorRequestReplyPattern = Pattern.compile("^02 ");
	
	private FloorSubsystem floorSubsystem;
	
	/*
	 * Constructor for the FloorSchedulerCommunicator class
	 * @param floor
	 */
	public FloorSchedulerCommunicator(FloorSubsystem floorSubsystem) {
		this.floorSubsystem = floorSubsystem;
		try {
			sendElevatorRequestSocket = new DatagramSocket();
			receiveElevatorInfoSocket = new DatagramSocket();
		} catch (SocketException se) {
			se.printStackTrace();
			System.exit(1);
		}
	}
	
	/*
	 * Method to send an elevator request to the scheduler using sockets and ensure the reply
	 * is in the correct format
	 * @param job
	 */
	public void sendElevatorRequest(RequestElevatorEvent job) {
		byte msg[] = job.getByteArray("01");
		
//		System.out.println("Preparing to send elevator request to Scheduler...\n");
		
		try {
			sendElevatorRequestPacket = new DatagramPacket(msg, msg.length, InetAddress.getLocalHost(), 77);
		} 
		catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
//		System.out.println("Sending elevator request to Scheduler...\n");
		
		try {
			sendElevatorRequestSocket.send(sendElevatorRequestPacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		byte reply[] = new byte[3];
		replyElevatorRequestPacket = new DatagramPacket(reply, reply.length);
		
//		System.out.println("Waiting to receive reply...\n");
		
		try {
			sendElevatorRequestSocket.receive(replyElevatorRequestPacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		int len = replyElevatorRequestPacket.getLength();
		String received = new String(reply, 0, len);
		
		Matcher m = elevatorRequestReplyPattern.matcher(received);
		if(m.find()) {
//			System.out.println("Reply received\n");
		} else {
			System.out.println("Invalid reply received");
		}
		
	}
	
	/*
	 * Method to send an elevatorInfo request to the scheduler using sockets and ensure the reply
	 * is in the correct format
	 */
	public void requestElevatorInfo() {
		
		String request_string = "07 0 ";
		byte[] msg = request_string.getBytes();
		
		try {
			receiveElevatorInfoPacket = new DatagramPacket(msg, msg.length, InetAddress.getLocalHost(), 84);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
//		System.out.println("Preparing to send elevator info request...\n");
		
		try {
			receiveElevatorInfoSocket.send(receiveElevatorInfoPacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		byte[] reply = new byte[20];
		replyElevatorInfoPacket = new DatagramPacket(reply, reply.length);
		
//		System.out.println("Waiting to receive reply...\n");
		
		try {
			receiveElevatorInfoSocket.receive(replyElevatorInfoPacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		int len = replyElevatorInfoPacket.getLength();
		String received = new String(reply, 0, len);
		
		try {
			floorSubsystem.addElevatorInfo(new ElevatorInfo(reply));
		} catch (Exception e) {
			System.out.println("Invalid byte array!");
			return;
		}
		
	}
	
}
