package com.psbk.ws;

public class Jadwal {

	private int id;
	private String hari;
	private String jam;
	private String id_matpel;
	private int id_ruangan;
	private String id_dosen;
	private char kelas;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHari() {
		return hari;
	}
	public void setHari(String hari) {
		this.hari = hari;
	}
	public String getJam() {
		return jam;
	}
	public void setJam(String jam) {
		this.jam = jam;
	}
	public String getId_matpel() {
		return id_matpel;
	}
	public void setId_matpel(String id_matpel) {
		this.id_matpel = id_matpel;
	}
	public int getId_ruangan() {
		return id_ruangan;
	}
	public void setId_ruangan(int id_ruangan) {
		this.id_ruangan = id_ruangan;
	}
	public String getId_dosen() {
		return id_dosen;
	}
	public void setId_dosen(String id_dosen) {
		this.id_dosen = id_dosen;
	}
	public char getKelas() {
		return kelas;
	}
	public void setKelas(char kelas) {
		this.kelas = kelas;
	}
	
	
}
