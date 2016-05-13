package com.psbk.ws;

import com.psbk.ws.common.MasterConnection;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

import com.psbk.ws.common.MyMap;

@Path("/jadwal")
public class JadwalService extends MasterConnection{
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map getJadwalByID(@PathParam("id") String id){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("statusId", "1");
		result.put("message", "INQUIRY BERHASIL");
		System.out.println("id : "+id);
		try {
			createConnection();
			MyMap jadwal = (MyMap)jt.queryObject("select  r.nama as nama_ruangan,d.nama as nama_dosen, m.nama as nama_matpel, j.hari, j.jam "
					+ "from jadwal j, dosen d, matpel m, ruangan r "
					+ "where  j.id_matpel=m.id and j.id_dosen=d.id and j.id_ruangan=r.id and j.id = ?", 
					new Object[] {id}, new MyMap());
			closeConnection();
			if (jadwal != null){
				result.put("result", jadwal);
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
			
			data.put("id", request.getInt("id"));
			data.put("hari", request.getString("hari"));
			data.put("jam", request.getString("jam"));
			data.put("id_matpel", request.getString("id_matpel"));
			data.put("id_ruangan", request.getInt("id_ruangan"));
			data.put("id_dosen", request.getString("id_dosen"));
			
			jt.insert("jadwal", data);// insert ke tabel
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
	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Object updateMatpel(@Context HttpServletRequest hsr){
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
			
			data.put("id", request.getInt("id"));
			data.put("hari", request.getString("hari"));
			data.put("jam", request.getString("jam"));
			data.put("id_matpel", request.getString("id_matpel"));
			data.put("id_ruangan", request.getInt("id_ruangan"));
			data.put("id_dosen", request.getString("id_dosen"));
			
			jt.updateData( data,"jadwal");// insert ke tabel
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
	
	
	@GET
	@Path("/jadwalall")
	@Produces(MediaType.APPLICATION_JSON)
	public Map getJadwalAll(){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("statusId", "1");
		result.put("message", "INQUIRY BERHASIL");
		try {
			createConnection();
			List jadwal = (List)jt.queryList("select  r.nama as nama_ruangan,d.nama as nama_dosen, m.nama as nama_matpel, j.hari, j.jam "
					+ "from jadwal j, dosen d, matpel m, ruangan r "
					+ "where  j.id_matpel=m.id and j.id_dosen=d.id and j.id_ruangan=r.id ", new MyMap());
			closeConnection();
			if (jadwal != null){
				result.put("result", jadwal);
			}
		} catch (Exception e) {
			result.put("Message", "GAGAL KARENA : " +e.getMessage());
		}
		
		return result;
	}
	
	
	@GET
	@Path("/jadwalall/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map getJadwalAllByIDDosen(@PathParam("id") String id){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("statusId", "1");
		result.put("message", "INQUIRY BERHASIL");
		System.out.println("id : "+id);
		try {
			createConnection();
			List jadwal = (List)jt.queryList("select  r.nama as nama_ruangan,d.nama as nama_dosen, m.nama as nama_matpel, j.hari, j.jam "
					+ "from jadwal j, dosen d, matpel m, ruangan r "
					+ "where  j.id_matpel=m.id and j.id_dosen=d.id and j.id_ruangan=r.id and j.id_dosen = ?"
					, new Object[] {id}, new MyMap());
			closeConnection();
			if (jadwal != null){
				result.put("result", jadwal);
			}
		} catch (Exception e) {
			result.put("Message", "GAGAL KARENA : " +e.getMessage());
		}
		
		return result;
	}
}
