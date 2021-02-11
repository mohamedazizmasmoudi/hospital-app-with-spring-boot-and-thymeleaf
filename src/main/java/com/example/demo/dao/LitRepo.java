package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Chambre;
import com.example.demo.entities.Lit;
import com.example.demo.entities.Patient;

public interface LitRepo extends JpaRepository<Lit, Long>{
	List<Lit> findByChambre(Chambre chambre);
	@Query("SELECT COUNT(*) FROM Lit l WHERE chambre_id=:x")
	int countlit(@Param("x") long id);
	List<Lit> findByChargé(Boolean chargé );
	@Query("SELECT COUNT(*) FROM Lit l WHERE chambre_id=:x AND l.chargé=false")
	int countlitnonchargé(@Param("x") long id);
	
	
	Lit findByPatient(Patient patient);
	@Query("SELECT l.id , c.numero FROM Lit l , Chambre c   WHERE c=l.chambre AND    l.chargé=FALSE  ")
	List<Object> findByChargéchambre();

	@Query("SELECT COUNT(*) FROM Lit l")
	int countalllit();
	
	@Query("SELECT l FROM Lit l   WHERE l.patient=:x  ")
	Lit litdepatient(@Param("x") Patient x);
}
