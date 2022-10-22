package org.togo.rikCorpSolution.services;

import net.sf.jasperreports.engine.JRException;
import org.togo.rikCorpSolution.dtos.EleveDTO;
import org.togo.rikCorpSolution.dtos.InscriptionDTO;

import java.io.FileNotFoundException;
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
    List<InscriptionDTO> getStudentByClasseId(long idClasse);

    List<InscriptionDTO> getStudentByClasseIdAndTrueOrFalse(long idClasse);

    InscriptionDTO setTrue(long id);
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException;

    public String exportListOfStudentByIdClassEquals(long idClasse) throws JRException, FileNotFoundException;

    int nbreGarcons();

    int nbreFilles();

    int recupNbInscrAuj(Date date);
}
