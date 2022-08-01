package org.togo.rikCorpSolution.services;

import org.togo.rikCorpSolution.dtos.PayementDTO;

import java.util.Date;
import java.util.List;

public interface PayementService {

    public PayementDTO ajouter(double montantVerse, Date dateDuPayement,String nomPayeur,long idInscription,long idFrais);
    public void supprimer(long id);
    public PayementDTO mettreAJour(PayementDTO payementDTO, long idInscription,long idFrais);
    public PayementDTO rechercher(long id);
    public List<PayementDTO> afficherTout();
    public List<PayementDTO> searchByKwNom(String nom);
    public List<PayementDTO> searchByKwPrenom(String prenom);
    public List<PayementDTO> getAllByIdInscription(long id);
}
