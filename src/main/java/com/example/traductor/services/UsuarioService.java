package com.example.traductor.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.misc.Array2DHashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.traductor.dto.RoleDTO;
import com.example.traductor.model.Role;
import com.example.traductor.model.Usuario;
import com.example.traductor.dto.UsuarioDTO;
import com.example.traductor.interfaces.IUsuarioService;
import com.example.traductor.repository.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO login(String user, String password) {
        // Busca al usuario por nombre de usuario en la base de datos
        UsuarioDTO usuarioDTO = findUsuarioByUser(user);

        if (usuarioDTO != null && usuarioDTO.getPassword().equals(password)) {
            // Si se encuentra un usuario con el nombre de usuario proporcionado
            // y la contraseña coincide, entonces se considera una autenticación exitosa
            return usuarioDTO;
        } else {
            // Si no se encuentra el usuario o la contraseña no coincide, retorna null
            return null;
        }
    }
    

	
	@Override
	public List<UsuarioDTO> getUsuarios() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
		
		for (Usuario usuario : usuarios) {
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getUser(), usuario.getPassword());
			usuariosDTO.add(usuarioDTO);
		}
		
		return usuariosDTO;
	}

	@Override
	public List<RoleDTO> getRolesByUser(String user) {
		Usuario usuario = usuarioRepository.findById(user).orElse(null);
		List<RoleDTO> rolesDTO = new ArrayList<RoleDTO>();
		if(usuario != null) {
			List<Role> roles = new ArrayList<Role>(usuario.getRoles());
			for (Role role : roles) {
				RoleDTO roleDTO = new RoleDTO(role.getRole(), role.getFuncion());
				rolesDTO.add(roleDTO);
			}
		}
		return rolesDTO;
	}

	@Override
	public boolean deleteUsuario(String user) {
		/*
		Usuario usuario = usuarioRepository.findById(user).orElse(null);
		if(usuario != null) {
			if(!usuario.getRoles().isEmpty()) {
				return false;
			} else {
				usuarioRepository.deleteById(user);
				return true;
			}
		} else {
			return false;
		}*/
		
		try {
			if(usuarioRepository.findById(user).orElse(null).getRoles().size() > 0) {
				return false;
			} else {
				usuarioRepository.deleteById(user);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void deleteRole(String user, String role) {
		//usuarioRepository.deleteRol(user, role);
		Usuario usuario = usuarioRepository.findById(user).orElse(null);
		//Role rol = new Role(role.getRole(), role.getFuncion());
		
		if(usuario != null) {
			//boolean eliminado = usuario.getRoles().remove(rol);
			List<Role> roles = new ArrayList<Role>(usuario.getRoles());
			for (Role rol : roles) {
				if(rol.getRole().equals(role)) {
					 usuario.getRoles().remove(rol);
				}
			}			
			usuarioRepository.save(usuario);
		}
	}

	@Override
	public UsuarioDTO findUsuarioByUser(String user) {
		Usuario usuario = usuarioRepository.findById(user).orElse(null);
		UsuarioDTO usuarioDTO = null;
		if(usuario != null) {
			usuarioDTO = new UsuarioDTO(usuario.getUser(),  usuario.getPassword());
			List<RoleDTO> rolesDTO = new ArrayList<RoleDTO>();
			Set<Role> roles = usuario.getRoles();
			if(roles != null) {
				for (Role role : roles) {
					RoleDTO roleDTO = new RoleDTO(role.getRole(), role.getFuncion());
					rolesDTO.add(roleDTO);
				}
				usuarioDTO.setRolesDTO(rolesDTO);
			}
		}
		return usuarioDTO;
	}
	
	@Override
	public void updateUsuarioByUser(UsuarioDTO usuarioDTO) {
		Usuario u = new Usuario(usuarioDTO.getUser(), usuarioDTO.getPassword());
		List<RoleDTO> rolesDTO = usuarioDTO.getRolesDTO();
		Set<Role> roles = new Array2DHashSet<Role>();
		if(rolesDTO != null) {
			for (RoleDTO roleDTO : rolesDTO) {
				Role role = new Role(roleDTO.getRole(), roleDTO.getFuncion());
				roles.add(role);
			}
			u.setRoles(roles);
		}
		usuarioRepository.save(u);
	}

	@Override
	public void addRole(UsuarioDTO usuarioDTO, RoleDTO role) {
		List<RoleDTO> rolesDTO = usuarioDTO.getRolesDTO();
		rolesDTO.add(role);
		usuarioDTO.setRolesDTO(rolesDTO);
		
		updateUsuarioByUser(usuarioDTO);
	}
}
