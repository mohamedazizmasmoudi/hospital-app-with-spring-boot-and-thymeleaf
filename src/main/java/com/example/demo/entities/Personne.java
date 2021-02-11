package com.example.demo.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
@Data

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Personne {
	@Id
	private long cin;
	@Basic
	@Column(name = "nom", nullable = false, length = 32,unique = true)
	private String nom;
	
	@Basic
    @Column(name = "prenom", nullable = false, length = 32)
	private String prenom;
	@Basic
    @Column(name = "age", nullable = true)
	private int age ;
	@Column(name = "tel", nullable = false, length = 8)
	@NotNull
	@NotBlank(message="Please enter your phone number")
	private String phoneNumber;
	@NotEmpty
    @Email(message = "mail invalide")
	private String email;
	public long getCin() {
		return cin;
	}
	public void setCin(long cin) {
		this.cin = cin;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
