package Floor;
import java.io.*;
import java.util.*;

/**
 * A class to handle the input from a file listing all the floors the elevator(s) have to visit
 * @author 
 * @version 02/06/2021
 *
 */
public class FloorSubsystem implements Runnable{

	List<FloorJobSimulation> floorjobs;
	
	public FloorSubsystem() {
		floorjobs = new ArrayList<FloorJobSimulation>();
		parse();
	}	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		new FloorSubsystem();
	}

	public void parse() {
		File simulation = new File("./Sim.txt");
		try {
			Scanner sc = new Scanner(simulation);
			while(sc.hasNextLine()) {
				floorjobs.add(new FloorJobSimulation(sc.nextLine()));
				}
				sc.close();
			}
		catch (FileNotFoundException ex) {
			System.out.println("Error: File not found.");
		}
	}

}
