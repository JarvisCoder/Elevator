package com.intuit.elevator.tests;

import org.junit.Assert;
import org.junit.Test;

import com.intuit.elevator.controller.Controller;
import com.intuit.elevator.controller.Request;

public class ControllerTests {
	
	@Test
	public void addRequestTest() {
		Controller.getInstance().addRequest(new Request(1,10));
		Assert.assertNotNull(Controller.getInstance().topRequest());
		Controller.getInstance().topRequest();
	}
	
	@Test
	public void peekRequestTest() {
		Controller.getInstance().addRequest(new Request(1,10));
		Request r = Controller.getInstance().peekTopRequest();
		Assert.assertNotNull(r);
		Assert.assertTrue((new Request(1,10)).equals(r));
		Controller.getInstance().topRequest();
	}

	@Test
	public void getRequestsTest() {
		Controller.getInstance().addRequest(new Request(1,10));
		Object[] requestList = Controller.getInstance().getAllRequests();
		Object[] actualList = {new Request(1,10)};
		Assert.assertArrayEquals(actualList, requestList);
		Controller.getInstance().topRequest();
	}
	
	@Test
	public void topRequestTest() {
		Controller.getInstance().addRequest(new Request(1,10));
		Assert.assertNotNull(Controller.getInstance().topRequest());
		Controller.getInstance().topRequest();
		Assert.assertNull(Controller.getInstance().topRequest());
	}

}
