package com.intuit.elevator.controller;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Logger;


public class Controller {
	
	final static Logger logger = Logger.getLogger(Controller.class);
	
	private static Controller instance = null;
	
	protected Queue<Request> requests = new LinkedList<Request>();
	
	protected Thread elevator_service = (new Thread(new Elevator()));
	
	protected Controller() {
//	      Exists only to defeat instantiation.
	}
	   
	public static Controller getInstance() {
		if(instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	
	public boolean startService() {
		if(!elevator_service.isAlive()) {
			logger.info("Starting service...");
			elevator_service.start();
			logger.info("Started "+elevator_service.getName());
			return true;
		}
		logger.warn("Service already started");
		return false;
	}
	
	public Request addRequest(Request r) {
		if(r!=null && r.getFrom() > 0 && r.getFrom() < Elevator.MAXLEVEL && r.getTo() > 0 && r.getTo() < Elevator.MAXLEVEL) {
			requests.add(r);
			return requests.peek();
		}
		return null;
	}
	
	public Request topRequest() {
		if(requests.size()==0)
			return null;
		else
			return requests.remove();
	}
	
	public void listRequests() {
		logger.info(requests.toString());
	}
	
	public Object[] getAllRequests() {
		listRequests();
		return requests.toArray();
	}
	
	public Request peekTopRequest() {
		if(requests.size()==0)
			return null;
		return requests.peek();
	}

}
