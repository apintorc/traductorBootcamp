package com.example.traductor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.traductor.dto.CategoriaDTO;
import com.example.traductor.dto.PalabraDTO;
import com.example.traductor.interfaces.ICategoriaService;
import com.example.traductor.interfaces.IPalabraService;
import com.example.traductor.util.Categoria_v;
import com.example.traductor.util.Palabra_v;
@Controller
public class CategoriaController {
	@Autowired
    ICategoriaService categoriaService;
	
	@GetMapping("/list_categorias")
	public String getCategorias(Model model) {
		model.addAttribute("categorias", categoriaService.listCategorias());
		
		return "list_categorias";
	}
	
	@GetMapping("/ver_traducciones/{id_categoria}/{descripcion}")
	public String getCategorias(@PathVariable("id_categoria") int id_categoria, @PathVariable("descripcion") String descripcion, Model model) {
		model.addAttribute("palabras", categoriaService.listPalabrasByCategoria(id_categoria));
		model.addAttribute("descripcion", descripcion);

		return "list_palabras";
	}
	
	@GetMapping("/alta_categoria")
	public String altaCategoria(Model model) {
		model.addAttribute("categoria_v", new Categoria_v());
		return "alta_categoria";
	}
	
	@PostMapping("/grabar_categoria")
	public String grabarCategoria(Categoria_v categoria_v, Model model) {
		CategoriaDTO categoriaDTO = new CategoriaDTO(categoria_v.getId_categoria(), categoria_v.getDescripcion());
		categoriaService.updateCategoria(categoriaDTO);
		
		return getCategorias(model);
	}
}
