package org.togo.rikCorpSolution.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Engagement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private long id;
    private long taux;
    private String typeEngagement;
    @ManyToOne(fetch = FetchType.EAGER)
    private Inscription inscription;
    @ManyToOne(fetch = FetchType.EAGER)
    private Frais frais;

    public Engagement() {
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
