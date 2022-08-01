package org.togo.rikCorpSolution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.togo.rikCorpSolution.entities.Eleve;

import java.util.List;

public interface EleveRepository extends JpaRepository<Eleve,Long> {

    @Query("SELECT e FROM Eleve e ORDER BY e.nom,e.prenom")
    public List<Eleve> displayAllStudentOrderedByNameAndSurname();

    @Query("SELECT e FROM Eleve e WHERE e.nom like %:x%")
    public List<Eleve> displayAllByName(@Param("x")String nom);
    @Query("SELECT e FROM Eleve e WHERE e.prenom like %:x%")
    public List<Eleve> displayAllByPrenom(@Param("x")String prenom);

}
