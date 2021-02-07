SYSC_3303_Project

Authors:
Alexander Bhend,  101073223
Colin Crasta,  101115422
Dharshatharan Jayatharan Aronan, 101134019
Bailey Lyster,  101115419
Quinn Sondermeyer, 101132073

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





