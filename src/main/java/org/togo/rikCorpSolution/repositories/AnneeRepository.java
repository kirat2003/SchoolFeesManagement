package org.togo.rikCorpSolution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.togo.rikCorpSolution.entities.Annee;

import java.util.List;

public interface AnneeRepository extends JpaRepository<Annee,Long> {
    @Query("SELECT a FROM Annee a ORDER BY a.libelle")
    public List<Annee> displayAllOrderByLib();
    @Query("SELECT a FROM Annee a WHERE  a.libelle like %:x%")
    public List<Annee> displayAllByLib(@Param("x")String libelle);
    @Query("select a from Annee a where a.isAnneeEnCours=true")
    public Annee selectCurrentYear();
    @Query("SELECT a FROM Annee a WHERE a.id<>:id")
    public List<Annee> selectAllWithoutThisId(@Param("id")long id);
}
