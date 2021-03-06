package com.psbk.ws.controller;

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
			MyMap jadwal = (MyMap)jt.queryObject("select  m.id as kode_matkul,r.nama as nama_ruangan,"
					+ "d.nama as nama_dosen, m.nama as nama_matpel, j.hari, j.jam , j.kelas "
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
	public Object createJadwal(@Context HttpServletRequest hsr){
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
			data.put("kelas", request.getString("kelas"));
			
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
	public Object updateJadwal(@Context HttpServletRequest hsr){
		StringBuffer sb = new StringBuffer();
		String line = null;
		JSONObject request = null;
		MyMap respon = new MyMap();
		//MyMap data = new MyMap();
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
			
//			data.put("id", request.getInt("id"));
//			data.put("hari", request.getString("hari"));
//			data.put("jam", request.getString("jam"));
//			data.put("id_matpel", request.getString("id_matpel"));
//			data.put("id_ruangan", request.getInt("id_ruangan"));
//			data.put("id_dosen", request.getString("id_dosen"));
			
			jt.update("update  jadwal set hari = '"+request.getString("hari")+"'"
					+ ", jam = '"+request.getString("jam")+"', id_matpel = '"+request.getString("id_matpel")+"',"
					+ "id_ruangan = '"+request.getInt("id_ruangan")+"', id_dosen = '"
					+request.getString("id_dosen")+"' , kelas='"+request.getString("kelas")+"'"
					+ "where id = '"+request.getInt("id")+"'");// insert ke tabel
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
			List jadwal = (List)jt.queryList("select  m.id as kode_matkul, r.nama as nama_ruangan,"
					+ "d.nama as nama_dosen, m.nama as nama_matpel, j.hari, j.jam , j.kelas "
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
			List jadwal = (List)jt.queryList("select  m.id as kode_matkul, r.nama as nama_ruangan,"
					+ "d.nama as nama_dosen, m.nama as nama_matpel, j.hari, j.jam ,j.kelas "
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
	
	
	@GET
	@Path("/mahasiswa/{nrp}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map getJadwalByNRP(@PathParam("nrp") String nrp){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("statusId", "1");
		result.put("message", "INQUIRY BERHASIL");
		System.out.println("nrp : "+nrp);
		try {
			createConnection();
			List jadwalMahasiswa = (List)jt.queryList("select  m.id as kode_matkul,r.nama as nama_ruangan,"
					+ "d.nama as nama_dosen, m.nama as nama_matpel, j.hari, j.jam , j.kelas, jmhs.nrp, mhs.nama "
					+ "from jadwal j, dosen d, matpel m, ruangan r , jadwal_mahasiswa jmhs, mahasiswa mhs "
					+ "where  j.id_matpel=m.id and j.id_dosen=d.id and j.id_ruangan=r.id and "
					+ "jmhs.id_jadwal = j.id and jmhs.nrp=mhs.nrp and jmhs.nrp = ?", 
					new Object[] {nrp}, new MyMap());
			closeConnection();
			if (jadwalMahasiswa != null){
				result.put("result", jadwalMahasiswa);
			}
		} catch (Exception e) {
			result.put("Message", "GAGAL KARENA : " +e.getMessage());
		}
		
		return result;
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/insertMahasiswa")
	public Object createJadwalMhs(@Context HttpServletRequest hsr){
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
			
			data.put("id_jadwal", request.getInt("id_jadwal"));
			data.put("nrp", request.getString("nrp"));
			
			
			jt.insert("jadwal_mahasiswa", data);// insert ke tabel
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
	@Path("/updateMahasiswa")
	public Object updateJadwalMahasiswa(@Context HttpServletRequest hsr){
		StringBuffer sb = new StringBuffer();
		String line = null;
		JSONObject request = null;
		MyMap respon = new MyMap();
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
			

			jt.update("update  jadwal_mahasiswa set id_jadwal = '"+request.getInt("id_jadwal_baru")+"', "
					+" nrp = '"+request.getString("nrp_baru")+"'"
					+ "where nrp = '"+request.getString("nrp_awal")+"' and id_jadwal = '"+request.getInt("id_jadwal_awal")+"'");// insert ke tabel
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
