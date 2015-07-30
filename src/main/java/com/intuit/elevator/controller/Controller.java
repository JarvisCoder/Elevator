package com.intuit.elevator.controller;

import java.util.LinkedList;
import java.util.Queue;


public class Controller {
	
	private static Controller instance = null;
	
	protected Queue<Request> requests = new LinkedList<Request>();
	
	protected Controller() {
//	      Exists only to defeat instantiation.
	}
	   
	public static Controller getInstance() {
		if(instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	
	public void addRequest(Request r) {
		if(r!=null)
			requests.add(r);
	}
	
	public Request topRequest() {
		if(requests.size()==0)
			return null;
		else
			return requests.remove();
	}
	
	public void listRequests() {
		System.out.println(requests.toString());
	}

}
