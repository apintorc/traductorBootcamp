package com.example.traductor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="palabra")
public class Palabra {
	@Id
	@Column(name="id_palabra")
	private String id_palabra;
	private String original;
	private String traduccionSP;
	private String traduccionIN;
	private String traduccionFR;
	
	public Palabra() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Palabra(String id_idioma, String original, String traduccionSP, String traduccionIN, String traduccionFR) {
		super();
		this.id_palabra = id_idioma;
		this.original = original;
		this.traduccionSP = traduccionSP;
		this.traduccionIN = traduccionIN;
		this.traduccionFR = traduccionFR;
	}

	public String getId_Palabra() {
		return id_palabra;
	}
	public void setId_Palabra(String id_palabra) {
		this.id_palabra = id_palabra;
	}
	public String getOriginal() {
		return original;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	public String getTraduccionSP() {
		return traduccionSP;
	}
	public void setTraduccionSP(String traduccionSP) {
		this.traduccionSP = traduccionSP;
	}
	public String getTraduccionIN() {
		return traduccionIN;
	}
	public void setTraduccionIN(String traduccionIN) {
		this.traduccionIN = traduccionIN;
	}
	public String getTraduccionFR() {
		return traduccionFR;
	}
	public void setTraduccionFR(String traduccionFR) {
		this.traduccionFR = traduccionFR;
	}
	
}
