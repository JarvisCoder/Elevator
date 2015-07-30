package com.intuit.elevator.controller;

public class Start {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("Application Elevator");
		
		(new Thread(new Elevator())).start();
		
		Thread.sleep(7000);
		Controller.getInstance().addRequest(new Request(3,5));
		Controller.getInstance().addRequest(new Request(5,5));
		Controller.getInstance().addRequest(new Request(9,1));
		Controller.getInstance().addRequest(new Request(1,3));
		Controller.getInstance().listRequests();
		Thread.sleep(7000);
		Controller.getInstance().addRequest(new Request(1,3));
		Controller.getInstance().addRequest(new Request(5,5));
		Controller.getInstance().addRequest(new Request(1,3));
		Controller.getInstance().addRequest(new Request(0,0));
		Controller.getInstance().listRequests();
		
	}

}
