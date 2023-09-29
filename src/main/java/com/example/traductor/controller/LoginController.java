package com.example.traductor.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.traductor.dto.UsuarioDTO;
import com.example.traductor.dto.RoleDTO;
import com.example.traductor.interfaces.IRoleService;
import com.example.traductor.interfaces.IUsuarioService;
import com.example.traductor.model.Usuario;
import com.example.traductor.util.Usuario_v;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	IUsuarioService usuarioService;
	
	@Autowired
	IRoleService roleService;
	
	@GetMapping("/")
	public String displayLogin(Model model) { 
		Usuario_v usuario_v = new Usuario_v();
	    model.addAttribute("usuario_v", usuario_v); 
	    return "index"; 
	}
	
	@PostMapping("/login")
	public String iniciarSesion(Usuario_v usuario_v, Model model, HttpServletRequest request) {
		UsuarioDTO usuarioDTO = usuarioService.login(usuario_v.getUser(), usuario_v.getPassword());
		if(usuarioDTO != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", session);
			List<RoleDTO>rolesDTO = usuarioDTO.getRolesDTO();
			if(rolesDTO.contains(new RoleDTO("ADMIN","Insertar, listar, editar y borrar palabras"))){
				return "admin";
			}else {
				return "traductor";
			}
			
		}
		model.addAttribute("mensaje", "Los datos introducidos no son correctos");
		return displayLogin(model);
	}
	
	@GetMapping("/list_usuarios")
	public String getUsuarios(Model model) {
		model.addAttribute("usuarios", usuarioService.getUsuarios());
		
		return "list_usuarios";
	}
	
	@GetMapping("/eliminar_usuario/{user}")
	public String eliminarUsuario(@PathVariable("user") String user, Model model) {
		boolean eliminado = usuarioService.deleteUsuario(user);
		
		if(!eliminado) {
			model.addAttribute("mensaje", "Usuario tiene roles asignados, no se puede eliminar");
		}
		model.addAttribute("usuarios", usuarioService.getUsuarios());

		return "list_usuarios";
	}
	
	@GetMapping("/ver_roles/{user}")
	public String verRoles(@PathVariable("user") String user, Model model) {
		model.addAttribute("roles", usuarioService.getRolesByUser(user));
		model.addAttribute("usuario", user);

		return "list_roles";
	}
	
	@GetMapping("/eliminar_role/{user}/{role}")
	public String eliminarRole(@PathVariable("user") String user, @PathVariable("role") String role, Model model) {
		usuarioService.deleteRole(user, role);
		
		return verRoles(user, model);
	}
	
	@GetMapping("/alta_usuario")
	public String altaUsuario(Model model) {
		model.addAttribute("roles", roleService.getRoles());
		model.addAttribute("usuario_v", new Usuario_v());
		return "alta_usuario";
	}
	
	@PostMapping("/grabar_usuario")
	public String grabarUsuario(Usuario_v usuario_v, Model model, BindingResult result) {
		usuario_v.validate(result);
		if(result.hasErrors()) {
			model.addAttribute("roles", roleService.getRoles());
			return "alta_usuario";
		}
		if(usuarioService.findUsuarioByUser(usuario_v.getUser()) != null) {
			model.addAttribute("roles", roleService.getRoles());
			model.addAttribute("mensaje", "Usuario existente");
			return "alta_usuario";
		}
		UsuarioDTO usuarioDTO = new UsuarioDTO(usuario_v.getUser(), usuario_v.getEmail(), usuario_v.getPassword());
		List<RoleDTO> rolesDTO = new ArrayList<RoleDTO>();
		
		if(!usuario_v.getRol1().isEmpty()) {
			RoleDTO role = new RoleDTO(usuario_v.getRol1());
			rolesDTO.add(role);
		}
		if(!usuario_v.getRol2().isEmpty()) {
			RoleDTO role = new RoleDTO(usuario_v.getRol2());
			rolesDTO.add(role);
		}
		if(!usuario_v.getRol3().isEmpty()) {
			RoleDTO role = new RoleDTO(usuario_v.getRol3());
			rolesDTO.add(role);
		}
		
		usuarioDTO.setRolesDTO(rolesDTO);
		usuarioService.updateUsuarioByUser(usuarioDTO);
		
		return getUsuarios(model);
	}
	
	@GetMapping("/add_roles/{user}")
	public String altaUsuario(@PathVariable("user") String user, Model model) {
		UsuarioDTO usuarioDTO = usuarioService.findUsuarioByUser(user);
		if(usuarioDTO != null) {
			if(usuarioDTO.getRolesDTO() != null) {
				List<RoleDTO> differences = new ArrayList<>(roleService.getRoles());
				differences.removeAll(usuarioDTO.getRolesDTO());
				model.addAttribute("roles", differences);
				
			} else {
				model.addAttribute("roles", roleService.getRoles());
			}
			model.addAttribute("usuario_v", new Usuario_v(user));
			return "add_role";
		} else {
			model.addAttribute("mensaje", "Usuario no existe");
			return getUsuarios(model);
		}
		
	}
	
	@GetMapping("/add_roles/{user}")

	public String altaUsuario(@PathVariable("user") String user, Model model) {

		UsuarioDTO usuarioDTO = usuarioService.findUsuarioByUser(user);

		if(usuarioDTO != null) {

			if(usuarioDTO.getRolesDTO() != null) {

				List<RoleDTO> differences = new ArrayList<>(roleService.getRoles());

				System.out.println(usuarioDTO.getRolesDTO());

				for (RoleDTO roleDTO : usuarioDTO.getRolesDTO()) {

					for (RoleDTO roleDTO2 : differences) {

						if (roleDTO2.getRole().equals(roleDTO.getRole())) {

							differences.remove(roleDTO2);

						break;

						}

					}

				}

				differences.removeAll(usuarioDTO.getRolesDTO());

				model.addAttribute("roles", differences);

				

			} else {

				model.addAttribute("roles", roleService.getRoles());

			}

			model.addAttribute("usuario_v", new Usuario_v(user));

			return "add_role";

		} else {

			model.addAttribute("mensaje", "Usuario no existe");

			return getUsuarios(model);

		}

		

	}
	
	@PostMapping("/add_role_usuario")
	public String addRoleUsuario(Usuario_v usuario_v, Model model) {
		UsuarioDTO usuarioDTO = usuarioService.findUsuarioByUser(usuario_v.getUser());
		if(usuarioDTO == null) {
			model.addAttribute("mensaje", "Usuario inexistente");
		}
		RoleDTO role = new RoleDTO(usuario_v.getRol1());

		usuarioService.addRole(usuarioDTO, role);
				
		return getUsuarios(model);
	}
	
	
}
