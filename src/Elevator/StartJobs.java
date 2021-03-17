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
			ProcessJob();
		}
	}
	
	private void ProcessJob() {
		if (!elevatorSubsystem.getJobList().get("1").isEmpty() && elevatorSubsystem.getElevators().get("1").getState() instanceof Idle) {
			elevatorSubsystem.getElevators().get("1").setJob(elevatorSubsystem.getJobList().get("1").remove(0));	
			System.out.println("Sent Job to Elevator");
		}
		if (!elevatorSubsystem.getJobList().get("2").isEmpty() && elevatorSubsystem.getElevators().get("2").getState() instanceof Idle) {
			elevatorSubsystem.getElevators().get("2").setJob(elevatorSubsystem.getJobList().get("2").remove(0));	
			System.out.println("Sent Job to Elevator");
		}
	}
}
