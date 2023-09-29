package com.example.traductor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PalabraDTO {
	private String id_palabra;
	private String original;
	private String traduccionSP;
	private String traduccionIN;
	private String traduccionFR;
}
