SYSC_3303_Project

Authors:
Alexander Bhend,  101073223
Colin Crasta,  101115422
Dharshatharan Jayatharan Aronan, 101134019
Bailey Lyster,  101115419
Quinn Sondermeyer, 101132073


Iteration 4

This iteration we mainly on made changes to existing classes to include logic for faults.
The only class we added is ElevatorTimer.java. We also removed some classes that we're being used.



Set up instructions for the ta:
1.	Open project (ensure the project is complete, input.txt needs to be in the project root folder)
2.	Run the following files in order
		i. Scheduler.java
		ii. FloorSubsystem.java
		iii. ElevatorSubsystem.java
3. 	Observe the console for the progress of the code as it executes.


Combined Effort: 
UML diagrams + Sequence Diagrams
Group revision of code and bug squashing.

Responsibilities Breakdown:


Dharshatharan:
- Cleaning up iteration 3 logic
- Worked on majority of the refactoring of code to be able to trigger faults in the right areas
- Refactored the code to change the way we were processing jobs, our previous model didn't allow for doing multiple jobs at a time and also being efficient in terms of where to stop first
- Made the scheduling logic more efficient

Colin:
- Set up a protocol (starting from the input.txt file) to trigger this fault in the elevator
- Set up one timer per elevator
- Start the timer for the specific elevator in ProcessElevatorInfoThread class in the processElevatorInfo() function


Alex:
- Set up a protocol (starting from the input.txt file) to trigger this fault in the elevator
- Set up a timer when both opening and closing the door
- Cancel the timer if the door closes in time
- Create a function that the timer will call in the case of a timeout

Bailey:
- Handle the timeout case (If the door fails to open, try closing and opening again (and vice versa) a total of 2 times excluding the fault. If the door is still not able to close, shutdown the elevator and send a notification to the scheduler)
- Create a protocol to communicate the door failure and shutdown
- Communicate the shutdown to the floor subsystem

Quinn:
- Have the timer call a function that will deal with the fault
- The fault should be handled by shutting down the respective elevator (this should be done by sending a notification to the respective elevator) and also marking it as shutdown in the scheduler
- The elevator needs a shutdown field by getting all threads related to that elevator to check if shutdown constantly (refer to lectures)
- Send shutdown notification to floor
