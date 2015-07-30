package com.intuit.elevator.controller;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

public class Elevator implements Runnable {
	
	final static Logger logger = Logger.getLogger(Elevator.class);
	
	private int level;
	private States currentState;
	public static int MAXLEVEL = 30;
	private int OPERATIONTIMER = 1000;
	public enum States {
		IDLE, UP, DOWN, OPEN, CLOSE
	}
	
	public Elevator() {
		level=1;
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
	
	public void levelUp() throws InterruptedException {
		setCurrentState(States.UP);
		describeState();
		Thread.sleep(OPERATIONTIMER);
		this.setLevel(++this.level);
		setCurrentState(States.IDLE);
	}
	
	public void levelDown() throws InterruptedException {
		setCurrentState(States.DOWN);
		describeState();
		Thread.sleep(OPERATIONTIMER);
		this.setLevel(--this.level);
		setCurrentState(States.IDLE);
	}
	public void openDoor() throws InterruptedException {
		setCurrentState(States.OPEN);
		this.describeState();
		Thread.sleep(OPERATIONTIMER);
	}
	public void closeDoor() throws InterruptedException {
		setCurrentState(States.CLOSE);
		this.describeState();
		Thread.sleep(OPERATIONTIMER);
	}
	void describeState() {
		java.util.Date date = new java.util.Date();
		SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = simpleDate.format(date);
		logger.info(formattedDate  +" Level:" +this.getLevel() + " Status:"+this.getCurrentState());
	}
	
//	Runs on a thread to serve the top request
	void Serve() throws InterruptedException {
		while (true) {
			Request r = Controller.getInstance().topRequest();
			if(r!=null) {
				if(r.getFrom() == r.getTo() || r.getFrom() > MAXLEVEL || r.getTo() > MAXLEVEL || r.getTo() < 0 || r.getFrom() < 0) {
					logger.warn("Not a valid request. SKIP " + r);
				}
				else {
					logger.info("PROCESS REQUEST " + r);
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
					logger.info("COMPLETED "+r);
				}
			}
			else {
				logger.info(Thread.currentThread().getName() + ": No Requests. Wait 5 secs");
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
