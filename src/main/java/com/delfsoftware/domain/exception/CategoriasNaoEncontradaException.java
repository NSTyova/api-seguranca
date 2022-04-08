package com.delfsoftware.domain.exception;

public class CategoriasNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CategoriasNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CategoriasNaoEncontradaException(Long cozinhaId) {
		this(String.format("Não existe um cadastro de categorias com código %d", cozinhaId));
	}
}
