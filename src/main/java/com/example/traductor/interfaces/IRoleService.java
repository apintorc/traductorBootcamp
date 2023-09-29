package com.example.traductor.interfaces;

import java.util.List;

import com.example.traductor.dto.RoleDTO;

public interface IRoleService {
	public RoleDTO getRole(String role);
	public List<RoleDTO> getRoles();
}
