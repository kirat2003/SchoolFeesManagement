package org.togo.rikCorpSolution.dtos;

import org.togo.rikCorpSolution.enums.Genre;

import java.util.Date;

public class EleveSpecialDTO {

    private long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private Date dateDeNaissance;
    private String lieuDeNaissance;
    private String MatriculeSpecial;
    private Genre genre;

    public EleveSpecialDTO(){

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

    public String getMatriculeSpecial() {
        return MatriculeSpecial;
    }

    public void setMatriculeSpecial(String matriculeSpecial) {
        MatriculeSpecial = matriculeSpecial;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
