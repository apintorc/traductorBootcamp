package com.example.traductor.services;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.traductor.dto.PalabraDTO;
import com.example.traductor.interfaces.IPalabraService;
import com.example.traductor.model.Categoria;
import com.example.traductor.model.Palabra;
import com.example.traductor.repository.CategoriaRepository;
import com.example.traductor.repository.PalabraRepository;

@Service
public class PalabraService implements IPalabraService {
	@Autowired
	PalabraRepository palabraRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	@Override
    public String traducir(String palabra, String idiomaDestino) {
        Palabra palabraEncontrada = palabraRepository.findByOriginal(palabra);

        if (palabraEncontrada != null) {
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
            return "Traducción no encontrada para " + palabra;
        }
    }
	
	@Override
	public List<PalabraDTO> listPalabras() {
	    List<Palabra> palabras = palabraRepository.findAll();
	    List<PalabraDTO> palabraDTOs = new ArrayList<>();

	    for (Palabra palabra : palabras) {
	        PalabraDTO palabraDTO = modelMapper.map(palabra, PalabraDTO.class);
	        palabraDTOs.add(palabraDTO);
	    }

	    return palabraDTOs;
	}


	
	@Override
    public PalabraDTO findPalabraById_Palabra(String id_palabra) {
        Palabra palabra = palabraRepository.findById(id_palabra).orElse(null);
        return modelMapper.map(palabra, PalabraDTO.class);
    }

	@Override
	public void updatePalabra(PalabraDTO palabraDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Palabra palabra = modelMapper.map(palabraDTO, Palabra.class);
        Categoria categoria = categoriaRepository.findByDescripcion(palabraDTO.getCategoria().getDescripcion());
        palabra.setCategoria(categoria);
        palabraRepository.save(palabra);
	}
	
	@Override
	public void deletePalabra(String id_palabra) {
		palabraRepository.deleteById(id_palabra);
	}



	
}
