package com.algaworks.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;

@Service
public class CidadeService {

	private static final String MSG_CIDADE_NAO_EXISTENTE = "Nao existe cadastro de cidade de código %d";
    private static final String MSG_CIDADE_EM_USO = "Cidade de código %d nao pode ser removida, pois está em uso";

	@Autowired
    private EstadoService estadoService;

    @Autowired
    private CidadeRepository cidadeRepository;
    
    public List<Cidade> listar() {
    	return cidadeRepository.findAll();
    }
    
    public Cidade buscar(Long id) {
    	return cidadeRepository.findById(id).orElseThrow(() -> 
    		new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_EXISTENTE , id)));
    }
    
    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoService.buscar(estadoId);

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    public void remover(Long id) {
        try {
         cidadeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_EXISTENTE , id));
        } catch (DataIntegrityViolationException e) {
        	throw new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_USO, id));
        }
    }
}
