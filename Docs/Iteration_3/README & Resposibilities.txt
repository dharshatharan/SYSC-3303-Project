SYSC_3303_Project

Authors:
Alexander Bhend,  101073223
Colin Crasta,  101115422
Dharshatharan Jayatharan Aronan, 101134019
Bailey Lyster,  101115419
Quinn Sondermeyer, 101132073



Iteration 3
Quick Explanation of each Class used for this iteration

FloorSchedulerCommunicator.java:
Used to communicate to the Scheduler from the Floor

SchdulerFloorCommunicator.java:
Used to communicate to the Floor from the Scheduler

SchedulerElevatorCommunicator.java:
Used to communicate to the Elevator from the Scheduler

ElevatorSchedulerCommunicator.java:
Used to communicate to the Scheduler from the Elevator

ElevatorSubsystem.java:
Used to manage multiple elevators

Elevator.java
Used to handle states for each elevator

Idle.java:
Implements an Idle state that instantiates a job.Trigger move state.

Moving.java:
State
Simulates a moving elevator and accelerates and decelerates when starting and stopping for floors.

Stopped.java:
State
Switched to this state from moving
Opens and closes doors. Triggers idle.

ElevatorState.java
Abstract Class that provides the states with the elevator context- 






Set up instructions for the ta:
1.	Open project (ensure the project is complete, input.txt needs to be in there folder)
2.	Run the following files in order
		i. Scheduler.java
		ii. FloorSubsystem.java
		iii. ElevatorSubsystem.java
3. 	Observe the console for the progress of the code as it executes.


Combined Effort: 
UML diagrams + State Diagrams
Refactoring old classes
Group revision of code and bug squashing.

Responsibilities Breakdown:


Dharshatharan:
-ReceiveSchedulerRequest.java
-Scheduler.java
-SchedulerElevatorCommunicator.java

Colin:
-idle.java
-UML Diagrams
-SchdulerFloorCommunicator.java

Alex:
-FloorSubsystem.java
-RequestElevatorEvent.java
-FloorSchedulerCommunicator.java:

Bailey:
-moving.java
-elevator.java refactoring
-ElevatorSchedulerCommunicator.java:

Quinn:
-stopped.java
-ElevatorState.java
-ElevatorSubsystem
-Lots of work towards refactoring and merging code















_______________________________________________________________________

Iteration 2
Quick Explanation of each Class used for this iteration
Since we've already implemented the Scheduler, in part in iteration 1, we've focused a lot on
refactoring and properly implementing the states. We've also coded in a manner that allows our code to
be integrated into future iterations easily!

Elevator.java:
refactored to have more getters and setters to allow the states to be used.

Idle.java:
Implements an Idle state that instantiates a job.Trigger move state.

Moving.java:
State
Simulates a moving elevator and accelerates and decelerates when starting and stopping for floors.

Stopped.java:
State
Switched to this state from moving
Opens and closes doors. Triggers idle.

SchedulerState.java
handles states the scheduler.


ReceiveRequestsandFaults.java


Fault.java
Built for future expansion in future iteration for errors.

ElevatorState.java
Abstract Class that provides the states with the elevator context- 






Set up instructions for the ta:
1.	Open project (ensure the project is complete, input.txt needs to be in there folder)
2.	Run ElevatorSystemSimulation.java
3. 	Observe the console for the progress of the code as it executes.


Combined Effort: 
UML diagrams + State Diagrams
Refactoring old classes
Group revision of code and bug squashing.

Responsibilities Breakdown:


Dharshatharan:
-ReceiveSchedulerRequest.java
-SchedulerState.java
-ProcessFault.java
-fault.java

Colin:
-idle.java
-UML Diagrams
-Elevator.java

Alex:
-FloorSubsystem.java
-RequestElevatorEvent.java

Bailey:
-moving.java
-elevator.java refactoring
-ReadMe.txt iteration2

Quinn:
-stopped.java
-ElevatorState.java
-Lots of work towards refactoring and merging code















_______________________________________________________________________

Iteration 1
Quick explanation of eachh class used for this iteration:

ElevatorSystemSimulation:
Creates the instances of the the three threads, one for each subsytem.
Also informs us that the program is starting.

ElevatorTest:
Used for JUnit testing. 

Scheduler:
This class acts as the server which connects the elevator and floor 
subsystems. 

FloorSubsystem: 
Parses the input file and handles the jobs of different floors. Also 
sends jobs to the scheduler.

RequestElevatorEvent: 
Stores the information regarding calling a elevator. Information stored
includes time and floornumbers.

Elevator: Handles the operations involving elevators. Gets jobs from the 
scheduler and executes the jobs.

ElevatorInfo: Basic information about each elevator job. Information 
includes direction and floor to pickup the passenger.

ElevatorJob: Stores information regarding the jobs it given like the 
floor is needs to got to and direction it needs to go to.

Direction: Enum that states the direction

ElevatorState: Enum that reports the state of the elevator regarding 
its motion 

FloorNumber: Enum about the floor number

Set up instructions for the ta:
1. Open the program in Eclipse
2. Run ElevatorSystemSimulation for the actual program and ElevatorTests for the single test case required.

Responsibilities Breakdown

Dharshatharan:
-Elevator.java
-ElevatorInfo.java
-ElevatorJob.java

Alexander:
-FloorSubsystem.java
-RequestElevatorEvent.java

Quinn:
-ElevatorTest.java
-ElevatorSystemSimulation.java

Colin:
-Scheduler.java
-ElevatorState.java
-FloorNumber.java
-Direction.java

Bailey:
-Door.java
-Other classes omitted from Iteration 1 due to quick last minute restructuring of 
the class diagram since we did more than what was asked for this iteration. His 
classes will be used for future iterations due to this.
-Helped with README.txt


Combined effort:
-UML class diagram
-UML sequence diagram
