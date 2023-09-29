package com.example.traductor.util;

import java.util.regex.Pattern;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario_v {

	private String user;
	private String password;
	private String email;
	private String rol1;
	private String rol2;
	private String rol3;
	
	public Usuario_v(String user) {
		super();
		this.user = user;
	}
	
	public boolean validate(Errors errors) {
		
		if(!StringUtils.hasText(user) ) {
			errors.rejectValue("user", "badformat", "rellene el user");
		}
		if(!StringUtils.hasText(password) ) {
			errors.rejectValue("password", "badformat", "rellene el password");
		} else if(password.length() < 9) {
			errors.rejectValue("password", "badformat", "La contraseña debe tener más de 8 caracteres");
			
		}
		if(!StringUtils.hasText(email) ) {
			errors.rejectValue("email", "badformat", "rellene la direccion de email");
		} else if(!Pattern.compile("^(.+)@(\\S+)$").matcher(email).matches()){
			errors.rejectValue("email", "badformat", "Introduzca un email válido");
		}
		
		
		return true;
	}

	
}
