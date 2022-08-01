package org.togo.rikCorpSolution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.togo.rikCorpSolution.entities.TypeFrais;

import java.util.List;

public interface TypeFraisRepository extends JpaRepository<TypeFrais,Long> {

    @Query("SELECT t FROM TypeFrais t ORDER BY t.libelle")
    public List<TypeFrais> displayAllTypeFraisByLibelle();
    @Query("SELECT t FROM TypeFrais t WHERE t.libelle like %:x%")
    public List<TypeFrais> displayAllTypeFraisByLibelle(@Param("x")String keyword);
}
