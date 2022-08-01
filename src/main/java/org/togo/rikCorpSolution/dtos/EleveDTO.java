package org.togo.rikCorpSolution.dtos;

import org.togo.rikCorpSolution.enums.Genre;

import java.util.Date;

public class EleveDTO {
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private Date dateDeNaissance;
    private String lieuDeNaissance;
    private Genre genre;
    private String nomParentTuteur;
    private String fonctionParentTuteur;
    private AdresseDTO adresseDTO;

    public EleveDTO(){

    }

    public EleveDTO(long id, String nom, String prenom, String email, String telephone, Date dateDeNaissance, String lieuDeNaissance, Genre genre, String nomParentTuteur, String fonctionParentTuteur) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.dateDeNaissance = dateDeNaissance;
        this.lieuDeNaissance = lieuDeNaissance;
        this.genre = genre;
        this.nomParentTuteur = nomParentTuteur;
        this.fonctionParentTuteur = fonctionParentTuteur;
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

    public AdresseDTO getAdresseDTO() {
        return adresseDTO;
    }

    public void setAdresseDTO(AdresseDTO adresseDTO) {
        this.adresseDTO = adresseDTO;
    }
}
