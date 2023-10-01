package com.example.traductor.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.traductor.dto.CategoriaDTO;
import com.example.traductor.dto.PalabraDTO;
import com.example.traductor.interfaces.ICategoriaService;
import com.example.traductor.model.Categoria;
import com.example.traductor.model.Palabra;
import com.example.traductor.repository.CategoriaRepository;

@Service
public class CategoriaService implements ICategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Override
	public List<CategoriaDTO> listCategorias() {
		List<Categoria> categorias = categoriaRepository.findAll();
		
		List<CategoriaDTO> categoriasdto = new ArrayList<CategoriaDTO>();
		
		for (Categoria categoria : categorias) {
			CategoriaDTO categoriadto = new CategoriaDTO(categoria.getId_categoria(), categoria.getDescripcion());
			categoriasdto.add(categoriadto);
		}
		
		return categoriasdto;
	}

	@Override
	public List<PalabraDTO> listPalabrasByCategoria(int id_categoria) {
		Categoria categoria = categoriaRepository.findById(id_categoria).orElse(null);
		List<PalabraDTO> palabrasdto = new ArrayList<PalabraDTO>();
		
		if(categoria != null) {
			for (Palabra palabra : categoria.getPalabras()) {
				PalabraDTO palabradto = new PalabraDTO(palabra.getId_palabra(),palabra.getOriginal(),palabra.getTraduccionSP(),palabra.getTraduccionIN(),palabra.getTraduccionFR(),new CategoriaDTO(palabra.getCategoria().getId_categoria(), palabra.getCategoria().getDescripcion()));
				palabrasdto.add(palabradto);
			}
		}
		
		return palabrasdto;
	}

}
