package org.togo.rikCorpSolution.dtos;

import java.util.Date;

public class AnneeDTO {

    private long id;
    private String libelle;
    private Date dateDebut;
    private Date dateFin;
    private boolean isAnneeEnCours;

    public AnneeDTO(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public boolean isAnneeEnCours() {
        return isAnneeEnCours;
    }

    public void setAnneeEnCours(boolean anneeEnCours) {
        isAnneeEnCours = anneeEnCours;
    }
}
