package com.algaworks.algafoodapi.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public EstadoNaoEncontradoException(Long id ) {
		this(String.format("Nao existe um cadastro de estado com código %d", id));
	}
}
