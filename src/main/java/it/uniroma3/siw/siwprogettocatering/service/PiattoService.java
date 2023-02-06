package it.uniroma3.siw.siwprogettocatering.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.siwprogettocatering.model.Ingrediente;
import it.uniroma3.siw.siwprogettocatering.model.Piatto;
import it.uniroma3.siw.siwprogettocatering.repository.PiattoRepository;

@Service
public class PiattoService {

	@Autowired
	private PiattoRepository piattoRepository;
	
	@Transactional
	public Piatto save(Piatto piatto) {
		for(Ingrediente ingrediente : piatto.getIngredienti())
			ingrediente.addPiatto(piatto);
		return this.piattoRepository.save(piatto);
	}
	
	public List<Piatto> findAll() {
		return (List<Piatto>)this.piattoRepository.findAll();
	}
	
	public Piatto findById(Long id) {
		return this.piattoRepository.findById(id).get();
	}
	
	public boolean existsByNome(String nome) {
		return this.piattoRepository.existsByNome(nome);
	}
	
	@Transactional
	public void deleteById(Long id) {
		Piatto piatto = this.findById(id);
		piatto.removeFromBuffets();
		piatto.removeFromIngredienti();
		this.piattoRepository.deleteById(id);
	}
	
}