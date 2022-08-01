package org.togo.rikCorpSolution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.togo.rikCorpSolution.dtos.ClasseDTO;
import org.togo.rikCorpSolution.services.ClasseService;

import java.util.List;

@RestController
@RequestMapping(path = "/API/V1/Classe")
@CrossOrigin("*")
public class ClasseAPI {

    private final ClasseService classeS;
    @Autowired
    public ClasseAPI(ClasseService classeS){
        this.classeS=classeS;
    }

    @PutMapping(path = "/edit/{id}",produces = { "application/json"})
    public ClasseDTO editClasse(@PathVariable("id")long id,@RequestBody ClasseDTO classeDTO){
        classeDTO.setId(id);
        return classeS.mettreAJour(classeDTO);
    }
    @PostMapping(path = "/create",produces = { "application/json"})
    public ClasseDTO createClasse(@RequestParam(name = "nbrePlaceDispo")int nbrePlaceDispo,@RequestParam(name = "designation")String designation){
        return classeS.ajouter(designation,nbrePlaceDispo);
    }
    @GetMapping(path = "/displayAll",produces = { "application/json"})
    public List<ClasseDTO> displayAll(){
        return classeS.afficherTout();
    }
    @GetMapping(path = "/find/{id}",produces = { "application/json"})
    public ClasseDTO findAClass(@PathVariable("id")long id){
        return classeS.rechercher(id);
    }
    @GetMapping(path="/findByKeyword",produces = { "application/json"})
    public List<ClasseDTO> findByLibelle(@RequestParam(name = "keyword")String keyword){
        return classeS.findByLibelle(keyword);
    }
    @DeleteMapping(path = "/delete/{id}")
    public void deleteClasse(@PathVariable("id")long id){
        classeS.supprimer(id);
    }

}
