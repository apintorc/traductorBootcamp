package com.example.traductor.dto;

public class PalabraDTO {
	private String id_palabra;
	private String original;
	private String traduccionSP;
	private String traduccionIN;
	private String traduccionFR;
	private CategoriaDTO categoria;
	
	
	public PalabraDTO() {
		super();
	}
	public PalabraDTO(String id_palabra, String original, String traduccionSP, String traduccionIN, String traduccionFR,
			CategoriaDTO categoria) {
		super();
		this.id_palabra = id_palabra;
		this.original = original;
		this.traduccionSP = traduccionSP;
		this.traduccionIN = traduccionIN;
		this.traduccionFR = traduccionFR;
		this.categoria = categoria;
	}
	public String getId_palabra() {
		return id_palabra;
	}
	public void setId_palabra(String id_palabra) {
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
	public CategoriaDTO getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

	
}
