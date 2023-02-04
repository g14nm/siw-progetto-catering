package it.uniroma3.siw.siwprogettocatering.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Piatto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(unique = true)
	private String nome;
	
	@NotBlank
	private String descrizione;
	
	@ManyToMany
	private Set<Ingrediente> ingredienti;

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

	public Set<Ingrediente> getIngredienti() {
		return this.ingredienti;
	}

	public void setIngredienti(Set<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}
	
	public void addIngrediente(Ingrediente ingrediente) {
		this.ingredienti.add(ingrediente);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null || this.getClass() != o.getClass()) return false;
		Piatto piatto = (Piatto)o;
		return this.nome.equals(piatto.getNome());
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}
	
}