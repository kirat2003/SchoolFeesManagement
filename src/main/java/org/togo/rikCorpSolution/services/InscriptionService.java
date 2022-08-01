package org.togo.rikCorpSolution.services;

import org.togo.rikCorpSolution.dtos.EleveDTO;
import org.togo.rikCorpSolution.dtos.InscriptionDTO;

import java.util.Date;
import java.util.List;

public interface InscriptionService {
    public InscriptionDTO ajouter(Date dateInscription, long idClasse, EleveDTO eleveDTO,long idAdresse);
    public void supprimer(long id);
    public InscriptionDTO mettreAJour(long id,long idClasse,long idAdresse,EleveDTO eleveDTO,boolean isInscrit);
    public InscriptionDTO rechercher(long id);
    public List<InscriptionDTO> afficherTout();
    public List<InscriptionDTO> displayAllByName(String nom);
    public List<InscriptionDTO> displayAllByClasse(String classe);

    List<InscriptionDTO> displayAllByPrenom(String prenom);

    List<InscriptionDTO> afficherToutEtVrai();

    InscriptionDTO setTrue(long id);
}
