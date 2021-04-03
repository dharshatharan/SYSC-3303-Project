package Elevator;

import java.util.Map;

import javax.swing.*; 

public class guiElevator{

	static ElevatorSubsystem elevators;
	static JFrame f = new JFrame();
	
	
	
	public guiElevator(ElevatorSubsystem e) {
		this.elevators = e;
	}
	
	

	
	public static void makeGUI(Map<String, Elevator> elevatorMap) {
		JLabel l1,l2,l3,l4;
		JTextField direction1, direction2, direction3, direction4;
		JTextField operational1, operational2, operational3, operational4;
		JTextField status1, status2, status3, status4;
		JTextField cfloor1, cfloor2, cfloor3, cfloor4;
		l1 = new JLabel("Elevator 1");
		l2 = new JLabel("Elevator 2");
		l3 = new JLabel("Elevator 3");
		l4 = new JLabel("Elevator 4");
		System.out.println("--------------------------------------------------------------IM RUNNING1");
		direction1 = new JTextField();
		operational1 = new JTextField();
		status1 = new JTextField();
		cfloor1 = new JTextField();
		direction2 = new JTextField();
		operational2 = new JTextField();
		status2 = new JTextField();
		cfloor2 = new JTextField();
		//direction3 = new JTextField();
		//operational3 = new JTextField();
		//status3 = new JTextField();
		//cfloor3 = new JTextField();
		//direction4 = new JTextField();
		//operational4 = new JTextField();
		//status4 = new JTextField();
		//cfloor4 = new JTextField();
		
		
		
		
		
		//Elevator 1
		l1.setBounds(50,50, 100,30);
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
		direction1.setBounds(50,100, 100,30);
		operational1.setBounds(50,150, 100,30);
		status1.setBounds(50,200, 100,30);
		cfloor1.setBounds(50,250, 100,30);
		System.out.println("--------------------------------------------------------------IM RUNNING2");
		
		
		
		//Elevator 2
		l2.setBounds(200,50, 100,30);
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
			}}catch (NullPointerException e) {}
		temp2 = "empty";
			
		status2.setText(temp2);
		

		temp2 = Integer.toString(elevator2.getCurrentFloor());
		if (temp2.isEmpty() || temp2.equals(null)) {
			temp2 = "empty";
		}
		cfloor2.setText(temp2);
		
		//
		direction2.setBounds(200,100, 100,30);
		operational2.setBounds(200,150, 100,30);
		status2.setBounds(200,200, 100,30);
		cfloor2.setBounds(200,250, 100,30);
		
		//Elevator 3
		//l3.setBounds(350,50, 100,30);
		//
		//if(!getElevatorInfo(3).equals(null)) {
			//direction3.setText(getElevatorInfo(3).getDirection().toString());
			//operational3.setText(String.valueOf(getElevatorInfo(3).getOperationalStatus()));
			//status3.setText(getElevatorInfo(3).getStateToString());
			//cfloor3.setText(Integer.toString(getElevatorInfo(3).getCurrentFloor()));
		//}
		//if(getElevatorInfo(3).equals(null)) {
			//direction3.setText("Null");
			//operational3.setText("Null");
			//status3.setText("Null");
			//cfloor3.setText("Null");
		//}
		//
		//direction3.setBounds(350,100, 100,30);
		//operational3.setBounds(350,150, 100,30);
		//status3.setBounds(350,200, 100,30);
		//cfloor3.setBounds(350,250, 100,30);
		
		//Elevator 4
		//l4.setBounds(500,50, 100,30);
		//
		//if(!getElevatorInfo(4).equals(null)) {
			//direction4.setText(getElevatorInfo(4).getDirection().toString());
			//operational4.setText(String.valueOf(getElevatorInfo(4).getOperationalStatus()));
			//status4.setText(getElevatorInfo(4).getStateToString());
			//cfloor4.setText(Integer.toString(getElevatorInfo(4).getCurrentFloor()));
		//}
		//if(getElevatorInfo(4).equals(null)) {
			//direction4.setText("Null");
			//operational4.setText("Null");
			//status4.setText("Null");
			//cfloor4.setText("Null");
		//}
		//
		//direction4.setBounds(500,100, 100,30);
		//operational4.setBounds(500,150, 100,30);
		//status4.setBounds(500,200, 100,30);
		//cfloor4.setBounds(500,250, 100,30);
		
		
		
		System.out.println("--------------------------------------------------------------IM RUNNING3");
		f.add(l1); f.add(l2); //f.add(l3); f.add(l4); 
		f.add(direction1); f.add(status1); f.add(cfloor1); f.add(operational1);
		f.add(direction2); f.add(status2); f.add(cfloor2); f.add(operational2);
		//f.add(direction3); f.add(status3); f.add(cfloor3); f.add(operational3);
		//f.add(direction4); f.add(status4); f.add(cfloor4); f.add(operational4);
	    f.setSize(800,500);
	    f.setLayout(null);  
	    f.setVisible(true);
	
	}
	
	
	
	
	
	public static Elevator getElevatorInfo(int id, Map<String, Elevator> elevatorMap){
		System.out.println("--------------------------------------------------------------IM RUNNING4");
		for(Map.Entry<String,Elevator> entry: elevatorMap.entrySet()) {
			if(entry.getKey() != null) {
				if(Integer.parseInt(entry.getKey()) == id) {
					System.out.println("Im running-------------------------------Retrun statemnetbreak");
					return entry.getValue();
			}
		}
	}
	return null;
	}
	
	
	
	
	
}  

	
	
	
	
	
	
	
	
	
	
	
	
	
	


