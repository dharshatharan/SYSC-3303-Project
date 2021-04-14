package Elevator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Constants.Direction;
import Constants.DoorStatus;
import Floor.RequestElevatorEvent;
import Scheduler.Scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An elevator that cycles through states completing Jobs
 * 
 * @author Darsh, Quinn, Bailey
 * @version 03/16/2021
 */
public class Elevator extends Thread {
	private String id;
	private int currentFloor;
	private ElevatorSubsystem elevatorSubsystem;
	private String timer;
    private Direction direction;
    private ElevatorState state;
    
    private List<ElevatorJob> jobs;
    private List<ElevatorJob> activeJobs;
    private boolean operationalStatus;   //True means it is operational.
    
    private DoorStatus doorState;
    
	/**
	 * Default constructor
	 * @param scheduler
	 */
	public Elevator(ElevatorSubsystem elevatorSubsystem, String id) {
		this.id = id;
		this.elevatorSubsystem = elevatorSubsystem;
		this.currentFloor = 1;
		this.doorState = DoorStatus.closed;
		this.direction = Direction.Idle;
		this.operationalStatus = true;
		this.jobs = Collections.synchronizedList(new LinkedList<ElevatorJob>());
		this.activeJobs = Collections.synchronizedList(new LinkedList<ElevatorJob>());
		this.state = new Stopped(this);
	}
	
	/**
	 * Runs the elevator pram constantly requesting  then exicuting jobs
	 */
	@Override
	public void run() {
//		ElevatorJob request = null;
		System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": Starting floor elevator " + id);
		while(!isInterrupted() && operationalStatus) {
			synchronized (jobs) {
				synchronized(activeJobs) {
					 try {
						 while (jobs.isEmpty() && activeJobs.isEmpty())
							 jobs.wait();
					 } catch (InterruptedException e) {
						 System.out.println(e);
						 return;
					 }
//					 request = jobs.get(0);
					 startFinishAllJobsInCurFloor();
//					 bestJob = getNextBestElevatorDestination();
				}
			 }
//			processInfo(request);
			state = new Moving(this);
			state.enter();
		}
//            state.enter();
	}
	
	public int getNextBestElevatorDestination() {
		synchronized (jobs) {
			synchronized(activeJobs) {
				Integer bestJob = null;
				for(ElevatorJob job: jobs) {
					if(job.getDirectionSeeking() == direction) {
						if(job.getDirectionSeeking() == Direction.UP && job.getFromFloor() > currentFloor){
							if (bestJob == null || job.getFromFloor() < bestJob)
								bestJob = job.getFromFloor();
						} else if (job.getDirectionSeeking() == Direction.DOWN && job.getFromFloor() < currentFloor) {
							if (bestJob == null || job.getFromFloor() > bestJob)
								bestJob = job.getFromFloor();
						}
					}
					if(job.getFault() == 9) {
						System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) +": Terminating Elevator " + id + " due to floor fault");
						operationalStatus = false;
						interrupt();
					}
				}
				for(ElevatorJob job: activeJobs) {
					if(job.getDirectionSeeking() == direction) {
						if(job.getDirectionSeeking() == Direction.UP && job.getToFloor() > currentFloor) {
							if (bestJob == null || job.getToFloor() < bestJob)
								bestJob = job.getToFloor();
						} else if (job.getDirectionSeeking() == Direction.DOWN && job.getToFloor() < currentFloor) {
							if (bestJob == null || job.getToFloor() > bestJob)
								bestJob = job.getToFloor();
						}
					}
					if(job.getFault() == 9) {
						System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) +": Terminating Elevator " + id + " due to floor fault");
						operationalStatus = false;
						interrupt();
					}
					if(job.getFault() == 3) {
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
				}
//				jobs.isEmpty() ? activeJobs.get(0).getToFloor() : jobs.get(0).getFromFloor()
				if (bestJob != null && bestJob > currentFloor) {
					direction = Direction.UP;
				} else if (bestJob != null && bestJob < currentFloor) {
					direction = Direction.DOWN;
				} else {
					direction = Direction.Idle;
				}
				if (bestJob == null && direction == Direction.Idle) {
					bestJob = !activeJobs.isEmpty() ? activeJobs.get(0).getToFloor() : jobs.get(0).getFromFloor();
				}
				return bestJob;
			}
		}
	}

	/**
	 * Simulates closing Doors
	 */
	public void closeDoors() {
		System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": Elevator " + this.getElevatorId() + " Doors Closing");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": Elevator " + this.getElevatorId() + " Doors Closed");
	}
	
	/**
	 * Simulates closing Doors
	 */
	public void openDoors() {
		System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": Elevator " + this.getElevatorId() + " Doors Opening");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) + ": Elevator " + this.getElevatorId() + " Doors opened");
	}
	
	
//	public void updateJob(ElevatorJob job) {
//		this.job = job;
//	}
	
	public void addJob(ElevatorJob job) {
		synchronized (jobs) {
			jobs.add(job);
			jobs.notify();
		}
	}
	
	public List<ElevatorJob> startFinishAllJobsInCurFloor() {
		synchronized (jobs) {
			synchronized(activeJobs) {
				List<ElevatorJob> removeJobs = new LinkedList<>();
				for(ElevatorJob job: jobs) {
					if(job.getFromFloor() == currentFloor) {
						removeJobs.add(job);
							activeJobs.add(job);
					}
				}
				List<ElevatorJob> removeActiveJobs = new LinkedList<>();
				for(ElevatorJob job: activeJobs) {
					if(job.getToFloor() == currentFloor) {
						removeActiveJobs.add(job);
					}
				}
				jobs.removeAll(removeJobs);
				activeJobs.removeAll(removeActiveJobs);
				return removeJobs;
			}
		}
	}
	
	
	
	/**
	 * Getters and seeters for terms in elevator below
	 */
	public String getTimer(){
        return timer;
    }
	
	public DoorStatus getDoorState() {
		return doorState;
	}
	
	//true = open
	//false = close
	public void setDoorState(boolean door) {
		if(door) {
			doorState = doorState.open;
		}
		if(!door) {
			doorState = doorState.closed;
		}
	}
	
	//True mean Operational
	public void setOperationalStatus(boolean temp) {
		if(temp){
			operationalStatus = temp;
		}
		if(!temp) {
			operationalStatus = temp;
		}
	}
	
	public boolean getOperationalStatus() {
		return operationalStatus;
	}
	

    public Direction getDirection() {
        return direction;
    }

    public int getCurrentFloor(){
        return currentFloor;
    }
    
    public ElevatorState getElevatorState() {
        return state;
    }
    
    public ElevatorSubsystem getElevatorSubsystem() {
    	return elevatorSubsystem;
    }

    public void setTimer(String timer){
        this.timer = timer;
    }

    public void setDirection(Direction direction) {
        this.direction =  direction;
    }

    public void setCurrentFloor(int curFlor){
        this.currentFloor =  curFlor;
    }
    
    
    public void setState(ElevatorState state) {
    	this.state = state;
    }
    
    public String getStateToString() {
    	if(state instanceof Stopped) {
    		return "Stopped";
    	}
    	if(state instanceof Moving) {
    		return "Moving";
    	}
    	return null;
    }
    
    
  

    

	/**
	 * @return the id
	 */
	public String getElevatorId() {
		return id;
	}
	
	public static void main(String[] args) {
		Elevator elevator = new Elevator(null, "1");
		elevator.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		elevator.addJob(new ElevatorJob("1", 1, 7, Direction.UP, 0));
		elevator.addJob(new ElevatorJob("1", 5, 4, Direction.DOWN, 0));
	}
    
}