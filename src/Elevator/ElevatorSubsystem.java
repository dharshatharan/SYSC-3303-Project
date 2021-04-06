package Elevator;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * receives and sends task to given elevators after the information is paresed fomr the scheduler
 * @author Quinn Sondermeyer
 * @version 03/13/2021
 */
public class ElevatorSubsystem implements Runnable{	
	private static Map<String, Elevator> elevators;
	private int numElevators;
	private static guiElevator gui; 
	private List<ElevatorInfo> notifyElevatorInfoList;
	private ElevatorSchedulerComminicator comunicator;
	
	
	public ElevatorSubsystem(int numElevators) {
		this.numElevators = numElevators;
		elevators = new HashMap<String,Elevator>();
		comunicator = new ElevatorSchedulerComminicator(this);
		notifyElevatorInfoList = Collections.synchronizedList(new LinkedList<ElevatorInfo>());
		this.gui= new guiElevator(this);
		
	}
	
	
	
	/**
	 * sends the new job to the given elevator
	 * @param elevatorID
	 * @param job
	 */
	public void receiveJob(ElevatorJob job) {
		if (job.getFault() == 9) {
			System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": <<<<<<<<<<<<<<<<<<<<<<<<<<<<<Terminated elevator " + job.getElevatorID() + " because of floor error>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			elevators.get(job.getElevatorID()).setOperationalStatus(false);
			elevators.get(job.getElevatorID()).interrupt();
		}
		elevators.get(job.getElevatorID()).addJob(job);
	}



	/**
	 * creates the elevators calsses based on the number of given eleveators and starts the loop to look for jobs 
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for (int i =0; i<numElevators; i++) {
			Elevator elevator = new Elevator(this, i + 1 + "");	
			elevators.put(i + 1 +"",elevator);
			
			elevator.start();
		}		
		Thread receiveElevatorJobThread = new Thread(){
			   public void run(){
				   while(true) {
					   comunicator.recieveElevatorJob();
				   }
			   }
		   };
		   
		   Thread sendElevatorInfoThread = new Thread(){
			   public void run(){
				   while(true) {
					   comunicator.sendElevatorInfo();
				   }
			   }
		   };
		   
		   receiveElevatorJobThread.start();
		   sendElevatorInfoThread.start();
	}
	
	/**
	 * Getter for the list of elevators
	 * @return elevators
	 */
	public  Map<String, Elevator> getElevators(){
		return elevators;
	}
	
	public ElevatorSchedulerComminicator getElevatorSchedulerComminicator() {
		return getElevatorSchedulerComminicator();
	}
	
	public ElevatorInfo getNextElevatorInfo() {
		synchronized (notifyElevatorInfoList) {
			while (notifyElevatorInfoList.isEmpty()) {
				try {
					notifyElevatorInfoList.wait();
	            } catch (InterruptedException e)  {
	                Thread.currentThread().interrupt(); 
	            }
			}
			ElevatorInfo nextInfo = notifyElevatorInfoList.remove(0);
			notifyElevatorInfoList.notify();
			return nextInfo;
		}
	}
	
	public void addElevatorInfoList(ElevatorInfo info) {
		synchronized (notifyElevatorInfoList) {
			notifyElevatorInfoList.add(info);
			notifyElevatorInfoList.notify();
		}
	}
	
	
	public static void main(String[] args) {
		ElevatorSubsystem es = new ElevatorSubsystem(4);
		es.run();
		try{Thread.sleep(500);
		}catch(InterruptedException e) {}
		//t1.start();
		GUIThread.start();
	}
	
	

	static Thread GUIThread = new Thread() {
		public void run() {
			while(true) {
				try {GUIThread.sleep(50);
				}catch (InterruptedException e) {}
				gui.makeGUI(elevators);
				gui.f.revalidate();
				gui.f.repaint();
			}
		}
	};
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}