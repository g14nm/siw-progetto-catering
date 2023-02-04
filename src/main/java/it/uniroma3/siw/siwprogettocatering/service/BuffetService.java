package it.uniroma3.siw.siwprogettocatering.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.siwprogettocatering.model.Buffet;
import it.uniroma3.siw.siwprogettocatering.repository.BuffetRepository;

@Service
public class BuffetService {

	@Autowired
	private BuffetRepository buffetRepository;
	
	public Buffet save(Buffet buffet) {
		return this.buffetRepository.save(buffet);
	}
	
	public List<Buffet> findAll() {
		return (List<Buffet>)this.buffetRepository.findAll();
	}
	
	public Buffet findById(Long id) {
		return this.buffetRepository.findById(id).get();
	}
	
	public void deleteById(Long id) {
		this.buffetRepository.deleteById(id);
	}
	
}