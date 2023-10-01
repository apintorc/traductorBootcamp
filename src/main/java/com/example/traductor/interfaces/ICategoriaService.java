package com.example.traductor.interfaces;

import java.util.List;

import com.example.traductor.dto.CategoriaDTO;
import com.example.traductor.dto.PalabraDTO;

public interface ICategoriaService {
	public List<CategoriaDTO> listCategorias();
	public List<PalabraDTO> listPalabrasByCategoria(int id_categoria);
	public void updateCategoria(CategoriaDTO categoriaDTO);
	public CategoriaDTO getCategoriaById(int id);
}
