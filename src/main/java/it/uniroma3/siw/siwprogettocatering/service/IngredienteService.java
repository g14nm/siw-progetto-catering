package it.uniroma3.siw.siwprogettocatering.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	public boolean existsByNomeAndOrigine(String nome, String origine) {
		return this.ingredienteRepository.existsByNomeAndOrigine(nome, origine);
	}
	
	@Transactional
	public void deleteById(Long id) {
		Ingrediente ingrediente = this.findById(id);
		ingrediente.removeFromPiatti();
		this.ingredienteRepository.deleteById(id);
	}
	
}