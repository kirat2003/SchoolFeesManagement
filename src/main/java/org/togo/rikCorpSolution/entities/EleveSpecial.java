package org.togo.rikCorpSolution.entities;

import org.togo.rikCorpSolution.enums.Genre;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class EleveSpecial implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Inscription inscription;
    @ManyToOne(fetch = FetchType.EAGER)
    private Frais frais;

    public EleveSpecial() {
    }

    public EleveSpecial(long id, Inscription inscription, Frais frais) {
        this.id = id;
        this.inscription = inscription;
        this.frais = frais;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Inscription getInscription() {
        return inscription;
    }

    public void setInscription(Inscription inscription) {
        this.inscription = inscription;
    }

    public Frais getFrais() {
        return frais;
    }

    public void setFrais(Frais frais) {
        this.frais = frais;
    }
}
