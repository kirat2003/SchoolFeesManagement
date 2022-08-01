package org.togo.rikCorpSolution.services;

import org.togo.rikCorpSolution.dtos.ClasseDTO;

import java.util.List;

public interface ClasseService {
    public ClasseDTO ajouter(String designation,int nombreDePlaceDisponible);
    public void supprimer(long id);
    public ClasseDTO mettreAJour(ClasseDTO classeDTO);
    public ClasseDTO rechercher(long id);
    public List<ClasseDTO> afficherTout();

    List<ClasseDTO> findByLibelle(String keyword);
}
