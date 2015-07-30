package com.intuit.elevator.controller;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.intuit.elevator.service.ElevatorService;


public class MessageApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();

	public MessageApplication() {
		singletons.add(new ElevatorService());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}

