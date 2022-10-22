package org.togo.rikCorpSolution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.togo.rikCorpSolution.entities.Payement;

import java.util.List;

public interface PayementRepository extends JpaRepository<Payement,Long> {
    @Query("SELECT p FROM Payement p ORDER BY p.dateDuPayement")
    public List<Payement> displayAllPayementOrderedByDatePayement();
    @Query("SELECT p FROM Payement p WHERE p.inscription.eleve.nom LIKE %:x%")
    public List<Payement> searchByKwNom(@Param("x")String nom);
    @Query("SELECT p FROM Payement p WHERE p.inscription.eleve.prenom LIKE %:x%")
    public List<Payement> searchByKwPrenom(@Param("x")String prenom);
    @Query("SELECT p FROM Payement p WHERE p.inscription.id=:id")
    public List<Payement> getAllByIdInscription(@Param("id")long id);
    @Query("SELECT SUM (p.montantVerse) FROM Payement p WHERE p.inscription.id=:idInscription AND p.frais.id=:idFrais")
    public Double sommeVerse(@Param("idInscription") long idInscription, @Param("idFrais")long idFrais);
    @Query("SELECT p FROM Payement  p WHERE p.id=:IdPayement")
    public List<Payement> getPayementById(@Param("IdPayement") long IdPayement);
}
