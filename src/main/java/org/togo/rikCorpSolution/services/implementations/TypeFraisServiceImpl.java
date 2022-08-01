package org.togo.rikCorpSolution.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.togo.rikCorpSolution.dtos.TypeFraisDTO;
import org.togo.rikCorpSolution.entities.TypeFrais;
import org.togo.rikCorpSolution.mappers.TypeFraisMapperImpl;
import org.togo.rikCorpSolution.repositories.TypeFraisRepository;
import org.togo.rikCorpSolution.services.TypeFraisService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TypeFraisServiceImpl implements TypeFraisService {
    private final TypeFraisRepository typeFraisRep;
    @Autowired
    public TypeFraisServiceImpl(TypeFraisRepository typeFraisRep){
        this.typeFraisRep=typeFraisRep;
    }
    @Override
    public TypeFraisDTO ajouter(TypeFraisDTO typeFraisDTO) {
        return TypeFraisMapperImpl.fromTypeFrais(typeFraisRep.save(TypeFraisMapperImpl.fromTypeFraisDTO(typeFraisDTO)));
    }

    @Override
    public void supprimer(long id) {
        typeFraisRep.deleteById(id);
    }

    @Override
    public TypeFraisDTO mettreAJour(TypeFraisDTO typeFraisDTO) {
        return TypeFraisMapperImpl.fromTypeFrais(typeFraisRep.save(TypeFraisMapperImpl.fromTypeFraisDTO(typeFraisDTO)));
    }

    @Override
    public TypeFraisDTO rechercher(long id) {
        TypeFrais typeFrais = new TypeFrais();
        typeFrais=typeFraisRep.findById(id).orElse(null);
        return TypeFraisMapperImpl.fromTypeFrais(typeFrais);
    }

    @Override
    public List<TypeFraisDTO> afficherTout() {
        return typeFraisRep.displayAllTypeFraisByLibelle().stream().map(typeFrais -> TypeFraisMapperImpl.fromTypeFrais(typeFrais)).collect(Collectors.toList());
    }

    @Override
    public List<TypeFraisDTO> afficherToutBy(String keyword) {
        return typeFraisRep.displayAllTypeFraisByLibelle(keyword).stream().map(typeFrais -> TypeFraisMapperImpl.fromTypeFrais(typeFrais)).collect(Collectors.toList());
    }
}
