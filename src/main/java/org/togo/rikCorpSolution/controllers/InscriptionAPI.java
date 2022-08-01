package org.togo.rikCorpSolution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.togo.rikCorpSolution.dtos.EleveDTO;
import org.togo.rikCorpSolution.dtos.InscriptionDTO;
import org.togo.rikCorpSolution.services.InscriptionService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/API/V1/Inscription")
@CrossOrigin("*")
public class InscriptionAPI {

    private final InscriptionService inscriptionS;

    @Autowired
    public InscriptionAPI(InscriptionService inscriptionS){
        this.inscriptionS=inscriptionS;
    }

    @PostMapping(path = "/create")
    public InscriptionDTO createInscription(@RequestParam(name = "idClasse")long idClasse, @RequestParam(name = "idAdresse")long idAdresse, @RequestBody EleveDTO eleveDTO){
        return inscriptionS.ajouter(new Date(),idClasse,eleveDTO,idAdresse);
    }

    @PutMapping(path = "/edit/{id}")
    public InscriptionDTO editInscription(@PathVariable("id")long id, @RequestParam(name = "idClasse")long idClasse, @RequestParam(name = "idAdresse")long idAdresse, @RequestParam(name = "idEleve")long idEleve, @RequestBody EleveDTO eleveDTO,@RequestParam(name = "isInscrit")boolean isInscrit) throws ParseException {
        eleveDTO.setId(idEleve);
        return inscriptionS.mettreAJour(id,idClasse,idAdresse,eleveDTO,isInscrit);
    }

    @DeleteMapping(path="/delete/{id}")
    public void deleteInscription(@PathVariable("id")long id){
        inscriptionS.supprimer(id);
    }
    @GetMapping(path = "/find/{id}")
    public InscriptionDTO findInscription(@PathVariable("id")long id){
        return inscriptionS.rechercher(id);
    }
    @GetMapping(path = "/displayAll")
    public List<InscriptionDTO> displayAll(){
        return inscriptionS.afficherTout();
    }
    @GetMapping(path = "/displayAllAndTrue")
    public List<InscriptionDTO> displayAllAndTrue(){
        return inscriptionS.afficherToutEtVrai();
    }
    @GetMapping(path = "/displayAllByName")
    public List<InscriptionDTO> displayAllByName(@RequestParam(name = "nom")String nom){
        return inscriptionS.displayAllByName(nom);
    }
    @GetMapping(path = "/displayAllByPrenom")
    public List<InscriptionDTO> displayAllByPrenom(@RequestParam(name = "prenom")String prenom){
        return inscriptionS.displayAllByPrenom(prenom);
    }
    @GetMapping(path = "/displayAllByClasse")
    public List<InscriptionDTO> displayAllByClasse(@RequestParam(name = "classe") String classe){
        return inscriptionS.displayAllByClasse(classe);
    }
    @PostMapping(path = "/setTrue/{id}")
    public InscriptionDTO setTrue(@PathVariable("id")long id){
        return inscriptionS.setTrue(id);
    }
}
