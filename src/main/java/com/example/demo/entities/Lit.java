package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Null;

import org.hibernate.annotations.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Lit {
@Id
@GeneratedValue()
@Column(name = "lit_id", nullable = false)
 private long id;
@Column(columnDefinition = "boolean default false")
private Boolean chargé = false;
@ManyToOne(cascade = javax.persistence.CascadeType.MERGE )
@JoinColumn(name = "chambreId")
private Chambre chambre;
@OneToOne(cascade = javax.persistence.CascadeType.ALL )
@JoinColumn(name = "patientId")
private Patient patient;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public Boolean getChargé() {
	return chargé;
}
public void setChargé(Boolean chargé) {
	this.chargé = chargé;
}
public Chambre getChambre() {
	return chambre;
}
public void setChambre(Chambre chambre) {
	this.chambre = chambre;
}
public Patient getPatient() {
	return patient;
}
public void setPatient(Patient patient) {
	this.patient = patient;
}


}
