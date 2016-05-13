package com.psbk.ws;

public class Matpel {
	private String id;
	private String nama;
	private int sks;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public int getSks() {
		return sks;
	}
	public void setSks(int sks) {
		this.sks = sks;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Matapelajaran : "+id+" - "+nama+" - "+sks;
	}
}
