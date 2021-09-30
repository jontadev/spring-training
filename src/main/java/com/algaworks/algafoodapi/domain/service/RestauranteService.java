package com.algaworks.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private RestauranteRepository restauranteRepository;
    
    public List<Restaurante> listar() {
    	return restauranteRepository.findAll();
    }
    
    public Restaurante buscar(Long id) {
    	return restauranteRepository.findById(id)
    			.orElseThrow(() -> new EntidadeNaoEncontradaException(
    					String.format("Nao existe um cadastro de restaurante com o código %d", id)));
    }
    
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaService.buscar(cozinhaId);

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }
}
