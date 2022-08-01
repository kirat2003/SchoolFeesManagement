package org.togo.rikCorpSolution.dtos;


public class FraisDTO {
    private long id;
    private double montantFrais;
    private String designation;
    private ClasseDTO classeDTO;
    private TypeFraisDTO typeFraisDTO;

    public FraisDTO(){

    }

    public FraisDTO(long id, double montantFrais, String designation) {
        this.id = id;
        this.montantFrais = montantFrais;
        this.designation = designation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getMontantFrais() {
        return montantFrais;
    }

    public void setMontantFrais(double montantFrais) {
        this.montantFrais = montantFrais;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public ClasseDTO getClasseDTO() {
        return classeDTO;
    }

    public void setClasseDTO(ClasseDTO classeDTO) {
        this.classeDTO = classeDTO;
    }

    public TypeFraisDTO getTypeFraisDTO() {
        return typeFraisDTO;
    }

    public void setTypeFraisDTO(TypeFraisDTO typeFraisDTO) {
        this.typeFraisDTO = typeFraisDTO;
    }
}
