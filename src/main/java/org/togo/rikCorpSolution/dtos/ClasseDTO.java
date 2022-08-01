package org.togo.rikCorpSolution.dtos;


public class ClasseDTO {
    private long id;
    private String designation;
    private int nombreDePlaceDisponible;
    private int EffectifDeLaClasse;


    public ClasseDTO(){

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

    public void setNombreDePlaceDisponible(int nombreDePlaceDisponible) {
        this.nombreDePlaceDisponible = nombreDePlaceDisponible;
    }

    public int getEffectifDeLaClasse() {
        return EffectifDeLaClasse;
    }

    public void setEffectifDeLaClasse(int effectifDeLaClasse) {
        EffectifDeLaClasse = effectifDeLaClasse;
    }
}
