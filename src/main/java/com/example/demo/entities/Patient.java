package com.example.demo.entities;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends Personne {
    @Basic
    @Column(name = "maladie", nullable = true, length = 255)
	private String maladie ;
    @Basic
    @Column(name = "etat", nullable = true, length = 20)
	private String etat = "en attente" ;
    
    
    @OneToMany(mappedBy = "patient")
	 private Collection<Visite> visites ;

    
    @OneToOne(mappedBy = "patient")
	 private Lit lit ;


	public String getMaladie() {
		return maladie;
	}


	public void setMaladie(String maladie) {
		this.maladie = maladie;
	}


	public String getEtat() {
		return etat;
	}


	public void setEtat(String etat) {
		this.etat = etat;
	}


	public Collection<Visite> getVisites() {
		return visites;
	}


	public void setVisites(Collection<Visite> visites) {
		this.visites = visites;
	}


	public Lit getLit() {
		return lit;
	}


	public void setLit(Lit lit) {
		this.lit = lit;
	}
	
    }
