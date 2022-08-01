package org.togo.rikCorpSolution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.togo.rikCorpSolution.dtos.FraisDTO;
import org.togo.rikCorpSolution.services.FraisService;

import java.util.List;

@RestController
@RequestMapping(path = "/API/V1/Frais")
@CrossOrigin("*")
public class FraisAPI {

    private final FraisService fraisS;

    @Autowired
    public FraisAPI(FraisService fraisS){
        this.fraisS=fraisS;
    }

    @PostMapping(path = "/create")
    public FraisDTO createFrais(@RequestParam(name = "montantFrais") double montantFrais,@RequestParam(name = "designation") String designation,@RequestParam(name = "idClasse") long idClasse,@RequestParam(name = "idTypeFrais") long idTypeFrais){
        return fraisS.ajouter(montantFrais, designation, idClasse, idTypeFrais);
    }

    @PutMapping(path = "/edit/{id}")
    public FraisDTO editFrais(@PathVariable("id")long id,@RequestParam(name = "montantFrais") double montantFrais,@RequestParam(name = "designation") String designation,@RequestParam(name = "idClasse") long idClasse,@RequestParam(name = "idTypeFrais") long idTypeFrais){
        return fraisS.mettreAJour(new FraisDTO(id,montantFrais,designation),idTypeFrais,idClasse);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteFrais(@PathVariable("id")long id){
        fraisS.supprimer(id);
    }

    @GetMapping(path = "/find/{id}")
    public FraisDTO findFrais(@PathVariable("id")long id){
        return fraisS.rechercher(id);
    }

    @GetMapping(path = "/displayAll")
    public List<FraisDTO> displayAll(){
        return fraisS.afficherTout();
    }

    @GetMapping(path = "/displayAllByDesignationFrais")
    public List<FraisDTO> displayAllByDesignationFrais(@RequestParam(name = "desFrais")String kw){
        return fraisS.searchByDesignFrais(kw);
    }
    @GetMapping(path = "/displayAllByDesignationClasse")
    public List<FraisDTO> displayAllByDesignationClasse(@RequestParam(name = "desClasse")String kw){
        return fraisS.searchByDesignClass(kw);
    }
    @GetMapping(path = "/displayFraisByClass/{id}")
    public List<FraisDTO> displayFraisByClass(@PathVariable("id") long id){
        return fraisS.displayFraisByClass(id);
    }
    @GetMapping(path = "/displayAllByLibType")
    public List<FraisDTO> displayAllByLibType(@RequestParam(name = "libTypeFrais")String kw){
        return fraisS.searchBylibTypeFrais(kw);
    }
}
