package com.example.traductor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.traductor.interfaces.IPalabraService;

@Controller
public class PalabraController {
	@Autowired
    IPalabraService palabraService;
	
	@GetMapping("/traductor")
	public String traductorView(Model model) {
		return "traductor";
	}
	

    @PostMapping("/traducir")
    public String traducirPalabra(
    		@RequestParam("original") String original,
    		@RequestParam("idiomaDestino") String idiomaDestino,
            Model model
    ) {
        String traduccion = palabraService.traducir(original, idiomaDestino);
        model.addAttribute("traduccion", traduccion);
        return "traductor"; // Esta es la vista que muestra el resultado de la traducción
    }
}
