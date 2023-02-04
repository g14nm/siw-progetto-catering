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
	private String nazionalità;
	
	@OneToMany(mappedBy = "chef")
	private Set<Buffet> buffet;

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
		this.nome = nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNazionalità() {
		return this.nazionalità;
	}

	public void setNazionalità(String nazionalità) {
		this.nazionalità = nazionalità;
	}

	public Set<Buffet> getBuffet() {
		return this.buffet;
	}

	public void setBuffet(Set<Buffet> buffet) {
		this.buffet = buffet;
	}
	
	public void addBuffet(Buffet buffet) {
		this.buffet.add(buffet);
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