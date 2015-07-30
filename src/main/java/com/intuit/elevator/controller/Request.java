package com.intuit.elevator.controller;

public class Request {
	private int from;
	private int to;
	private String description;
	
	public Request(int _from, int _to) {
		this.from=_from;
		this.to=_to;
		this.description="";
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

	public String getDescription() {
		return description;
	}

	protected void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "From:" + this.from +" To:"+ this.to; 
	}
	
}
