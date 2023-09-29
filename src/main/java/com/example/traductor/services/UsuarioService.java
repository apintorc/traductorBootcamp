package com.example.traductor.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
}
