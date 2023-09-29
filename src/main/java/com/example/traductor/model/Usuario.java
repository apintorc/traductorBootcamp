package com.example.traductor.model;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {
	@Id
	@Column(name="user")
	private String user;
	private String password;
	@ManyToMany
	@JoinTable(name = "usuario_role", joinColumns = @JoinColumn(name = "usuario"), inverseJoinColumns = @JoinColumn(name = "role"))
	private Set<Role> roles;
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Usuario(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
