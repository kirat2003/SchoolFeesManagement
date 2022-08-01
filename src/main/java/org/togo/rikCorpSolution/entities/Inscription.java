package org.togo.rikCorpSolution.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Inscription implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean isInscrit;
    private Date dateInscription;

    @ManyToOne(fetch = FetchType.EAGER)
    private Eleve eleve;
    @ManyToOne(fetch = FetchType.EAGER)
    private Annee annee;
    @ManyToOne(fetch = FetchType.EAGER)
    private Classe classe;
    @ManyToOne(fetch = FetchType.EAGER)
    private EleveSpecial eleveSpecial;

    @OneToMany(mappedBy = "inscription",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Payement> payements;
    public Inscription(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getIsInscrit() {
        return isInscrit;
    }

    public void setIsInscrit(boolean inscrit) {
        isInscrit = inscrit;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public EleveSpecial getEleveSpecial() {
        return eleveSpecial;
    }

    public void setEleveSpecial(EleveSpecial eleveSpecial) {
        this.eleveSpecial = eleveSpecial;
    }

    public List<Payement> getPayements() {
        return payements;
    }

    public void setPayements(List<Payement> payements) {
        this.payements = payements;
    }
}
