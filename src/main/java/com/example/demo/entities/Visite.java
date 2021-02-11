package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Visite {
	@Id
	@Column(name="visite_id", nullable=false)
	private long id ;
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date date_visite;
	@ManyToOne
	@JoinColumn(name = "docId")
	private Docteur docteur;
	
	@ManyToOne
	@JoinColumn(name = "patientId")
	private Patient patient;

}
