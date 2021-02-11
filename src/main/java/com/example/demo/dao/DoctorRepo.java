package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Docteur;

public interface DoctorRepo extends JpaRepository<Docteur,Long>{
	@Query("SELECT COUNT(*) FROM Docteur l")
	int countalldoctor();
}
