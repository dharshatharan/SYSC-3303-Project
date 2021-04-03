package Elevator;

import java.util.Map;

import javax.swing.*; 

public class guiElevator{

	static ElevatorSubsystem elevators;
	Elevator elevatorInfo;
	private static Map<String, Elevator> elevatorMap;
	static JFrame f = new JFrame();
	
	
	
	public static void main(String args[]) {
		makeGUI();
		t1.start();
		mapUpdate.start();
}
	
	
	
	
	public static void updateMap() {
		try {Thread.sleep(100);
		}catch (InterruptedException e) {	
	}
		elevatorMap = elevators.getElevators(); 
	}
	
	
	
	
	
	
	
	public static void makeGUI() {
		JLabel l1,l2,l3,l4;
		JTextField direction1, direction2, direction3, direction4;
		JTextField operational1, operational2, operational3, operational4;
		JTextField status1, status2, status3, status4;
		JTextField cfloor1, cfloor2, cfloor3, cfloor4;
		l1 = new JLabel("Elevator 1");
		l2 = new JLabel("Elevator 2");
		l3 = new JLabel("Elevator 3");
		l4 = new JLabel("Elevator 4");
		
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
		
		
		
		
		
		//Elevator 1
		l1.setBounds(50,50, 100,30);
		direction1.setText(getElevatorInfo(1).getDirection().toString());
		operational1.setText(String.valueOf(getElevatorInfo(1).getOperationalStatus()));
		status1.setText(getElevatorInfo(1).getStateToString());
		cfloor1.setText(Integer.toString(getElevatorInfo(1).getCurrentFloor()));
		//
		direction1.setBounds(50,100, 100,30);
		operational1.setBounds(50,150, 100,30);
		status1.setBounds(50,200, 100,30);
		cfloor1.setBounds(50,250, 100,30);
		
		//Elevator 2
		l2.setBounds(200,50, 100,30);
		//
		direction2.setText(getElevatorInfo(2).getDirection().toString());
		operational2.setText(String.valueOf(getElevatorInfo(2).getOperationalStatus()));
		status2.setText(getElevatorInfo(2).getStateToString());
		cfloor2.setText(Integer.toString(getElevatorInfo(2).getCurrentFloor()));
		//
		direction2.setBounds(200,100, 100,30);
		operational2.setBounds(200,150, 100,30);
		status2.setBounds(200,200, 100,30);
		cfloor2.setBounds(200,250, 100,30);
		
		//Elevator 3
		l3.setBounds(350,50, 100,30);
		//
		direction2.setText(getElevatorInfo(2).getDirection().toString());
		operational2.setText(String.valueOf(getElevatorInfo(2).getOperationalStatus()));
		status2.setText(getElevatorInfo(2).getStateToString());
		cfloor2.setText(Integer.toString(getElevatorInfo(2).getCurrentFloor()));
		//
		direction3.setBounds(350,100, 100,30);
		operational3.setBounds(350,150, 100,30);
		status3.setBounds(350,200, 100,30);
		cfloor3.setBounds(350,250, 100,30);
		
		//Elevator 4
		l4.setBounds(500,50, 100,30);
		//
		direction4.setText(getElevatorInfo(4).getDirection().toString());
		operational4.setText(String.valueOf(getElevatorInfo(4).getOperationalStatus()));
		status4.setText(getElevatorInfo(4).getStateToString());
		cfloor4.setText(Integer.toString(getElevatorInfo(4).getCurrentFloor()));
		//
		direction4.setBounds(500,100, 100,30);
		operational4.setBounds(500,150, 100,30);
		status4.setBounds(500,200, 100,30);
		cfloor4.setBounds(500,250, 100,30);
		
		
		
		
		f.add(l1); f.add(l2); f.add(l3); f.add(l4); 
		f.add(direction1); f.add(status1); f.add(cfloor1); f.add(operational1);
		f.add(direction2); f.add(status2); f.add(cfloor2); f.add(operational2);
		f.add(direction3); f.add(status3); f.add(cfloor3); f.add(operational3);
		f.add(direction4); f.add(status4); f.add(cfloor4); f.add(operational4);
	    f.setSize(800,500);
	    f.setLayout(null);  
	    f.setVisible(true);
	}
	
	
	public static Elevator getElevatorInfo(int id){
		Elevator temp = null;
		for(Map.Entry<String, Elevator> entry: elevatorMap.entrySet()) {
			if(Integer.parseInt(entry.getKey()) == id) {
				temp = entry.getValue();
			}
		}
		return temp;
	}
	
	
	
	
	
	
	//REFRESHES DATA ON THE GUI
	static Thread t1 = new Thread(){
		public void run() {
			while(true) {
				try {Thread.sleep(100);
			}catch (InterruptedException e) {	
			}
			
			makeGUI();
			f.revalidate();
			f.repaint();
		}
	}
	};
	static Thread mapUpdate = new Thread() {
		public void run() {
			while(true) {
				updateMap();
			}
		}
	};
	
	
	
	
}  

	
	
	
	
	
	
	
	
	
	
	
	
	
	


