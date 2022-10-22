package org.togo.rikCorpSolution.dtos;

import java.util.Date;

public class PayementDTO {

    private long id;
    private double montantVerse;
    private double resteApayer;
    private Date dateDuPayement;
    private String nomPayeur;
    private InscriptionDTO inscriptionDTO;
    private FraisDTO fraisDTO;

    public PayementDTO(){

    }

    public PayementDTO(long id, double montantVerse, Date dateDuPayement, String nomPayeur) {
        this.id = id;
        this.montantVerse = montantVerse;
        this.dateDuPayement = dateDuPayement;
        this.nomPayeur = nomPayeur;
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

    public InscriptionDTO getInscriptionDTO() {
        return inscriptionDTO;
    }

    public void setInscriptionDTO(InscriptionDTO inscriptionDTO) {
        this.inscriptionDTO = inscriptionDTO;
    }

    public FraisDTO getFraisDTO() {
        return fraisDTO;
    }

    public void setFraisDTO(FraisDTO fraisDTO) {
        this.fraisDTO = fraisDTO;
    }

    public double getResteApayer() {
        return resteApayer;
    }

    public void setResteApayer(double resteApayer) {
        this.resteApayer = resteApayer;
    }
}
