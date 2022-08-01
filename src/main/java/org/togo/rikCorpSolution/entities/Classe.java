package org.togo.rikCorpSolution.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Classe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String designation;
    private int nombreDePlaceDisponible;
    private int EffectifDeLaClasse;
    @OneToMany(mappedBy = "classe",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Inscription> inscriptions;
    @OneToMany(mappedBy = "classe",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Frais> frais;


    public Classe(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getNombreDePlaceDisponible() {
        return nombreDePlaceDisponible;
    }

    public int getEffectifDeLaClasse() {
        return EffectifDeLaClasse;
    }

    public void setEffectifDeLaClasse(int effectifDeLaClasse) {
        EffectifDeLaClasse = effectifDeLaClasse;
    }

    public List<Frais> getFrais() {
        return frais;
    }

    public void setFrais(List<Frais> frais) {
        this.frais = frais;
    }

    public void setNombreDePlaceDisponible(int nombreDePlaceDisponible) {
        this.nombreDePlaceDisponible = nombreDePlaceDisponible;
    }

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

//    public Frais getFrais() {
//        return frais;
//    }
//
//    public void setFrais(Frais frais) {
//        this.frais = frais;
//    }

}
