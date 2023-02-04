package it.uniroma3.siw.siwprogettocatering.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.siwprogettocatering.model.Chef;
import it.uniroma3.siw.siwprogettocatering.repository.ChefRepository;

@Service
public class ChefService {

	@Autowired
	private ChefRepository chefRepository;
	
	public Chef save(Chef chef) {
		return this.chefRepository.save(chef);
	}
	
	public List<Chef> findAll() {
		return (List<Chef>)this.chefRepository.findAll();
	}
	
	public Chef findById(Long id) {
		return this.chefRepository.findById(id).get();
	}
	
	public void deleteById(Long id) {
		this.chefRepository.deleteById(id);
	}	
	
}