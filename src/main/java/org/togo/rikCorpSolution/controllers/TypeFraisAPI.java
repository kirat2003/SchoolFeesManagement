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

    @PostMapping(path = "/create")
    public TypeFraisDTO createTypeFrais(@RequestBody TypeFraisDTO typeFraisDTO){
        return typeFraisS.ajouter(typeFraisDTO);
    }

    @PutMapping(path = "/edit/{id}")
    public TypeFraisDTO editTypeFrais(@PathVariable("id")long id,@RequestBody TypeFraisDTO typeFraisDTO){
        typeFraisDTO.setId(id);
        return this.typeFraisS.mettreAJour(typeFraisDTO);
    }
    @DeleteMapping(path = "/delete/{id}")
    public void deleteTypeFrais(@PathVariable("id")long id){
        typeFraisS.supprimer(id);
    }

    @GetMapping(path = "/find/{id}")
    public TypeFraisDTO findTypeFrais(@PathVariable("id")long id){
        return typeFraisS.rechercher(id);
    }

    @GetMapping(path = "/displayAll")
    public List<TypeFraisDTO> displayAll(){
        return typeFraisS.afficherTout();
    }
    @GetMapping(path = "/displayAll/{keyword}")
    public List<TypeFraisDTO> displayAll(@PathVariable("keyword")String keyword){
        return typeFraisS.afficherToutBy(keyword);
    }
}
