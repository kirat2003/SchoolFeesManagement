package org.togo.rikCorpSolution.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Annee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String libelle;
    private Date dateDebut;
    private Date dateFin;
    private boolean isAnneeEnCours;

    @OneToMany(mappedBy = "annee",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Inscription> inscriptions;

    public Annee(){

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

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }
}
