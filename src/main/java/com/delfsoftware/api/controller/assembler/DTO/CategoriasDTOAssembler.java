package com.delfsoftware.api.controller.assembler.DTO;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.delfsoftware.domain.modelo.Categorias;
import com.delfsoftware.domain.modelo.DTO.CategoriasDTO;

@Component
public class CategoriasDTOAssembler {

	@Autowired
    private ModelMapper modelMapper;
    
    public CategoriasDTO toModel(Categorias formaPagamento) {
        return modelMapper.map(formaPagamento, CategoriasDTO.class);
    }
    
    public List<CategoriasDTO> toCollectionModel(List<Categorias> formasPagamentos) {
        return formasPagamentos.stream()
                .map(formaPagamento -> toModel(formaPagamento))
                .collect(Collectors.toList());
    }
}
