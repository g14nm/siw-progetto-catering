package it.uniroma3.siw.siwprogettocatering.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Buffet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(unique = true)
	private String nome;
	
	private String descrizione;
	
	@ManyToMany
	private Set<Piatto> piatti;
	
	@ManyToOne
	private Chef chef;
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome.trim();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return this.descrizione.trim();
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Set<Piatto> getPiatti() {
		return this.piatti;
	}

	public void setPiatti(Set<Piatto> piatti) {
		this.piatti = piatti;
	}

	public Chef getChef() {
		return this.chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}
	
	public void addPiatto(Piatto piatto) {
		this.piatti.add(piatto);
	}

	@Override
	public boolean equals(Object o) {
		if(o == null || this.getClass() != o.getClass()) return false;
		Buffet buffet = (Buffet)o;
		return this.nome.equals(buffet.getNome());
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}

}