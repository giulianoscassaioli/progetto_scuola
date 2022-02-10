package model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="studente")
public class Studente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int matricola;
	private String nome;
	private String cognome;
	
	private List<Corso> corsi;
	
	
	@Id
	@Column(name="matricola")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getMatricola() {
		return matricola;
	}
	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}
	
	@Column(name="nome")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Column(name="cognome")
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="frequenta",
	joinColumns = {@JoinColumn(name="matricola_studente",referencedColumnName = "matricola")},
	inverseJoinColumns = {@JoinColumn(name="id_corso", referencedColumnName = "id")}
	)
	public List<Corso> getCorsi() {
		return corsi;
	}
	
	public void setCorsi(List<Corso> corsi) {
		this.corsi = corsi;
	}

}
