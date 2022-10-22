package org.togo.rikCorpSolution.controllers;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.togo.rikCorpSolution.dtos.PayementDTO;
import org.togo.rikCorpSolution.services.PayementService;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/API/V1/Payement")
@CrossOrigin("*")
public class PayementAPI {

    private final PayementService payementS;

    @Autowired
    public PayementAPI(PayementService payementS){
        this.payementS = payementS;
    }

    @PostMapping(path = "/create",produces = { "application/json"})
    public PayementDTO createPayement(@RequestParam(name = "montantVerse") double montantVerse,@RequestParam(name = "nomPayeur") String nomPayeur,@RequestParam(name = "idInscription") long idInscription,@RequestParam(name = "idFrais") long idFrais){
        return payementS.ajouter(montantVerse, new Date(), nomPayeur, idInscription,idFrais);
    }

    @PutMapping(path = "/edit/{id}",produces = { "application/json"})
    public PayementDTO editPayement(@PathVariable("id")long id,@RequestParam(name = "montantVerse") double montantVerse,@RequestParam(name = "nomPayeur") String nomPayeur,@RequestParam(name = "idInscription") long idInscription,@RequestParam(name = "idFrais") long idFrais){
        return payementS.mettreAJour(new PayementDTO(id,montantVerse,new Date(),nomPayeur),idInscription,idFrais);
    }
    /*
    @PutMapping(path = "/edit/Eleve/Payement/{id}")
    public PayementDTO editPayementAndEleve(@PathVariable("id")long id,@RequestParam(name = "montantVerse") double montantVerse,@RequestParam(name = "dateDuPayement") Date dateDuPayement,@RequestParam(name = "nomPayeur") String nomPayeur,@RequestParam(name = "idInscription") long idInscription,){
        return payementS.mettreAJour(new PayementDTO(id,montantVerse,dateDuPayement,nomPayeur),idInscription);
    }*/

    @DeleteMapping(path = "/delete/{id}")
    public void deletePayement(@PathVariable("id")long id){
        payementS.supprimer(id);
    }

    @GetMapping(path = "/find/{id}",produces = { "application/json"})
    public PayementDTO findPayement(@PathVariable("id")long id){
        return payementS.rechercher(id);
    }
    @GetMapping(path = "/displayAll",produces = { "application/json"})
    public List<PayementDTO> displayAll(){
        return payementS.afficherTout();
    }
    @GetMapping(path = "/searchByKwNom",produces = { "application/json"})
    public List<PayementDTO> searchByKwNom(@RequestParam(name = "nom",defaultValue = "")String nom){
        return payementS.searchByKwNom(nom);
    }
    @GetMapping(path = "/searchByKwPrenom",produces = { "application/json"})
    public List<PayementDTO> searchByKwPrenom(@RequestParam(name = "prenom",defaultValue = "")String prenom){
        return payementS.searchByKwPrenom(prenom);
    }
    @GetMapping(path = "/Inscription/{id}/payements",produces = { "application/json"})
    public List<PayementDTO> getAllByIdInscription(@PathVariable("id")long idInscription){
        return payementS.getAllByIdInscription(idInscription);
    }
    @GetMapping(path = "/sommeVerse")
    public Double getTotalVerse(@RequestParam(name = "idInscription")long idInscription,@RequestParam(name = "idFrais")long idFrais){
        return payementS.sommeVerse(idInscription,idFrais);
    }
    @GetMapping(path = "/{id}/report")
    public String reportPayementWhichIdIs(@PathVariable("id")long idPayement) throws JRException, FileNotFoundException {
        return payementS.exportReport(idPayement);
    }
    @GetMapping(path = "/montantTotalAuj")
    public double montantTotalAuj(){
        return payementS.selectAmoutAllFromPayementToday(new Date());
    }
}
