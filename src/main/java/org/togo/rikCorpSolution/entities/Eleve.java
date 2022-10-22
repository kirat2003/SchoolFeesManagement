package org.togo.rikCorpSolution.entities;

import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;
import org.togo.rikCorpSolution.enums.Genre;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Eleve implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private Date dateDeNaissance;
    private String lieuDeNaissance;

    private String nomParentTuteur;
    private String fonctionParentTuteur;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Transient
    private String concat;
    @Transient
    private String adresseTotal;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_adresse")
    private Adresse adresse;

    @OneToMany(mappedBy = "eleve",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Inscription> inscriptions;

    public Eleve(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getLieuDeNaissance() {
        return lieuDeNaissance;
    }

    public void setLieuDeNaissance(String lieuDeNaissance) {
        this.lieuDeNaissance = lieuDeNaissance;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public String getNomParentTuteur() {
        return nomParentTuteur;
    }

    public void setNomParentTuteur(String nomParentTuteur) {
        this.nomParentTuteur = nomParentTuteur;
    }

    public String getFonctionParentTuteur() {
        return fonctionParentTuteur;
    }

    public void setFonctionParentTuteur(String fonctionParentTuteur) {
        this.fonctionParentTuteur = fonctionParentTuteur;
    }

    public String getConcat() {
        return concat;
    }

    public void setConcat(String concat) {
        this.concat = concat;
    }

    public String getAdresseTotal() {
        return adresseTotal;
    }

    public void setAdresseTotal(String adresseTotal) {
        this.adresseTotal = adresseTotal;
    }
}
