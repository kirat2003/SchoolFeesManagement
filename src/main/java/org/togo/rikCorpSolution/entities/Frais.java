package org.togo.rikCorpSolution.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Frais implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double montantFrais;
    private String designation;

    @ManyToOne(fetch = FetchType.EAGER)
    private Classe classe;

    @ManyToOne(fetch = FetchType.EAGER)
    private TypeFrais typeFrais;

    @OneToMany(mappedBy = "frais",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Payement> payements;
    public Frais() {
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

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public TypeFrais getTypeFrais() {
        return typeFrais;
    }

    public void setTypeFrais(TypeFrais typeFrais) {
        this.typeFrais = typeFrais;
    }

    public List<Payement> getPayements() {
        return payements;
    }

    public void setPayements(List<Payement> payements) {
        this.payements = payements;
    }
}
