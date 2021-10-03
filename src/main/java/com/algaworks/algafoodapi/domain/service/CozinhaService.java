package com.algaworks.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;

@Service
public class CozinhaService {

	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d nao pode ser removida, pois está em uso";

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Cozinha buscar(Long id) {
		return this.cozinhaRepository.findById(id).orElseThrow(() -> new CozinhaNaoEncontradaException(id));
	}

	public Cozinha salvar(Cozinha cozinha) {
		return this.cozinhaRepository.save(cozinha);
	}

	public void remover(Long id) {
		try {
			cozinhaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new CozinhaNaoEncontradaException(id);
		} catch (DataIntegrityViolationException ex) {
			throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO, id));
		}

	}
}
