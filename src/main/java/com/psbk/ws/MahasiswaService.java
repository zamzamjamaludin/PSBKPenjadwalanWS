package com.psbk.ws;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

import com.psbk.ws.common.MasterConnection;
import com.psbk.ws.common.MyMap;

@Path("/mahasiswa")
public class MahasiswaService extends MasterConnection{

	@GET
	@Path("{nrp}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map getMahasiswaByNRP(@PathParam("nrp") String nrp){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("statusId", "1");
		result.put("message", "INQUIRY BERHASIL");
		System.out.println("nrp : "+nrp);
		try {
			createConnection();
			MyMap mahasiswa = (MyMap)jt.queryObject("select * from mahasiswa where  nrp = ?", new Object[] {nrp}, new MyMap());
			closeConnection();
			if (mahasiswa != null){
				result.put("result", mahasiswa);
			}
		} catch (Exception e) {
			result.put("Message", "GAGAL KARENA : " +e.getMessage());
		}
		
		return result;
	}
	
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("/insert")
//	public Object createMatpel(@Context HttpServletRequest hsr){
//		StringBuffer sb = new StringBuffer();
//		String line = null;
//		JSONObject request = null;
//		MyMap respon = new MyMap();
//		MyMap data = new MyMap();
//		DataInputStream in;
//		
//		try {
//			createConnection();
//			in = new DataInputStream(new BufferedInputStream(hsr.getInputStream()));
//			
//			while ((line = in.readLine()) != null)
//				sb.append(line);
//			JSONObject json = new JSONObject(sb.toString());
//			request = (JSONObject) json.get("request");
//			
//			if(request == null){
//				respon.put("message", "Data yang dikirim tidak ditemukan");
//				respon.put("rCode", "99");
//				respon.put("statusId", "0");
//				
//				return respon;
//			}
//			
//			data.put("nrp", request.getString("nrp"));
//			data.put("nama", request.getString("nama"));
//			data.put("alamat", request.getString("alamat"));
//			
//			jt.insert("mahasiswa", data);// insert ke tabel
//			respon.put("message", "DATA BERHASIL DISIMPAN");
//			respon.put("rCode", "00");
//			respon.put("statusId", "1");
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			respon.put("message", e.getMessage());
//			respon.put("rCode", "99");
//			respon.put("statusId", "0");
//		}
//		return respon;
//	}
	
	@GET
	@Path("/mhsall")
	@Produces(MediaType.APPLICATION_JSON)
	public Map getMahasiswaAll(@PathParam("id") String id){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("statusId", "1");
		result.put("message", "INQUIRY BERHASIL");
		System.out.println("id : "+id);
		try {
			createConnection();
			List mhs = (List)jt.queryList("select nama from mahasiswa", new MyMap());
			closeConnection();
			if (mhs != null){
				result.put("result", mhs);
			}
		} catch (Exception e) {
			result.put("Message", "GAGAL KARENA : " +e.getMessage());
		}
		
		return result;
	}
}
