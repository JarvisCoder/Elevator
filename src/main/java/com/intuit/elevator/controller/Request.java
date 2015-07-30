package com.intuit.elevator.controller;

public class Request {
	private int from;
	private int to;
	
	public Request() {
		
	}
	
	public Request(int _from, int _to) {
		this.from=_from;
		this.to=_to;
	}

	public int getFrom() {
		return from;
	}

	protected void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	protected void setTo(int to) {
		this.to = to;
	}
	
	@Override
	public String toString() {
		return "From:" + this.from +" To:"+ this.to; 
	}
	
}
