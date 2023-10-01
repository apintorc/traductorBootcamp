package com.example.traductor.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {

	@Id
	private int id_categoria;
	private String descripcion;
	@OneToMany(cascade=CascadeType.ALL)
	//Lazyload
	@JoinColumn(name="id_categoria",referencedColumnName ="id_categoria" )
	private List<Palabra> palabras;
	public Categoria() {
		super();
	}
	public Categoria(int id_categoria, String descripcion) {
		super();
		this.id_categoria = id_categoria;
		this.descripcion = descripcion;
	}
	
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<Palabra> getPalabras() {
		return palabras;
	}
	public void setPalabras(List<Palabra> palabras) {
		this.palabras = palabras;
	}
	
	
	
}
