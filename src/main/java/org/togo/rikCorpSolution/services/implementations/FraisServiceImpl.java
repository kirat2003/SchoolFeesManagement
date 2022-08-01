package org.togo.rikCorpSolution.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.togo.rikCorpSolution.dtos.FraisDTO;
import org.togo.rikCorpSolution.entities.Frais;
import org.togo.rikCorpSolution.mappers.ClasseMapperImpl;
import org.togo.rikCorpSolution.mappers.FraisMapperImpl;
import org.togo.rikCorpSolution.mappers.TypeFraisMapperImpl;
import org.togo.rikCorpSolution.repositories.ClasseRepository;
import org.togo.rikCorpSolution.repositories.FraisRepository;
import org.togo.rikCorpSolution.repositories.TypeFraisRepository;
import org.togo.rikCorpSolution.services.FraisService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FraisServiceImpl implements FraisService {

    private final ClasseRepository classeRep;
    private final TypeFraisRepository typeFraisRep;
    private final FraisRepository fraisRep;

    @Autowired
    public FraisServiceImpl(ClasseRepository classeRep,TypeFraisRepository typeFraisRep,FraisRepository fraisRep){
        this.fraisRep=fraisRep;
        this.typeFraisRep=typeFraisRep;
        this.classeRep=classeRep;
    }
    @Override
    public FraisDTO ajouter(double montantFrais,String designation,long idClasse,long idTypeFrais) {
        Frais frais = new Frais();
        frais.setTypeFrais(typeFraisRep.findById(idTypeFrais).get());
        frais.setClasse(classeRep.findById(idClasse).get());
        frais.setMontantFrais(montantFrais);
        frais.setDesignation(designation);
        return FraisMapperImpl.fromFrais(fraisRep.save(frais));
    }

    @Override
    public void supprimer(long id) {
        fraisRep.deleteById(id);
    }

    @Override
    public FraisDTO mettreAJour(FraisDTO fraisDTO,long idTypeFrais,long idClasse) {
        fraisDTO.setTypeFraisDTO(TypeFraisMapperImpl.fromTypeFrais(typeFraisRep.findById(idTypeFrais).get()));
        fraisDTO.setClasseDTO(ClasseMapperImpl.fromClasse(classeRep.findById(idClasse).get()));
        return FraisMapperImpl.fromFrais(fraisRep.save(FraisMapperImpl.fromFraiDTO(fraisDTO)));
    }

    @Override
    public FraisDTO rechercher(long id) {
        return FraisMapperImpl.fromFrais(fraisRep.findById(id).get());
    }

    @Override
    public List<FraisDTO> afficherTout() {
        return fraisRep.displayAllByDesignation().stream().map(frais -> FraisMapperImpl.fromFrais(frais)).collect(Collectors.toList());
    }

    @Override
    public List<FraisDTO> searchByDesignFrais(String designationFrais) {
        return fraisRep.searchByDesignFrais(designationFrais).stream().map(frais -> FraisMapperImpl.fromFrais(frais)).collect(Collectors.toList());
    }

    @Override
    public List<FraisDTO> searchByDesignClass(String designationClasse) {
        return fraisRep.searchByDesignClass(designationClasse).stream().map(frais -> FraisMapperImpl.fromFrais(frais)).collect(Collectors.toList());
    }

    @Override
    public List<FraisDTO> searchBylibTypeFrais(String libelleTypeFrais) {
        return fraisRep.searchBylibTypeFrais(libelleTypeFrais).stream().map(frais -> FraisMapperImpl.fromFrais(frais)).collect(Collectors.toList());
    }

    @Override
    public List<FraisDTO> displayFraisByClass(long id) {
        return fraisRep.displayFraisByClass(id).stream().map(frais -> FraisMapperImpl.fromFrais(frais)).collect(Collectors.toList());
    }
}
