package com.psbk.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/student")
public class StudentService {
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(){
		Student s = new Student();
		
		s.setId("143040001");
		s.setName("John Doe");
		s.setAddress("Gotham City");
		
		return s;
	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createStudent(Student student){
		String respon = "Data Saved : "+student;
		
		return Response.status(201).entity(respon).build();
	}

}
