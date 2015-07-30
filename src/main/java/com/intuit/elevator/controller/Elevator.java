package com.intuit.elevator.controller;

import java.text.SimpleDateFormat;

public class Elevator implements Runnable {
	private int level;
	private States currentState;
	private int MAXLEVEL = 10;
	private int OPERATIONTIMER = 1000;
	public enum States {
		IDLE, UP, DOWN, OPEN, CLOSE
	}

	public Elevator() {
		level=0;
		setCurrentState(States.IDLE);
	}
	
	public int getLevel() {
		return level;
	}
	protected void setLevel(int _level) {
		this.level = _level;
	}
	public States getCurrentState() {
		return currentState;
	}

	public void setCurrentState(States currentState) {
		this.currentState = currentState;
	}
	
	void levelUp() throws InterruptedException {
		setCurrentState(States.UP);
		describeState();
		Thread.sleep(OPERATIONTIMER);
		this.setLevel(++this.level);
		setCurrentState(States.IDLE);
	}
	
	void levelDown() throws InterruptedException {
		setCurrentState(States.DOWN);
		describeState();
		Thread.sleep(OPERATIONTIMER);
		this.setLevel(--this.level);
		setCurrentState(States.IDLE);
	}
	void openDoor() throws InterruptedException {
		setCurrentState(States.OPEN);
		this.describeState();
		Thread.sleep(OPERATIONTIMER);
	}
	void closeDoor() throws InterruptedException {
		setCurrentState(States.CLOSE);
		this.describeState();
		Thread.sleep(OPERATIONTIMER);
	}
	void describeState() {
		java.util.Date date = new java.util.Date();
		SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = simpleDate.format(date);
		System.out.println(formattedDate  +" Level:" +this.getLevel() + " Status:"+this.getCurrentState());
	}
	
//	Runs on a thread to serve the top request
	void Serve() throws InterruptedException {
		while (true) {
			Request r = Controller.getInstance().topRequest();
			if(r!=null) {
				if(r.getFrom() == r.getTo() || r.getFrom() > MAXLEVEL || r.getTo() < 0) {
					System.out.println("Warning: Not a valid request. SKIP " + r);
				}
				else {
					System.out.println("PROCESS REQUEST " + r);
					Thread.sleep(OPERATIONTIMER);
					while(this.getLevel()!=r.getFrom()) {
						if (this.getLevel()>r.getFrom())
							this.levelDown();
						else if (this.getLevel()<r.getFrom())
							this.levelUp();
					}
					this.openDoor();
					this.closeDoor();
					while(this.getLevel()!=r.getTo()) {
						if (this.getLevel()>r.getTo())
							this.levelDown();
						else if (this.getLevel()<r.getTo())
							this.levelUp();
					}
					this.openDoor();
					this.closeDoor();
					System.out.println("COMPLETED "+r);
				}
			}
			else {
				System.out.println("No Requests. Wait 5 secs");
				Thread.sleep(5000);
			}
		}
	}

	public void run() {
		try {
			this.Serve();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
