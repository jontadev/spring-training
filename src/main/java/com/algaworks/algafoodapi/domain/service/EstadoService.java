package com.algaworks.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;

@Service
public class EstadoService {

    private static final String MSG_ESTADO_EM_USO = "Estado de código %d nao pode ser removida, pois está em uso";
	
	@Autowired
    private EstadoRepository estadoRepository;

	public List<Estado> listar() {
		return estadoRepository.findAll();
	}
	
    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }
    
    public Estado buscar(Long id) {
    	return estadoRepository.findById(id).orElseThrow(() -> new EstadoNaoEncontradoException(id));
    }

    public void remover (Long id) {
        try {
            estadoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EstadoNaoEncontradoException(id);
        } catch (DataIntegrityViolationException ex) {
            throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, id));
        }

    }
}
