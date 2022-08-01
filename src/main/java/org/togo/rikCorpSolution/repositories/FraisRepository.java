package org.togo.rikCorpSolution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.togo.rikCorpSolution.entities.Frais;

import java.util.List;

public interface FraisRepository extends JpaRepository<Frais,Long> {

    @Query("SELECT f FROM Frais f ORDER BY f.designation")
    public List<Frais> displayAllByDesignation();
    @Query("SELECT f FROM Frais f WHERE f.classe.id=:id")
    public List<Frais> displayFraisByClass(@Param("id")long id);
    @Query("SELECT f FROM Frais f WHERE f.designation LIKE %:x%")
    public List<Frais> searchByDesignFrais(@Param("x") String designationFrais );
    @Query("SELECT f FROM Frais f WHERE f.classe.designation LIKE %:x%")
    public List<Frais> searchByDesignClass(@Param("x") String designationClasse);
    @Query("SELECT f FROM Frais f WHERE f.typeFrais.libelle LIKE %:x%")
    public List<Frais> searchBylibTypeFrais(@Param("x") String libelleTypeFrais);

}
