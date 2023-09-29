package com.example.traductor.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
	private String role;
	private String funcion;
	
	private List<UsuarioDTO> usuariosDTO;

	public RoleDTO(String role, String funcion) {
		super();
		this.role = role;
		this.funcion = funcion;
	}

	public RoleDTO(String role) {
		super();
		this.role = role;
	}
	
	
}
