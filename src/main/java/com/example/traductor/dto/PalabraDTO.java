package com.example.traductor.dto;

import com.example.traductor.dto.CategoriaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PalabraDTO {
	private String id_palabra;
	private String original;
	private String traduccionSP;
	private String traduccionIN;
	private String traduccionFR;
	private CategoriaDTO categoriadto;
	public PalabraDTO(String id_palabra, String original, String traduccionSP, String traduccionIN, String traduccionFR,
			CategoriaDTO categoriadto) {
		super();
		this.id_palabra = id_palabra;
		this.original = original;
		this.traduccionSP = traduccionSP;
		this.traduccionIN = traduccionIN;
		this.traduccionFR = traduccionFR;
		this.categoriadto = categoriadto;
	}
	
	
}
