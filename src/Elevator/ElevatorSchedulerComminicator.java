package Elevator;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Elevator.ElevatorInfo;

/*
 * Class to facilitate the communication between the ElevatorSubsystem and the Scheduler
 * @author Bailey
 */
public class ElevatorSchedulerComminicator {
	
	private DatagramPacket receiveElevatorJobPacket, sendElevatorInfoPacket;
	private DatagramSocket receiveElevatorJobSocket, sendElevatorInfoSocket;
	
	private Pattern elevatorInfoReplyPattern = Pattern.compile("^06 ");
	
	private ElevatorSubsystem elevatorSubsystem;
	
	/*
	 * Constructor for the ElevatorSchedulerComminicator class
	 * @param floor
	 */
	public ElevatorSchedulerComminicator(ElevatorSubsystem elevatorSubsystem) {
		this.elevatorSubsystem = elevatorSubsystem;
		try {
			receiveElevatorJobSocket = new DatagramSocket();
			sendElevatorInfoSocket = new DatagramSocket();
		} catch (SocketException se) {
			se.printStackTrace();
			System.exit(1);
		}
	}
	
	/*
	 * Method to receives an elevator job using sockets and ensure the reply
	 * is in the correct format
	 */
	public void recieveElevatorJob() {
		
		String request_string = "03 ";
		byte[] msg = request_string.getBytes();
		
		try {
			receiveElevatorJobPacket = new DatagramPacket(msg, msg.length, InetAddress.getLocalHost(), 69);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
//		System.out.println("Preparing to send elevator Job request...\n");
		
		try {
			receiveElevatorJobSocket.send(receiveElevatorJobPacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		byte[] reply = new byte[20];
		receiveElevatorJobPacket = new DatagramPacket(reply, reply.length);
		
//		System.out.println("Waiting to receive reply...\n");
		
		try {
			receiveElevatorJobSocket.receive(receiveElevatorJobPacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		int len = receiveElevatorJobPacket.getLength();
		String received = new String(reply, 0, len);
		
		try {
			ElevatorJob job = new ElevatorJob(reply);
//			System.out.println(job);
			elevatorSubsystem.receiveJob(job);
		} catch (Exception e) {
			System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) +": "+ e);
			return;
		}
		
	}
	
	/*
	 * Method to send an elevator request to the scheduler using sockets and ensure the reply
	 * is in the correct format
	 * @param info
	 */
	public void sendElevatorInfo() {
		ElevatorInfo info = elevatorSubsystem.getNextElevatorInfo();
		byte msg[] = info.getByteArray("01");
		
//		System.out.println("Preparing to send elevator request to Scheduler...\n");
		
		try {
			sendElevatorInfoPacket = new DatagramPacket(msg, msg.length, InetAddress.getLocalHost(), 96);
		} 
		catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
//		System.out.println("Sending elevator request to Scheduler...\n");
		
		try {
			sendElevatorInfoSocket.send(sendElevatorInfoPacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		byte reply[] = new byte[20];
		sendElevatorInfoPacket = new DatagramPacket(reply, reply.length);
		
//		System.out.println("Waiting to receive reply...\n");
		
		try {
			sendElevatorInfoSocket.receive(sendElevatorInfoPacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		int len = sendElevatorInfoPacket.getLength();
		String received = new String(reply, 0, len);
		
		Matcher m = elevatorInfoReplyPattern.matcher(received);
		if(m.find()) {
//			System.out.println("Reply received\n");
		} else {
			System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": Invalid reply received");
		}
		
	}
	
	
	
}
