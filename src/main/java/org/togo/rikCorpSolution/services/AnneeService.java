package org.togo.rikCorpSolution.services;


import org.togo.rikCorpSolution.dtos.AnneeDTO;
import org.togo.rikCorpSolution.exceptions.AnneeNotFoundException;

import java.util.List;

public interface AnneeService {
    public AnneeDTO ajouter(AnneeDTO anneeDTO);
    public void supprimer(long id);
    public AnneeDTO mettreAJour(AnneeDTO anneeDTO);
    public AnneeDTO rechercher(long id) throws AnneeNotFoundException;
    public List<AnneeDTO> afficherTout();

    List<AnneeDTO> afficherToutParRapportAKeyword(String libelle);

    AnneeDTO getCurrentYear();
    AnneeDTO setItToTrue(long id);
}
