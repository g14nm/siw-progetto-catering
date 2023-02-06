package it.uniroma3.siw.siwprogettocatering.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Chef {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String cognome;
	
	@NotBlank
	private String nazionalita;
	
	@OneToMany(mappedBy = "chef")
	private Set<Buffet> buffets;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome.trim();
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome.trim();
	}

	public String getNazionalita() {
		return this.nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita.trim();
	}

	public Set<Buffet> getBuffets() {
		return this.buffets;
	}

	public void setBuffets(Set<Buffet> buffets) {
		this.buffets = buffets;
	}
	
	public void addBuffet(Buffet buffet) {
		this.buffets.add(buffet);
	}
	
	public void removeBuffet(Buffet buffet) {
		this.buffets.remove(buffet);
	}
	
	public void removeFromBuffets() {
		for(Buffet buffet : this.buffets)
			buffet.removeChef();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null || this.getClass() != o.getClass()) return false;
		Chef chef = (Chef)o;
		return this.nome.equals(chef.getNome()) && this.cognome.equals(chef.getCognome());
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode() + this.cognome.hashCode();
	}
	
}