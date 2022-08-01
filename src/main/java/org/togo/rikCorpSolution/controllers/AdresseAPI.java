package org.togo.rikCorpSolution.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.togo.rikCorpSolution.dtos.AdresseDTO;
import org.togo.rikCorpSolution.exceptions.AdresseNotFoundException;
import org.togo.rikCorpSolution.services.AdresseService;

import java.util.List;

@RestController
@RequestMapping(path = "/API/V1/Adresse")
@CrossOrigin("*")
public class AdresseAPI{

    private final AdresseService adresseS;

    @Autowired
    public AdresseAPI(AdresseService adresseS){
        this.adresseS=adresseS;
    }

    @PostMapping(path = "/create",produces = { "application/json"})
    public AdresseDTO createAddress(@RequestBody AdresseDTO adresseDTO) throws AdresseNotFoundException {
        return adresseS.ajouter(adresseDTO);
    }
    @PutMapping(path = "/edit/{id}",produces = { "application/json"})
    public AdresseDTO editAddress(@PathVariable("id")long id, @RequestBody AdresseDTO adresseDTO){
        adresseDTO.setId(id);
        return adresseS.mettreAJour(adresseDTO);
    }
    @GetMapping(path = "/find/{id}",produces = { "application/json"})
    public AdresseDTO displayOneAddress(@PathVariable("id")long id){
        return adresseS.rechercher(id);
    }
    @GetMapping(path = "/displayAll",produces = { "application/json"})
    public List<AdresseDTO> displayAllAddress(){
        return adresseS.afficherTout();
    }
    @GetMapping(path = "/displayAll/{kw}",produces = { "application/json"})
    public List<AdresseDTO> displayAllByKw(@PathVariable("kw")String keyword){
        return adresseS.afficherToutByKw(keyword);
    }
    @DeleteMapping(path = "/delete/{id}")
    public void deleteAdress(@PathVariable("id")long id){
        adresseS.supprimer(id);
    }
}
