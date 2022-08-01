package org.togo.rikCorpSolution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.togo.rikCorpSolution.dtos.TypeFraisDTO;
import org.togo.rikCorpSolution.services.TypeFraisService;

import java.util.List;

@RestController
@RequestMapping(path = "/API/V1/TypeFrais")
@CrossOrigin("*")
public class TypeFraisAPI {

    private final TypeFraisService typeFraisS;

    @Autowired
    public TypeFraisAPI(TypeFraisService typeFraisS){
        this.typeFraisS=typeFraisS;
    }

    @PostMapping(path = "/create",produces = { "application/json"})
    public TypeFraisDTO createTypeFrais(@RequestBody TypeFraisDTO typeFraisDTO){
        return typeFraisS.ajouter(typeFraisDTO);
    }

    @PutMapping(path = "/edit/{id}",produces = { "application/json"})
    public TypeFraisDTO editTypeFrais(@PathVariable("id")long id,@RequestBody TypeFraisDTO typeFraisDTO){
        typeFraisDTO.setId(id);
        return this.typeFraisS.mettreAJour(typeFraisDTO);
    }
    @DeleteMapping(path = "/delete/{id}")
    public void deleteTypeFrais(@PathVariable("id")long id){
        typeFraisS.supprimer(id);
    }

    @GetMapping(path = "/find/{id}",produces = { "application/json"})
    public TypeFraisDTO findTypeFrais(@PathVariable("id")long id){
        return typeFraisS.rechercher(id);
    }

    @GetMapping(path = "/displayAll",produces = { "application/json"})
    public List<TypeFraisDTO> displayAll(){
        return typeFraisS.afficherTout();
    }
    @GetMapping(path = "/displayAll/{keyword}",produces = { "application/json"})
    public List<TypeFraisDTO> displayAll(@PathVariable("keyword")String keyword){
        return typeFraisS.afficherToutBy(keyword);
    }
}
