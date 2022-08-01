package org.togo.rikCorpSolution.services;

import org.togo.rikCorpSolution.dtos.FraisDTO;

import java.util.List;

public interface FraisService {
    public FraisDTO ajouter(double montantFrais,String designation,long idClasse,long idTypeFrais);
    public void supprimer(long id);
    public FraisDTO mettreAJour(FraisDTO fraisDTO,long idTypeFrais,long idClasse);
    public FraisDTO rechercher(long id);
    public List<FraisDTO> afficherTout();
    public List<FraisDTO> searchByDesignFrais(String designationFrais);
    public List<FraisDTO> searchByDesignClass(String designationClasse);
    public List<FraisDTO> searchBylibTypeFrais(String libelleTypeFrais);

    List<FraisDTO> displayFraisByClass(long id);
}
