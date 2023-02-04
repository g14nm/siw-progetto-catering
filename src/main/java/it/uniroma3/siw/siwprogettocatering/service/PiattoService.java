package it.uniroma3.siw.siwprogettocatering.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.siwprogettocatering.model.Piatto;
import it.uniroma3.siw.siwprogettocatering.repository.PiattoRepository;

@Service
public class PiattoService {

	@Autowired
	private PiattoRepository piattoRepository;
	
	public Piatto save(Piatto piatto) {
		return this.piattoRepository.save(piatto);
	}
	
	public List<Piatto> findAll() {
		return (List<Piatto>)this.piattoRepository.findAll();
	}
	
	public Piatto findById(Long id) {
		return this.piattoRepository.findById(id).get();
	}
	
	public void deleteById(Long id) {
		this.piattoRepository.deleteById(id);
	}
	
}