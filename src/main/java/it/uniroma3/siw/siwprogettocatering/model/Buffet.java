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
	
	@ManyToMany(mappedBy = "buffets")
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
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome.trim();
	}

	public String getDescrizione() {
		return this.descrizione;
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

	public Chef getChef() {
		return this.chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}
	
	public void removePiatto(Piatto piatto) {
		this.piatti.remove(piatto);
	}
	
	public void removeFromPiatti() {
		for(Piatto piatto : this.piatti)
			piatto.removeBuffet(this);
	}
	
	public void removeChef() {
		this.chef = null;
	}
	
	public void addToChef() {
		this.chef.addBuffet(this);
	}
	
	public void removeFromChef() {
		this.chef.removeBuffet(this);
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