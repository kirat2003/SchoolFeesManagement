package org.togo.rikCorpSolution.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Payement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double montantVerse;
    private Date dateDuPayement;
    private String nomPayeur;

    @ManyToOne(fetch = FetchType.EAGER)
    private Inscription inscription;
    @ManyToOne(fetch = FetchType.EAGER)
    private Frais frais;

    public Frais getFrais() {
        return frais;
    }

    public void setFrais(Frais frais) {
        this.frais = frais;
    }

    public Payement(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getMontantVerse() {
        return montantVerse;
    }

    public void setMontantVerse(double montantVerse) {
        this.montantVerse = montantVerse;
    }

    public Date getDateDuPayement() {
        return dateDuPayement;
    }

    public void setDateDuPayement(Date dateDuPayement) {
        this.dateDuPayement = dateDuPayement;
    }

    public String getNomPayeur() {
        return nomPayeur;
    }

    public void setNomPayeur(String nomPayeur) {
        this.nomPayeur = nomPayeur;
    }

    public Inscription getInscription() {
        return inscription;
    }

    public void setInscription(Inscription inscription) {
        this.inscription = inscription;
    }
}
