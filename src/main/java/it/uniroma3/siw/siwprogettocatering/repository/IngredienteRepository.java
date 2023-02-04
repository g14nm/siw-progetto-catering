package it.uniroma3.siw.siwprogettocatering.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.siwprogettocatering.model.Ingrediente;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long> {

	public boolean existsByNome(String nome);
	
}