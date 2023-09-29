package com.example.traductor.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.traductor.dto.PalabraDTO;
import com.example.traductor.dto.RoleDTO;
import com.example.traductor.dto.UsuarioDTO;
import com.example.traductor.interfaces.IPalabraService;
import com.example.traductor.model.Palabra;
import com.example.traductor.model.Role;
import com.example.traductor.model.Usuario;
import com.example.traductor.repository.PalabraRepository;

@Service
public class PalabraService implements IPalabraService {
	@Autowired
	PalabraRepository palabraRepository;

	@Override
    public String traducir(String palabra, String idiomaDestino) {
        // Buscar la palabra en la base de datos por su nombre original
        Palabra palabraEncontrada = palabraRepository.findByOriginal(palabra);

        if (palabraEncontrada != null) {
            // Devolver la traducción correspondiente según el idioma de destino
            switch (idiomaDestino) {
                case "es":
                    return palabraEncontrada.getTraduccionSP();
                case "en":
                    return palabraEncontrada.getTraduccionIN();
                case "fr":
                    return palabraEncontrada.getTraduccionFR();
                default:
                    return "Idioma de destino no válido";
            }
        } else {
            // Si no se encuentra la palabra, devolver un mensaje de error
            return "Traducción no encontrada para " + palabra;
        }
    }
	
	@Override
	public List<PalabraDTO> listPalabras() {
		List<Palabra> palabras = palabraRepository.findAll();
		
		List<PalabraDTO> palabrasdto = new ArrayList<PalabraDTO>();
		
		for (Palabra palabra : palabras) {
			PalabraDTO palabradto = new PalabraDTO(palabra.getId_Palabra(),palabra.getOriginal(),palabra.getTraduccionSP(),palabra.getTraduccionIN(),palabra.getTraduccionFR());
			palabrasdto.add(palabradto);
		}
		
		return palabrasdto;
	}
	
	@Override
	public PalabraDTO findPalabraById_Palabra(String id_palabra) {
		Palabra palabra = palabraRepository.findById(id_palabra).orElse(null);
		PalabraDTO palabradto = new PalabraDTO(palabra.getId_Palabra(),palabra.getOriginal(),palabra.getTraduccionSP(),palabra.getTraduccionIN(),palabra.getTraduccionFR());;
		return palabradto;
	}

	@Override
	public void updatePalabra(PalabraDTO palabraDTO) {
		Palabra p = new Palabra(palabraDTO.getId_palabra(),palabraDTO.getOriginal(),palabraDTO.getTraduccionSP(),palabraDTO.getTraduccionIN(),palabraDTO.getTraduccionFR());
		palabraRepository.save(p);
	}
	
	@Override
	public void deletePalabra(String id_palabra) {
		palabraRepository.deleteById(id_palabra);
	}
	
}
