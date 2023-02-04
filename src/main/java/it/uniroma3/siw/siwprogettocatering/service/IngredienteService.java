package it.uniroma3.siw.siwprogettocatering.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.siwprogettocatering.model.Ingrediente;
import it.uniroma3.siw.siwprogettocatering.repository.IngredienteRepository;

@Service
public class IngredienteService {

	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	public Ingrediente save(Ingrediente ingrediente) {
		return this.ingredienteRepository.save(ingrediente);
	}
	
	public List<Ingrediente> findAll() {
		return (List<Ingrediente>)this.ingredienteRepository.findAll();
	}
	
	public Ingrediente findById(Long id) {
		return this.ingredienteRepository.findById(id).get();
	}
	
	public void deleteById(Long id) {
		this.ingredienteRepository.deleteById(id);
	}
	
}