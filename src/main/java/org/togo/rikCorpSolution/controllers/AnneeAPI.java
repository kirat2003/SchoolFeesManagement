package org.togo.rikCorpSolution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.togo.rikCorpSolution.dtos.AnneeDTO;
import org.togo.rikCorpSolution.entities.Annee;
import org.togo.rikCorpSolution.exceptions.AnneeNotFoundException;
import org.togo.rikCorpSolution.services.AnneeService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/API/V1/Annee")
@CrossOrigin("*")
public class AnneeAPI {
    private AnneeService anneeS;

    @Autowired
    public AnneeAPI(AnneeService anneeS){
        this.anneeS=anneeS;
    }

    @PostMapping(path = "/create",produces = { "application/json"})
    public AnneeDTO saveYear(@RequestBody AnneeDTO anneeDTO){
        return anneeS.ajouter(anneeDTO);
    }
    @PutMapping(path = "/update/{id}",produces = { "application/json"})
    public AnneeDTO updateYear(@PathVariable("id") long id,@RequestBody AnneeDTO anneeDTO){
        anneeDTO.setId(id);
        return anneeS.mettreAJour(anneeDTO);
    }
    @GetMapping(path = "/find/{id}",produces = { "application/json"})
    public AnneeDTO findYear(@PathVariable("id") long id) throws AnneeNotFoundException {
        return anneeS.rechercher(id);
    }
    @GetMapping(path = "/displayAll",produces = { "application/json"})
    public List<AnneeDTO> displayAll(){
        return anneeS.afficherTout();
    }

    @GetMapping(path = "/displayAll/{keyword}",produces = { "application/json"})
    public List<AnneeDTO> displayAll(@PathVariable("keyword")String libelle){
        return anneeS.afficherToutParRapportAKeyword(libelle);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteYear(@PathVariable("id")long id){
        anneeS.supprimer(id);
    }
    @GetMapping(path = "/getCurrentYear",produces = { "application/json"})
    public AnneeDTO getCurrentYear(){
        return anneeS.getCurrentYear();
    }
    @GetMapping(path = "/setItToTrue/{id}",produces = { "application/json"})
    public AnneeDTO setItToTrue(@PathVariable("id")long id){
        return anneeS.setItToTrue(id);
    }
}
