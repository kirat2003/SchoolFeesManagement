package org.togo.rikCorpSolution.services;

import org.togo.rikCorpSolution.dtos.AdresseDTO;
import org.togo.rikCorpSolution.exceptions.AdresseNotFoundException;

import java.util.List;

public interface AdresseService {
    public AdresseDTO ajouter(AdresseDTO adresseDTO) throws AdresseNotFoundException;
    public void supprimer(long id);
    public AdresseDTO mettreAJour(AdresseDTO adresseDTO);
    public AdresseDTO rechercher(long id);
    public List<AdresseDTO> afficherTout();
    List<AdresseDTO> afficherToutByKw(String keyword);
}
