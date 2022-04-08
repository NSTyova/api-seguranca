package com.delfsoftware.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.delfsoftware.api.controller.assembler.DTO.CategoriasDTOAssembler;
import com.delfsoftware.api.controller.assembler.form.CategoriaFormAssembler;
import com.delfsoftware.domain.modelo.Categorias;
import com.delfsoftware.domain.modelo.DTO.CategoriasDTO;
import com.delfsoftware.domain.modelo.form.CategoriaForm;
import com.delfsoftware.domain.repository.CategoriasRepository;
import com.delfsoftware.domain.service.CategoriasService;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

	@Autowired
    private CategoriasRepository categoriasRepository;
    
    @Autowired
    private CategoriasService service;
    
    @Autowired
    private CategoriasDTOAssembler categoriasModelAssembler;
    
    @Autowired
    private CategoriaFormAssembler categoriasInputDisassembler;
    
    @GetMapping
    public List<CategoriasDTO> listar() {
        List<Categorias> todasFormasPagamentos = categoriasRepository.findAll();
        
        return categoriasModelAssembler.toCollectionModel(todasFormasPagamentos);
    }
    
    @GetMapping("/{id}")
    public CategoriasDTO buscar(@PathVariable Long id) {
        Categorias categorias = service.buscarOuFalhar(id);
        
        return categoriasModelAssembler.toModel(categorias);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriasDTO adicionar(@RequestBody @Valid CategoriaForm categoriasInput) {
        Categorias categorias = categoriasInputDisassembler.toDomainObject(categoriasInput);
        
        categorias = service.salvar(categorias);
        
        return categoriasModelAssembler.toModel(categorias);
    }
    
    @PutMapping("/{id}")
    public CategoriasDTO atualizar(@PathVariable Long id,
            @RequestBody @Valid CategoriaForm categoriasInput) {
        Categorias categoriasAtual = service.buscarOuFalhar(id);
        
        categoriasInputDisassembler.copyToDomainObject(categoriasInput, categoriasAtual);
        
        categoriasAtual = service.salvar(categoriasAtual);
        
        return categoriasModelAssembler.toModel(categoriasAtual);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        service.excluir(id);	
    }   
	
}
