package com.example.traductor.interfaces;

import com.example.traductor.dto.UsuarioDTO;
import com.example.traductor.model.Usuario;

public interface IUsuarioService {
	public UsuarioDTO login(String user, String password);
	public UsuarioDTO findUsuarioByUser(String user);

}
