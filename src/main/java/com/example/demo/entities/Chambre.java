package com.example.demo.entities;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chambre {
	 @Id
	 @Column(name = "chambre_id", nullable = false)
	 @GeneratedValue(strategy = GenerationType.AUTO)   
	 private long id;
	 @Basic
	 @Column(name = "numero", nullable = false)
	 private int numero ;
	 @Basic
	 @Column(name = "capacite", nullable = false)
	 private int capacite ;
	 @Basic
	 @Column(name = "litnoncharge", nullable = false)
	 private int litnoncharge ;
	 @OneToMany(cascade = CascadeType.ALL ,mappedBy = "chambre" )
	 private Collection<Lit> lits ;
	public int getNumero() {
		// TODO Auto-generated method stub
		return this.numero;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getCapacite() {
		return capacite;
	}
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	public Collection<Lit> getLits() {
		return lits;
	}
	public void setLits(Collection<Lit> lits) {
		this.lits = lits;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getLitnoncharge() {
		return litnoncharge;
	}
	public void setLitnoncharge(int litnoncharge) {
		this.litnoncharge = litnoncharge;
	} 
	
	 
	 
}
