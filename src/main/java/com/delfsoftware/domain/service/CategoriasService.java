package com.delfsoftware.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delfsoftware.domain.exception.CategoriasNaoEncontradaException;
import com.delfsoftware.domain.exception.EntidadeEmUsoException;
import com.delfsoftware.domain.modelo.Categorias;
import com.delfsoftware.domain.repository.CategoriasRepository;

@Service
public class CategoriasService {

	private static final String MSG_Categorias_EM_USO = "Categorias de código %d não pode ser removida, pois está em uso";

	@Autowired
	private CategoriasRepository repository;

	@Transactional
	public Categorias salvar(Categorias categorias) {
		return repository.save(categorias);
	}

	@Transactional
	public void excluir(Long categoriasId) {
		try {
			repository.deleteById(categoriasId);
			repository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new CategoriasNaoEncontradaException(categoriasId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_Categorias_EM_USO, categoriasId));
		}
	}

	public Categorias buscarOuFalhar(Long categoriasId) {
		return repository.findById(categoriasId).orElseThrow(() -> new CategoriasNaoEncontradaException(categoriasId));
	}
}
