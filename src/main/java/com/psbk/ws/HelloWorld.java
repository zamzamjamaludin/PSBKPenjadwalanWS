package com.psbk.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorld {
	
	@GET
	@Path("/getMessage")
	public Response getMessage(@QueryParam("msg") String msg1){
		
		String respon = "Hello World, Welcome "+msg1+" !!!!";
		
		return Response.status(200).entity(respon).build();
	}

}
