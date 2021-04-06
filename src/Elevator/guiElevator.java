package Elevator;
/**
 * @Author Bailey Lyster
 * @Version 04/03/2021
 */
import java.util.Map;

import javax.swing.*; 

public class guiElevator{
	

	static ElevatorSubsystem elevators;
	static JFrame f = new JFrame();
	
	
	
	public guiElevator(ElevatorSubsystem e) {
		this.elevators = e;
	}
	
	

	
	public static void makeGUI(Map<String, Elevator> elevatorMap) {
		JLabel l1,l2,l3,l4,l5,l6,l7,l8;
		JTextField direction1, direction2, direction3, direction4;
		JTextField operational1, operational2, operational3, operational4;
		JTextField status1, status2, status3, status4;
		JTextField cfloor1, cfloor2, cfloor3, cfloor4;
		l1 = new JLabel("Elevator 1");
		l2 = new JLabel("Elevator 2");
		l3 = new JLabel("Elevator 3");
		l4 = new JLabel("Elevator 4");
		l5 = new JLabel("Direction");
		l6 = new JLabel("Operational");
		l7 = new JLabel("State");
		l8 = new JLabel("Floor");		
		
		direction1 = new JTextField();
		operational1 = new JTextField();
		status1 = new JTextField();
		cfloor1 = new JTextField();
		direction2 = new JTextField();
		operational2 = new JTextField();
		status2 = new JTextField();
		cfloor2 = new JTextField();
		direction3 = new JTextField();
		operational3 = new JTextField();
		status3 = new JTextField();
		cfloor3 = new JTextField();
		direction4 = new JTextField();
		operational4 = new JTextField();
		status4 = new JTextField();
		cfloor4 = new JTextField();
		l5.setBounds(50,100, 100, 30);
		l6.setBounds(50,150, 100,30);
		l7.setBounds(50,200, 100,30);
		l8.setBounds(50,250, 100,30);
		
		
		
		
		//Elevator 1
		l1.setBounds(150,50, 100,30);
		Elevator elevator1 = getElevatorInfo(1,elevatorMap);
		while(elevator1 ==null) {
			elevator1 = getElevatorInfo(1,elevatorMap);
		}
		String temp = elevator1.getDirection().toString();
		if (temp.isEmpty() || temp.equals(null)) {
			temp = "empty";
		}
		direction1.setText(temp);
		
		temp = String.valueOf(elevator1.getOperationalStatus());
		if (temp.isEmpty() || temp.equals(null)) {
			temp = "empty";
		}		
		operational1.setText(temp);
		
		try{temp = elevator1.getStateToString();
		}catch (NullPointerException  e) {
			temp = null;
		}
		try {
			if (temp.equals(null) ||  temp.isEmpty()) {
				temp = "empty";
			}
		}catch (NullPointerException e) {}
		
		status1.setText(temp);
		
		
		temp = Integer.toString(elevator1.getCurrentFloor());
		if (temp.isEmpty() || temp.equals(null)) {
			temp = "empty";
		}
		cfloor1.setText(temp);
		
		
		//
		direction1.setBounds(150,100, 100,30);
		operational1.setBounds(150,150, 100,30);
		status1.setBounds(150,200, 100,30);
		cfloor1.setBounds(150,250, 100,30);
		
		
		
		
		//Elevator 2
		l2.setBounds(300,50, 100,30);
		//
		Elevator elevator2 =getElevatorInfo(2,elevatorMap);
		while(elevator2 ==null) {
			elevator2 =getElevatorInfo(2,elevatorMap);
		}
		String temp2;
		temp2 = elevator2.getDirection().toString();
		if (temp2.isEmpty() || temp2.equals(null)) {
			temp2 = "empty";
		}
		direction2.setText(temp2);
		
		temp2 = String.valueOf(elevator2.getOperationalStatus());
		if (temp2.isEmpty() || temp2.equals(null)) {
			temp2 = "empty";
		}
		operational2.setText(temp2);
		
		try {temp2 = elevator2.getStateToString();
		}catch (NullPointerException e) {temp2=null;}
		
		try {
			if (temp2.equals(null)|| temp2.isEmpty()) {
				temp2 = "empty";
			}}catch (NullPointerException e) {}
		
		status2.setText(temp2);
		

		temp2 = Integer.toString(elevator2.getCurrentFloor());
		if (temp2.isEmpty() || temp2.equals(null)) {
			temp2 = "empty";
		}
		cfloor2.setText(temp2);
		
		//
		direction2.setBounds(300,100, 100,30);
		operational2.setBounds(300,150, 100,30);
		status2.setBounds(300,200, 100,30);
		cfloor2.setBounds(300,250, 100,30);
		
		//Elevator 3
		l3.setBounds(450,50, 100,30);
		Elevator elevator3 =getElevatorInfo(3,elevatorMap);
		
		String temp3;
		temp3 = elevator3.getDirection().toString();
		if (temp3.isEmpty() || temp3.equals(null)) {
			temp3 = "empty";
		}
		direction3.setText(temp3);
		
		temp3 = String.valueOf(elevator3.getOperationalStatus());
		if (temp3.isEmpty() || temp3.equals(null)) {
			temp3 = "empty";
		}
		operational3.setText(temp3);
		
		try {temp3 = elevator3.getStateToString();
		}catch (NullPointerException e) {temp3=null;}
		
		try {
			if (temp3.equals(null)|| temp3.isEmpty()) {
				temp3 = "empty";
			}}catch (NullPointerException e) {}
		
		status3.setText(temp3);
		

		temp3 = Integer.toString(elevator3.getCurrentFloor());
		if (temp3.isEmpty() || temp3.equals(null)) {
			temp3 = "empty";
		}
		cfloor3.setText(temp3);
		
		direction3.setBounds(450,100, 100,30);
		operational3.setBounds(450,150, 100,30);
		status3.setBounds(450,200, 100,30);
		cfloor3.setBounds(450,250, 100,30);
		

		
		
		//Elevator 4
		l4.setBounds(600,50, 100,30);
		Elevator elevator4 =getElevatorInfo(4,elevatorMap);
		
		String temp4;
		temp4 = elevator4.getDirection().toString();
		if (temp4.isEmpty() || temp4.equals(null)) {
			temp4 = "empty";
		}
		direction4.setText(temp4);
		
		temp4 = String.valueOf(elevator4.getOperationalStatus());
		if (temp4.isEmpty() || temp4.equals(null)) {
			temp4 = "empty";
		}
		operational4.setText(temp4);
		
		try {temp4 = elevator4.getStateToString();
		}catch (NullPointerException e) {temp4=null;}
		
		try {
			if (temp4.equals(null)|| temp4.isEmpty()) {
				temp4 = "empty";
			}}catch (NullPointerException e) {}
		
		status4.setText(temp4);
		

		temp4 = Integer.toString(elevator4.getCurrentFloor());
		if (temp4.isEmpty() || temp4.equals(null)) {
			temp4 = "empty";
		}
		cfloor4.setText(temp4);
		
		
		
		direction4.setBounds(600,100, 100,30);
		operational4.setBounds(600,150, 100,30);
		status4.setBounds(600,200, 100,30);
		cfloor4.setBounds(600,250, 100,30);
		
		

		f.add(l1); f.add(l2); f.add(l3); f.add(l4); f.add(l5);f.add(l6);f.add(l6);f.add(l7);f.add(l8);
		f.add(direction1); f.add(status1); f.add(cfloor1); f.add(operational1);
		f.add(direction2); f.add(status2); f.add(cfloor2); f.add(operational2);
		f.add(direction3); f.add(status3); f.add(cfloor3); f.add(operational3);
		f.add(direction4); f.add(status4); f.add(cfloor4); f.add(operational4);
	    f.setSize(800,500);
	    f.setLayout(null);  
	    f.setVisible(true);
	
	}
	
	
	
	
	
	public static Elevator getElevatorInfo(int id, Map<String, Elevator> elevatorMap){

		for(Map.Entry<String,Elevator> entry: elevatorMap.entrySet()) {
			if(entry.getKey() != null) {
				if(Integer.parseInt(entry.getKey()) == id) {
					return entry.getValue();
			}
		}
	}
	return null;
	}
	
	
	
	
	
}  

	
	
	
	
	
	
	
	
	
	
	
	
	
	


