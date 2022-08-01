package org.togo.rikCorpSolution.services;

import org.togo.rikCorpSolution.dtos.EleveDTO;
import org.togo.rikCorpSolution.enums.Genre;

import java.util.Date;
import java.util.List;

public interface EleveService {

    public EleveDTO ajouter(String nom,
                            String prenom,
                            String email,
                            String telephone,
                            Date dateDeNaissance,
                            String lieuDeNaissance,
                            Genre genre,
                            String nomParentTuteur,
                            String fonctionParentTuteur,
                            long idAdresse);
    public void supprimer(long id);
    public EleveDTO mettreAJour(EleveDTO eleveDTO,long idAdresse);
    public EleveDTO rechercher(long id);
    public List<EleveDTO> afficherTout();

    public List<EleveDTO> displayAllByName(String nom);

    public List<EleveDTO> displayAllByPrenom(String prenom);
}
