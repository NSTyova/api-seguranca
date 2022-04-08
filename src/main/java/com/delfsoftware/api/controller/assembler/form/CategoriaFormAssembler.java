package com.delfsoftware.api.controller.assembler.form;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.delfsoftware.domain.modelo.Categorias;
import com.delfsoftware.domain.modelo.form.CategoriaForm;


@Component
public class CategoriaFormAssembler {
	@Autowired
    private ModelMapper modelMapper;
    
    public Categorias toDomainObject(CategoriaForm categoriaForm) {
        return modelMapper.map(categoriaForm, Categorias.class);
    }
    
    public void copyToDomainObject(CategoriaForm formaPagamentoInput, Categorias formaPagamento) {
        modelMapper.map(formaPagamentoInput, formaPagamento);
    }   
}
