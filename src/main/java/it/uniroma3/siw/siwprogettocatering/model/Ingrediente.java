package it.uniroma3.siw.siwprogettocatering.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Ingrediente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String origine;
	
	private String descrizione;
	
	@ManyToMany
	private Set<Piatto> piatti;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.trim();
	}

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine.trim();
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione.trim();
	}
	
	public Set<Piatto> getPiatti() {
		return this.piatti;
	}
	
	public void setPiatti(Set<Piatto> piatti) {
		this.piatti = piatti;
	}
	
	public void addPiatto(Piatto piatto) {
		this.piatti.add(piatto);
	}
	
	public void removePiatto(Piatto piatto) {
		this.piatti.remove(piatto);
	}
	
	public void removeFromPiatti() {
		for(Piatto piatto : this.piatti)
			piatto.removeIngrediente(this);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null || this.getClass() != o.getClass()) return false;
		Ingrediente ingrediente = (Ingrediente)o;
		return this.nome.equals(ingrediente.getNome()) && this.origine.equals(ingrediente.getOrigine());
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode() + this.origine.hashCode();
	}
	
}