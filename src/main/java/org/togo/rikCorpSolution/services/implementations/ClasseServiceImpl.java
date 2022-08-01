package org.togo.rikCorpSolution.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.togo.rikCorpSolution.dtos.ClasseDTO;
import org.togo.rikCorpSolution.dtos.FraisDTO;
import org.togo.rikCorpSolution.entities.Classe;
import org.togo.rikCorpSolution.entities.Frais;
import org.togo.rikCorpSolution.mappers.ClasseMapperImpl;
import org.togo.rikCorpSolution.mappers.FraisMapperImpl;
import org.togo.rikCorpSolution.repositories.ClasseRepository;
import org.togo.rikCorpSolution.repositories.FraisRepository;
import org.togo.rikCorpSolution.services.ClasseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClasseServiceImpl implements ClasseService {
    public final ClasseRepository classeRep;
    public final FraisRepository fraisRep;

    @Autowired
    public ClasseServiceImpl(ClasseRepository classeRep,FraisRepository fraisRep){
        this.classeRep=classeRep;
        this.fraisRep=fraisRep;
    }

    @Override
    public ClasseDTO ajouter(String designation,int nombreDePlaceDisponible) {
        Classe classe = new Classe();
        classe.setDesignation(designation);
        classe.setNombreDePlaceDisponible(nombreDePlaceDisponible);
        classe.setEffectifDeLaClasse(0);
        return ClasseMapperImpl.fromClasse(classeRep.save(classe));
    }

    @Override
    public void supprimer(long id) {
        classeRep.deleteById(id);
    }

    @Override
    public ClasseDTO mettreAJour(ClasseDTO classeDTO) {
        return ClasseMapperImpl.fromClasse(classeRep.save(ClasseMapperImpl.fromClasseDTO(classeDTO)));
    }

    @Override
    public ClasseDTO rechercher(long id) {
        return ClasseMapperImpl.fromClasse(classeRep.findById(id).get());
    }

    @Override
    public List<ClasseDTO> afficherTout() {
        return classeRep.findByLibelle().stream().map(classe -> ClasseMapperImpl.fromClasse(classe)).collect(Collectors.toList());
    }

    @Override
    public List<ClasseDTO> findByLibelle(String keyword) {
        return classeRep.findByLibelle2(keyword).stream().map(classe -> ClasseMapperImpl.fromClasse(classe)).collect(Collectors.toList());
    }
}
