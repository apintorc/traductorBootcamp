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
	private String rol1;
	private String rol2;
	
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
		
		
		return true;
	}

	
}
