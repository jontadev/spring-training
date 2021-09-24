package com.algaworks.algafoodapi.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;

@Service
public class CidadeService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
       Optional<Estado> estado = estadoRepository.findById(estadoId);

        if (estado.isEmpty()) {
            throw new EntidadeNaoEncontradaException(String.format("Nao há cadastro de estado de código %d", estadoId));
        }

        cidade.setEstado(estado.get());

        return cidadeRepository.save(cidade);
    }

    public void remover(Long id) {
        try {
         cidadeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Nao existe cadastro de cidade de código %d" , id));
        }
    }
}
