package it.uniroma3.siw.siwprogettocatering.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.siwprogettocatering.model.Buffet;

public interface BuffetRepository extends CrudRepository<Buffet, Long> {

	public boolean existsByNome(String nome);
	
}