package com.intuit.elevator.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.intuit.elevator.controller.Controller;
import com.intuit.elevator.controller.Elevator;
import com.intuit.elevator.controller.Request;

//http://localhost:8080/RESTfulExample/rest/message/hello
@Path("/")
public class ElevatorService {

	final static Logger logger = Logger.getLogger(Elevator.class);
	
//	Start service with keyword start
	@POST
	@Path("/{param}")
	@Consumes("text/plain")
	public Response getMessage(@PathParam("param") String msg) {
		String result = "Restful service message : " + msg;
		if(msg.equals("start")) {
			if(Controller.getInstance().startService()==Boolean.TRUE)
				return Response.status(200).entity("Service started").build();
		}
		return Response.status(200).entity(result).build();
	}
	
// Accept elevator request from user POST JSON->Request
    @POST
    @Path("/request")
    @Consumes("application/json")
    @Produces("application/json")
    public Response consumeRequestJSON( Request r ) {
        Request retRequest = Controller.getInstance().addRequest(r);
        if(retRequest!=null)
        	return Response.status(200).entity(retRequest).build();
        return Response.status(400).entity(new Request(-1,-1)).build();
    }

// Show the top request in queue GET Request->JSON
    @GET
    @Path("/request/top")
    @Produces("application/json")
    public Request produceTopRequestJSON() {
    	return Controller.getInstance().peekTopRequest();
    }
	
//	Show the list of requests in queue GET Queue->JSON
    @GET
    @Path("/request/all")
    @Produces("application/json")
    public Object[] produceAllRequestsJSON() {
    	return Controller.getInstance().getAllRequests();
    }
}
