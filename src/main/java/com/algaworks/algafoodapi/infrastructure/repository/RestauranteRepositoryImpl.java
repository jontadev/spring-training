package com.algaworks.algafoodapi.infrastructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepositoryQueries;
import com.algaworks.algafoodapi.infrastructure.repository.spec.RestauranteSpecs;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Lazy
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
    	
    	List<Predicate> predicates = new ArrayList<Predicate>();
    	
    	// É como uma fábrica para construir elementos para consultas e também a CriteriaBuilder;
    	CriteriaBuilder builder = entityManager.getCriteriaBuilder();    	
    	
    	// Resposável por compor as clausulas
    	CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
    	Root<Restaurante> root = criteria.from(Restaurante.class);
    	// from Restaurante
    	
    	if (StringUtils.hasLength(nome)) {
    		predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
    	}
    	
    	if (!Objects.isNull(taxaFreteInicial)) {
    		predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), (BigDecimal) taxaFreteInicial));
    	}
    	
    	if (!Objects.isNull(taxaFreteFinal)) {
    		predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), (BigDecimal) taxaFreteFinal));
    	}
    	
    	criteria.where(predicates.toArray(new Predicate[0]));
    	
    	TypedQuery<Restaurante> query = entityManager.createQuery(criteria);
    	return query.getResultList();

    	
    	
    	//    	StringBuilder jpql = new StringBuilder();
//    	jpql.append("from Restaurante where 0 = 0 ");
//    	
//    	Map<String, Object> parametros = new HashMap<String, Object>();
//    	
//    	if (StringUtils.hasLength(nome)) {
//    		jpql.append("and nome like :nome ");
//    		parametros.put("nome", "%" + nome + "%");
//    	}
//    	
//    	if (taxaFreteInicial != null) {
//    		jpql.append("and taxaFrete >= :taxaInicial ");
//    		parametros.put("taxaInicial", taxaFreteInicial);
//    	}
//    	
//    	if (taxaFreteFinal != null) {
//    		jpql.append("and taxaFrete <= :taxaFinal");
//    		parametros.put("taxaFinal", taxaFreteFinal);
//    	}
//    	
//        TypedQuery<Restaurante> query = entityManager.createQuery(jpql.toString(), Restaurante.class);
//        parametros.forEach((key, value) -> query.setParameter(key, value));
//        
//        return query.getResultList();
    	
    }
    
	@Override
	public List<Restaurante> findComFreteGratis(String nome) {
		return restauranteRepository.findAll(RestauranteSpecs.comFreteGratis().and(RestauranteSpecs.comNomeSemelhante(nome)));
	}
}