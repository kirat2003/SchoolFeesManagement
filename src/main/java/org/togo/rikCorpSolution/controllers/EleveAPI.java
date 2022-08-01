package org.togo.rikCorpSolution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.togo.rikCorpSolution.dtos.EleveDTO;
import org.togo.rikCorpSolution.enums.Genre;
import org.togo.rikCorpSolution.services.EleveService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/API/V1/Eleve")
@CrossOrigin("*")
public class EleveAPI {

    private final EleveService eleveS;
    @Autowired
    public EleveAPI(EleveService eleveS){
        this.eleveS=eleveS;
    }

    @PostMapping(path = "/create",produces = { "application/json"})
    public EleveDTO createEleve(@RequestParam(name = "nom") String nom,@RequestParam(name = "prenom") String prenom,@RequestParam(name = "email") String email,@RequestParam(name = "telephone") String telephone,@RequestParam(name = "dateDeNaissance") Date dateDeNaissance,@RequestParam(name = "lieuDeNaissance") String lieuDeNaissance,@RequestParam(name = "genre") Genre genre,@RequestParam(name = "nomParentTuteur") String nomParentTuteur,@RequestParam(name = "fonctionParentTuteur") String fonctionParentTuteur,@RequestParam(name = "idAdresse") long idAdresse){
        return eleveS.ajouter(nom,prenom,email,telephone,dateDeNaissance,lieuDeNaissance,genre,nomParentTuteur,fonctionParentTuteur,idAdresse);
    }

    @PutMapping(path = "/edit/{id}",produces = { "application/json"})
    public EleveDTO editEleve(@PathVariable("id")long id, @RequestParam(name = "nom") String nom, @RequestParam(name = "prenom") String prenom, @RequestParam(name = "email") String email, @RequestParam(name = "telephone") String telephone, @RequestParam(name = "dateDeNaissance") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateDeNaissance, @RequestParam(name = "lieuDeNaissance") String lieuDeNaissance, @RequestParam(name = "genre") Genre genre, @RequestParam(name = "nomParentTuteur") String nomParentTuteur, @RequestParam(name = "fonctionParentTuteur") String fonctionParentTuteur, @RequestParam(name = "idAdresse") long idAdresse){
        return eleveS.mettreAJour(new EleveDTO(id,nom,prenom,email,telephone,dateDeNaissance,lieuDeNaissance,genre,nomParentTuteur,fonctionParentTuteur),idAdresse);
    }

    @DeleteMapping(path = "/delete/{id} ")
    public void deleteEleve(@PathVariable("id")long id){
        eleveS.supprimer(id);
    }

    @GetMapping(path = "/find/{id}",produces = { "application/json"})
    public EleveDTO findEleve(@PathVariable("id")long id){
        return eleveS.rechercher(id);
    }
    @GetMapping(path = "/displayAll",produces = { "application/json"})
    public List<EleveDTO> displayAll(){
        return eleveS.afficherTout();
    }
    @GetMapping(path = "/displayAllByName/{nom}",produces = { "application/json"})
    public List<EleveDTO> displayAllByName(@PathVariable("nom")String nom){
        return eleveS.displayAllByName(nom);
    }
    @GetMapping(path = "/displayAllByPrenom/{prenom}",produces = { "application/json"})
    public List<EleveDTO> displayAllByPrenom(@PathVariable(value = "prenom")String prenom){
        return eleveS.displayAllByPrenom(prenom);
    }
}
