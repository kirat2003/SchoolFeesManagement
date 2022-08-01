package org.togo.rikCorpSolution.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class TypeFrais implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String libelle;

    @OneToMany(mappedBy = "typeFrais",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Frais> frais;

    public TypeFrais(){

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

    public List<Frais> getFrais() {
        return frais;
    }

    public void setFrais(List<Frais> frais) {
        this.frais = frais;
    }
}
