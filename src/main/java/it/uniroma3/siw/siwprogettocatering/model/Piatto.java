package it.uniroma3.siw.siwprogettocatering.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

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
	
	@NotEmpty
	@ManyToMany(mappedBy = "piatti")
	private Set<Ingrediente> ingredienti;
	
	@ManyToMany
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
	
	public Set<Buffet> getBuffets() {
		return this.buffets;
	}
	
	public void setBuffets(Set<Buffet> buffets) {
		this.buffets = buffets;
	}
	
	public void removeIngrediente(Ingrediente ingrediente) {
		this.ingredienti.remove(ingrediente);
	}
	
	public void addBuffet(Buffet buffet) {
		this.buffets.add(buffet);
	}
	
	public void removeBuffet(Buffet buffet) {
		this.buffets.remove(buffet);
	}
	
	public void removeFromIngredienti() {
		for(Ingrediente ingrediente : this.ingredienti)
			ingrediente.removePiatto(this);
	}
	
	public void removeFromBuffets() {
		for(Buffet buffet : this.buffets)
			buffet.removePiatto(this);
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