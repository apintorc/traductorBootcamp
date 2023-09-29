package com.example.traductor.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.traductor.dto.RoleDTO;
import com.example.traductor.interfaces.IRoleService;
import com.example.traductor.model.Role;
import com.example.traductor.model.Usuario;
import com.example.traductor.repository.RoleRepository;
import com.example.traductor.repository.UsuarioRepository;

@Service
public class RoleService implements IRoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public RoleDTO getRole(String role) {
		Role rol = roleRepository.findById(role).orElse(null);
		RoleDTO roleDTO = null;
		if(rol != null) {
			roleDTO = new RoleDTO(rol.getRole(), rol.getFuncion());
		}
		return roleDTO;
	}

	@Override
	public List<RoleDTO> getRoles() {
		List<Role> roles = roleRepository.findAll();
		List<RoleDTO> rolesDTO = new ArrayList<RoleDTO>();
		for (Role role : roles) {
			RoleDTO roleDTO = new RoleDTO(role.getRole(), role.getFuncion());
			rolesDTO.add(roleDTO);
		}
		return rolesDTO;
	}

}
