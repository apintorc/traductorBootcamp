package com.example.traductor.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.misc.Array2DHashSet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.traductor.dto.CategoriaDTO;
import com.example.traductor.dto.PalabraDTO;
import com.example.traductor.dto.RoleDTO;
import com.example.traductor.dto.UsuarioDTO;
import com.example.traductor.interfaces.ICategoriaService;
import com.example.traductor.model.Categoria;
import com.example.traductor.model.Palabra;
import com.example.traductor.model.Role;
import com.example.traductor.model.Usuario;
import com.example.traductor.repository.CategoriaRepository;

@Service
public class CategoriaService implements ICategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
    @Override
    public CategoriaDTO getCategoriaById(int id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + id));
        
        // Mapear la entidad Categoria a CategoriaDTO
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId_categoria(categoria.getId_categoria());
        categoriaDTO.setDescripcion(categoria.getDescripcion());
        
        return categoriaDTO;
    }
    

    @Override
    public List<CategoriaDTO> listCategorias() {
    	List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(this::convertToCategoryToCategoryDTO).collect(Collectors.toList());
    }

	@Override
	public List<PalabraDTO> listPalabrasByCategoria(int id_categoria) {
		Categoria categoria = categoriaRepository.findById(id_categoria).orElse(null);
		List<PalabraDTO> palabrasdto = new ArrayList<PalabraDTO>();
		
		if(categoria != null) {
			for (Palabra palabra : categoria.getPalabras()) {
				PalabraDTO palabradto = new PalabraDTO(palabra.getId_palabra(),palabra.getOriginal(),palabra.getTraduccionSP(),palabra.getTraduccionIN(),palabra.getTraduccionFR(),new CategoriaDTO(palabra.getCategoria().getId_categoria(),palabra.getCategoria().getDescripcion()));
				palabrasdto.add(palabradto);
			}
			
		}
		
		return palabrasdto;
	}
	
	@Override
	public void updateCategoria(CategoriaDTO categoriaDTO) {
		Categoria c = new Categoria(categoriaDTO.getId_categoria(),categoriaDTO.getDescripcion());
		categoriaRepository.save(c);
	}
	
	/**@Override
	public List<CategoriaDTO> getCategorias() {
		List<Categoria> categorias = categoriaRepository.findAll();
		List<CategoriaDTO> categoriasDTO = new ArrayList<CategoriaDTO>();
		for (Categoria categoria : categorias) {
			CategoriaDTO categoriaDTO = new CategoriaDTO(categoria.getId_categoria(), categoria.getDescripcion());
			categoriasDTO.add(categoriaDTO);
		}
		return categoriasDTO;
	}*/

    private CategoriaDTO convertToCategoryToCategoryDTO(Categoria categoria) {
        return modelMapper.map(categoria, CategoriaDTO.class);
    }



}
