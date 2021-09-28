package com.algaworks.algafoodapi.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.algaworks.algafoodapi.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {

    List<Cozinha> findCozinhasByNomeContaining(String nome);

    Optional<Cozinha> findByNome(String nome);

    boolean existsByNome(String nome);

}