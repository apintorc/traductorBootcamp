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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.traductor.dto.CategoriaDTO;
import com.example.traductor.dto.PalabraDTO;
import com.example.traductor.dto.RoleDTO;
import com.example.traductor.dto.UsuarioDTO;
import com.example.traductor.interfaces.ICategoriaService;
import com.example.traductor.interfaces.IPalabraService;
import com.example.traductor.model.Categoria;
import com.example.traductor.model.Palabra;
import com.example.traductor.util.Palabra_v;
import com.example.traductor.util.Usuario_v;

@Controller
public class PalabraController {
	@Autowired
    IPalabraService palabraService;
	
	@Autowired
    ICategoriaService categoriaService;
	
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
    
	@GetMapping("/list_palabras")
	public String getPalabras(Model model) {
		model.addAttribute("palabras", palabraService.listPalabras());
		
		return "list_palabras";
	}
	
	@GetMapping("/alta_palabra")
	public String altaPalabra(Model model) {
		model.addAttribute("palabraDTO", new PalabraDTO());
		model.addAttribute("categorias", categoriaService.listCategorias());
		
		return "alta_palabra";
	}
	
	@PostMapping("/grabar_palabra")
	public String grabarPalabra(PalabraDTO palabraDTO, Model model) {
	    palabraService.updatePalabra(palabraDTO);
	    return getPalabras(model); 
	}




	@GetMapping("/eliminar_palabra/{id_palabra}")
	public String eliminarPalabra(@PathVariable("id_palabra") String id_palabra, Model model) {
		palabraService.deletePalabra(id_palabra);
		return getPalabras(model);
	}
	
	@GetMapping("/editar_palabra/{id_palabra}")
	public String editar_palabra(@PathVariable("id_palabra") String id_palabra, Model model) {
		PalabraDTO palabradto=palabraService.findPalabraById_Palabra(id_palabra);
		//Palabra_v palabra_v=new Palabra_v(palabradto.getId_palabra(),palabradto.getOriginal(),palabradto.getTraduccionSP(),palabradto.getTraduccionIN(),palabradto.getTraduccionFR(),1);
		model.addAttribute("categorias", categoriaService.listCategorias());
		model.addAttribute("palabra_v", palabradto);
		return "editar_palabra";
	}
	
	@PostMapping("/update_palabra")
	public String update_palabra(PalabraDTO palabraDTO, Model model) {
		//PalabraDTO palabradto=new PalabraDTO(palabra_v.getId_palabra(),palabra_v.getOriginal(),palabra_v.getTraduccionSP(),palabra_v.getTraduccionIN(),palabra_v.getTraduccionFR(),new CategoriaDTO());
		palabraService.updatePalabra(palabraDTO);
		return getPalabras(model);
	}
	

}
