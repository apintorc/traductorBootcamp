package com.example.traductor.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
	private String user;
	private String password;
	private List<RoleDTO> rolesDTO;
	public UsuarioDTO(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}	
}