package org.togo.rikCorpSolution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.togo.rikCorpSolution.entities.Classe;

import java.util.Collection;
import java.util.List;

public interface ClasseRepository extends JpaRepository<Classe,Long> {

    @Query("SELECT c FROM Classe c order by c.designation")
    List<Classe> findByLibelle();
    @Query("SELECT c FROM Classe c WHERE c.designation LIKE %:x%")
    List<Classe> findByLibelle2(@Param("x")String designation);
}
