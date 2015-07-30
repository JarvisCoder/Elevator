package com.intuit.elevator.tests;

import org.junit.Assert;
import org.junit.Test;

import com.intuit.elevator.controller.Elevator;
import com.intuit.elevator.controller.Elevator.States;

public class ElevatorTests {

	Elevator e;
	
	@Test
	public void levelUpTest() throws InterruptedException {
		e = new Elevator();
		e.levelUp();
		Assert.assertTrue(e.getLevel()==2);
	}
	
	@Test
	public void levelDownTest() throws InterruptedException {
		e = new Elevator();
		e.levelUp();
		Assert.assertTrue(e.getLevel()==2);
		e.levelDown();
		Assert.assertTrue(e.getLevel()==1);
		Assert.assertTrue(Boolean.TRUE);
	}
	
	@Test
	public void closeDoorTest() throws InterruptedException {
		e = new Elevator();
		e.closeDoor();
		Assert.assertTrue(e.getCurrentState()==States.CLOSE);
	}
	
	@Test
	public void openDoorTest() throws InterruptedException {
		e = new Elevator();
		e.openDoor();
		Assert.assertTrue(e.getCurrentState()==States.OPEN);
	}
	
	@Test
	public void idleStateTest() throws InterruptedException {
		e = new Elevator();
		Assert.assertTrue(e.getCurrentState()==States.IDLE);
	}
}
