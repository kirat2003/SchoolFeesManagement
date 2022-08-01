package org.togo.rikCorpSolution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.togo.rikCorpSolution.entities.Adresse;

import java.util.List;

public interface AdresseRepository extends JpaRepository<Adresse,Long> {

    @Query("SELECT ad FROM Adresse ad WHERE ad.pays LIKE %:x%")
    public List<Adresse> displayBykw(@Param("x")String pays);
}
