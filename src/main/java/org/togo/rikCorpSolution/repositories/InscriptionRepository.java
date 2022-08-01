package org.togo.rikCorpSolution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.togo.rikCorpSolution.entities.Inscription;

import java.util.List;

public interface InscriptionRepository extends JpaRepository<Inscription,Long> {
    @Query("SELECT i FROM Inscription i ORDER BY i.dateInscription")
    public List<Inscription> displayAllByDateInscription();
    @Query("SELECT i FROM Inscription i WHERE i.isInscrit=true ORDER BY i.dateInscription")
    public List<Inscription> displayAllByDateInscriptionAndTrue();

    @Query("SELECT i FROM Inscription i WHERE i.eleve.nom LIKE %:nom%")
    public List<Inscription> displayAllByName(@Param("nom")String nom);
    @Query("SELECT i FROM Inscription i WHERE i.eleve.prenom LIKE %:nom%")
    public List<Inscription> displayAllByPrenom(@Param("nom")String nom);
    @Query("SELECT i FROM Inscription i WHERE i.classe.designation LIKE %:designation%")
    public List<Inscription> displayAllByClasse(@Param("designation")String designation);
}
