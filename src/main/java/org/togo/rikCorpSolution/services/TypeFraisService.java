package org.togo.rikCorpSolution.services;

import org.togo.rikCorpSolution.dtos.TypeFraisDTO;

import java.util.List;

public interface TypeFraisService {
    public TypeFraisDTO ajouter(TypeFraisDTO typeFraisDTO);
    public void supprimer(long id);
    public TypeFraisDTO mettreAJour(TypeFraisDTO typeFraisDTO);
    public TypeFraisDTO rechercher(long id);
    public List<TypeFraisDTO> afficherTout();

    List<TypeFraisDTO> afficherToutBy(String keyword);
}
