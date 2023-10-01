package com.example.traductor.interfaces;

import java.util.List;

import com.example.traductor.dto.PalabraDTO;
import com.example.traductor.dto.UsuarioDTO;
import com.example.traductor.model.Palabra;

public interface IPalabraService {
	public String traducir(String palabra, String idiomaDestino);
	public void updatePalabra(PalabraDTO palabra);
	public List<PalabraDTO> listPalabras();
	public void deletePalabra(String id_palabra);
	public PalabraDTO findPalabraById_Palabra(String id_palabra);
}
