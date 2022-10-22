package org.togo.rikCorpSolution.controllers;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.togo.rikCorpSolution.dtos.EleveDTO;
import org.togo.rikCorpSolution.dtos.InscriptionDTO;
import org.togo.rikCorpSolution.services.InscriptionService;

import java.io.FileNotFoundException;
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

    @PostMapping(path = "/create",produces = { "application/json"})
    public InscriptionDTO createInscription(@RequestParam(name = "idClasse")long idClasse, @RequestParam(name = "idAdresse")long idAdresse, @RequestBody EleveDTO eleveDTO){
        return inscriptionS.ajouter(new Date(),idClasse,eleveDTO,idAdresse);
    }

    @PutMapping(path = "/edit/{id}",produces = { "application/json"})
    public InscriptionDTO editInscription(@PathVariable("id")long id, @RequestParam(name = "idClasse")long idClasse, @RequestParam(name = "idAdresse")long idAdresse, @RequestParam(name = "idEleve")long idEleve, @RequestBody EleveDTO eleveDTO,@RequestParam(name = "isInscrit")boolean isInscrit) throws ParseException {
        eleveDTO.setId(idEleve);
        System.out.println(isInscrit);
        return inscriptionS.mettreAJour(id,idClasse,idAdresse,eleveDTO,isInscrit);
    }

    @DeleteMapping(path="/delete/{id}")
    public void deleteInscription(@PathVariable("id")long id){
        inscriptionS.supprimer(id);
    }
    @GetMapping(path = "/find/{id}",produces = { "application/json"})
    public InscriptionDTO findInscription(@PathVariable("id")long id){
        return inscriptionS.rechercher(id);
    }
    @GetMapping(path = "/displayAll",produces = { "application/json"})
    public List<InscriptionDTO> displayAll(){
        return inscriptionS.afficherTout();
    }
    @GetMapping(path = "/displayAllAndTrue",produces = { "application/json"})
    public List<InscriptionDTO> displayAllAndTrue(){
        return inscriptionS.afficherToutEtVrai();
    }
    @GetMapping(path = "/displayAllByName",produces = { "application/json"})
    public List<InscriptionDTO> displayAllByName(@RequestParam(name = "nom")String nom){
        return inscriptionS.displayAllByName(nom);
    }
    @GetMapping(path = "/displayAll/report/{format}")
    public String generateReport(@PathVariable("format")String format) throws JRException, FileNotFoundException {
        return inscriptionS.exportReport(format);
    }
    @GetMapping(path = "/report/classe/{id}/eleves")
    public String generateTrueReport(@PathVariable("id")long idClasse) throws JRException, FileNotFoundException {
        return inscriptionS.exportListOfStudentByIdClassEquals(idClasse);
    }
    @GetMapping(path = "/displayAllByPrenom",produces = { "application/json"})
    public List<InscriptionDTO> displayAllByPrenom(@RequestParam(name = "prenom")String prenom){
        return inscriptionS.displayAllByPrenom(prenom);
    }
    @GetMapping(path = "/displayAllByClasse",produces = { "application/json"})
    public List<InscriptionDTO> displayAllByClasse(@RequestParam(name = "classe") String classe){
        return inscriptionS.displayAllByClasse(classe);
    }
    @GetMapping(path = "/Classe/{id}/inscriptions",produces = { "application/json"})
    public List<InscriptionDTO> getStudentByClasseId(@PathVariable(name = "id") long idClasse){
        return inscriptionS.getStudentByClasseId(idClasse);
    }
    @GetMapping(path = "/Classe/{id}/inscriptionsATF",produces = { "application/json"})
    public List<InscriptionDTO> getStudentByClasseIdAndTrueOrFalse(@PathVariable(name = "id") long idClasse){
        return inscriptionS.getStudentByClasseIdAndTrueOrFalse(idClasse);
    }
    @GetMapping(path = "/nbreFilles",produces = { "application/json"})
    public int nbreFilles(){
        return this.inscriptionS.nbreFilles();
    }
    @GetMapping(path = "/nbreGarcons",produces = { "application/json"})
    public int nbreGarcons(){
        return this.inscriptionS.nbreGarcons();
    }
    @GetMapping(path = "/nbreInscriptionAuj",produces = { "application/json"})
    public int nbreInscriptionAuj(){
        return this.inscriptionS.recupNbInscrAuj(new Date(System.currentTimeMillis()));
    }
    @PostMapping(path = "/setTrue/{id}",produces = { "application/json"})
    public InscriptionDTO setTrue(@PathVariable("id")long id){
        return inscriptionS.setTrue(id);
    }
}
