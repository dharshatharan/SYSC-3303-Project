//package Scheduler;
//
///**
// * Scheduler State for processing Faults found in the system
// * @author Dharsh
// * @Version 02/27/2021
// */
//public class ProcessFault extends SchedulerState {
//
//	/**
//	 * Default constructor calling parent class
//	 */
//	public ProcessFault(Scheduler context) {
//		super(context);
//	}
//	/**
//	 * Starts Task to Process Faults
//	 */
//	@Override
//	public void enter() {
//		System.out.println("---------------------Scheduler State changed to: PROCESS FAULT---------------------");
//		processFault(context.getFault());
//		exit();
//	}
//	/**
//	 * will handle flaws found in the system
//	 * @param fault
//	 */	
//	public void processFault(Fault fault) {
//		
//	}
//	/**
//	 * Exits state and switches to RecieveRequestsAndFaults
//	 */
//	@Override
//	public void exit() {
//		context.setState(new ReceiveRequestsAndFaults(context));
//		context.getState().enter();
//	}
//
//}
