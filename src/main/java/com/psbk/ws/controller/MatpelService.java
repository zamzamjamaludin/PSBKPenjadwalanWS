package com.psbk.ws.controller;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.util.HashMap;
import java.util.Map;

import com.psbk.ws.common.MasterConnection;
import com.psbk.ws.common.MyMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;

@Path("/matpel")
public class MatpelService extends MasterConnection{
		
//	@GET
//	@Path("/get")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Student getStudent(){
//		Student s = new Student();
//		
//		s.setId("143040001");
//		s.setName("John Doe");
//		s.setAddress("Gotham City");
//		
//		return s;
//	}
//	
//	@POST
//	@Path("/create")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response createStudent(Student student){
//		String respon = "Data Saved : "+student;
//		
//		return Response.status(201).entity(respon).build();
//	}
	
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map getMatpelByID(@PathParam("id") String id){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("statusId", "1");
		result.put("message", "INQUIRY BERHASIL");
		System.out.println("id : "+id);
		try {
			createConnection();
			MyMap matpel = (MyMap)jt.queryObject("select * from matpel where  id = ?", new Object[] {id}, new MyMap());
			closeConnection();
			if (matpel != null){
				result.put("result", matpel);
			}
		} catch (Exception e) {
			result.put("Message", "GAGAL KARENA : " +e.getMessage());
		}
		
		return result;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/insert")
	public Object createMatpel(@Context HttpServletRequest hsr){
		StringBuffer sb = new StringBuffer();
		String line = null;
		JSONObject request = null;
		MyMap respon = new MyMap();
		MyMap data = new MyMap();
		DataInputStream in;
		
		try {
			createConnection();
			in = new DataInputStream(new BufferedInputStream(hsr.getInputStream()));
			
			while ((line = in.readLine()) != null)
				sb.append(line);
			JSONObject json = new JSONObject(sb.toString());
			request = (JSONObject) json.get("request");
			
			if(request == null){
				respon.put("message", "Data yang dikirim tidak ditemukan");
				respon.put("rCode", "99");
				respon.put("statusId", "0");
				
				return respon;
			}
			
			data.put("id", request.getString("id"));
			data.put("nama", request.getString("nama"));
			data.put("sks", request.getInt("sks"));
			
			jt.insert("matpel", data);// insert ke tabel
			respon.put("message", "DATA BERHASIL DISIMPAN");
			respon.put("rCode", "00");
			respon.put("statusId", "1");
			
		} catch (Exception e) {
			e.printStackTrace();
			respon.put("message", e.getMessage());
			respon.put("rCode", "99");
			respon.put("statusId", "0");
		}
		return respon;
	}
}
