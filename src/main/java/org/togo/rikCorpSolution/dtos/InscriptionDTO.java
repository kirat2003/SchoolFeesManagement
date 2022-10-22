package org.togo.rikCorpSolution.dtos;

import java.util.Date;

public class InscriptionDTO {

    private long id;
    private boolean isInscrit;
    private Date dateInscription;
    private EleveDTO eleveDTO;
    private AnneeDTO anneeDTO;
    private ClasseDTO classeDTO;

    public InscriptionDTO(){

    }

    public InscriptionDTO(long id, Date dateInscription) {
        this.id = id;
        this.dateInscription = dateInscription;
    }

    public InscriptionDTO(long id) {
    }
    public boolean getIsInscrit() {
        return isInscrit;
    }

    public void setIsInscrit(boolean inscrit) {
        isInscrit = inscrit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public EleveDTO getEleveDTO() {
        return eleveDTO;
    }

    public void setEleveDTO(EleveDTO eleveDTO) {
        this.eleveDTO = eleveDTO;
    }

    public AnneeDTO getAnneeDTO() {
        return anneeDTO;
    }

    public void setAnneeDTO(AnneeDTO anneeDTO) {
        this.anneeDTO = anneeDTO;
    }

    public ClasseDTO getClasseDTO() {
        return classeDTO;
    }

    public void setClasseDTO(ClasseDTO classeDTO) {
        this.classeDTO = classeDTO;
    }
}
