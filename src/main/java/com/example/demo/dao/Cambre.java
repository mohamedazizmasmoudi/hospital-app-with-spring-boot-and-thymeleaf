package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Chambre;
public interface Cambre extends JpaRepository<Chambre,Long>{
	
	List<Chambre> findByNumero(int num);
	@Query("SELECT COUNT(*) FROM Chambre l")
	int countallchambre();

}
