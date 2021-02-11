package com.example.demo.entities;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Departement {
	@Id
	@Column(name = "dep_id", nullable = false)
	private long id;
	@Basic
	@Column(name="dep", nullable=false)
  private String nom_dep;
	@OneToMany(mappedBy = "departement")
	private Collection<Docteur> docteurs ;

}
