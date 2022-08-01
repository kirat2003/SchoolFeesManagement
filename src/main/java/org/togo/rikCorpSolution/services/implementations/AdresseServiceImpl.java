package org.togo.rikCorpSolution.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.togo.rikCorpSolution.dtos.AdresseDTO;
import org.togo.rikCorpSolution.entities.Adresse;
import org.togo.rikCorpSolution.exceptions.AdresseNotFoundException;
import org.togo.rikCorpSolution.mappers.AdresseMapperImpl;
import org.togo.rikCorpSolution.repositories.AdresseRepository;
import org.togo.rikCorpSolution.services.AdresseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdresseServiceImpl implements AdresseService {

    private final AdresseRepository adresseRep;

    @Autowired
    public AdresseServiceImpl(AdresseRepository adresseRep){
        this.adresseRep = adresseRep;
    }
    @Override
    public AdresseDTO ajouter(AdresseDTO adresseDTO) throws AdresseNotFoundException {

        if (adresseDTO.getPays()==null) throw new AdresseNotFoundException("Not Found");
        Adresse saveAdresse = AdresseMapperImpl.fromAdresseDTO(adresseDTO);
        return AdresseMapperImpl.fromAdresse(adresseRep.save(saveAdresse));
    }

    @Override
    public void supprimer(long id) {
        adresseRep.deleteById(id);
    }

    @Override
    public AdresseDTO mettreAJour(AdresseDTO adresseDTO) {
        Adresse updatedAdresse = AdresseMapperImpl.fromAdresseDTO(adresseDTO);
        return AdresseMapperImpl.fromAdresse(adresseRep.save(updatedAdresse));
    }

    @Override
    public AdresseDTO rechercher(long id) {

        return AdresseMapperImpl.fromAdresse(adresseRep.findById(id).get());
    }

    @Override
    public List<AdresseDTO> afficherTout() {
        return adresseRep.findAll().stream().map(adresse -> AdresseMapperImpl.fromAdresse(adresse)).collect(Collectors.toList());
    }

    @Override
    public List<AdresseDTO> afficherToutByKw(String keyword) {
        return adresseRep.displayBykw(keyword).stream().map(adresse -> AdresseMapperImpl.fromAdresse(adresse)).collect(Collectors.toList());
    }
}
