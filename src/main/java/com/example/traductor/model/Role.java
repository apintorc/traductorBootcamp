package com.example.traductor.model;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "roles")
public class Role {
	@Id
	private String role;
	private String funcion;
	
	@ManyToMany
	@JoinTable(name = "usuario_role", joinColumns = @JoinColumn(name = "role"), inverseJoinColumns = @JoinColumn(name = "usuario"))
	private Set<Usuario> usuarios;

	public Role() {
		super();
	}

	public Role(String role, String funcion) {
		super();
		this.role = role;
		this.funcion = funcion;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
