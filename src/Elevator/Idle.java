package Elevator;

import Constants.Direction;
import Floor.RequestElevatorEvent;

public class Idle extends ElevatorState{


    private RequestElevatorEvent job;

    public Idle(Elevator elevator) {
        super(elevator);


    }

    @Override
    public void enter() {

        while (this.job != null) {
            try { 
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt(); 
            }
            this.job = elevator.getJob();
        }
        startJob();

    }

    public void requestJob(RequestElevatorEvent job) {
        while (this.job != null) {
            try { 
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt(); 
            }
        }
        this.job = job;
        notifyAll();
    }

    public void startJob() {
        elevator.setJob(elevator.getJob()); 
        elevator.setTimer();				
        elevator.setDirection();
        elevator.setcurFlor();
        elevator.setDestination();
        System.out.println("test");
        exit();
        //notifyAll();
    }

    @Override
    public void exit() {
        elevator.setState(new Moving(elevator));
        elevator.getState().enter();
        notifyAll();
        

    }

}