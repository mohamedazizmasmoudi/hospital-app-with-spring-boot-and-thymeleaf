package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Patient;

import antlr.collections.List;

public interface PatientRepo extends JpaRepository<Patient,Long>{
	@Query("SELECT COUNT(*) FROM Patient l")
	int countallpattient();
	
}
