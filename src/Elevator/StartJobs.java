package Elevator;

public class StartJobs implements Runnable {
	
	private ElevatorSubsystem elevatorSubsystem;
	
	
	public StartJobs(ElevatorSubsystem system) {
		elevatorSubsystem = system;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			if (!elevatorSubsystem.getJobList().isEmpty()) {
				
				
				elevatorSubsystem.getElevators().get("1").setJob(elevatorSubsystem.getJobList().get("1").remove(0));
					
				}
			}
		}
	}

}
