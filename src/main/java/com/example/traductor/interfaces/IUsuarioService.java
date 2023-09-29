package com.example.traductor.interfaces;

import java.util.List;

import com.example.traductor.dto.RoleDTO;
import com.example.traductor.dto.UsuarioDTO;

public interface IUsuarioService {
	public UsuarioDTO login(String user, String password);
	public List<UsuarioDTO> getUsuarios();
	public List<RoleDTO> getRolesByUser(String user);
	public boolean deleteUsuario(String user);
	public void deleteRole(String user, String rol);
	public UsuarioDTO findUsuarioByUser(String user);
	public void updateUsuarioByUser(UsuarioDTO usuarios);
	public void addRole(UsuarioDTO usuarioDTO, RoleDTO role);
}
