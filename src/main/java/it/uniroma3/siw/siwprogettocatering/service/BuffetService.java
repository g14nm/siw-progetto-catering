package it.uniroma3.siw.siwprogettocatering.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.siwprogettocatering.model.Buffet;
import it.uniroma3.siw.siwprogettocatering.model.Piatto;
import it.uniroma3.siw.siwprogettocatering.repository.BuffetRepository;

@Service
public class BuffetService {

	@Autowired
	private BuffetRepository buffetRepository;
	
	@Transactional
	public Buffet save(Buffet buffet) {
		buffet.addToChef();
		for(Piatto piatto: buffet.getPiatti()) 
			piatto.addBuffet(buffet);
		return this.buffetRepository.save(buffet);
	}
	
	public List<Buffet> findAll() {
		return (List<Buffet>)this.buffetRepository.findAll();
	}
	
	public Buffet findById(Long id) {
		return this.buffetRepository.findById(id).get();
	}
	
	public boolean existsByNome(String nome) {
		return this.buffetRepository.existsByNome(nome);
	}
	
	@Transactional
	public void deleteById(Long id) {
		Buffet buffet = this.findById(id);
		buffet.removeFromChef();
		buffet.removeFromPiatti();
		this.buffetRepository.deleteById(id);
	}
	
}