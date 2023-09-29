package com.example.traductor.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.traductor.interfaces.IPalabraService;
import com.example.traductor.model.Palabra;
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
}
