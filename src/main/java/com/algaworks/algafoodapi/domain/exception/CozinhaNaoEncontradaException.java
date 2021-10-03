package com.algaworks.algafoodapi.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CozinhaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CozinhaNaoEncontradaException(Long id) {
		this(String.format("Nao existe um cadastro de cozinha com código %d", id));
	}

}
