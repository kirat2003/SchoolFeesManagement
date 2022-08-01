package org.togo.rikCorpSolution.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.togo.rikCorpSolution.dtos.EleveDTO;
import org.togo.rikCorpSolution.entities.Eleve;
import org.togo.rikCorpSolution.enums.Genre;
import org.togo.rikCorpSolution.mappers.AdresseMapperImpl;
import org.togo.rikCorpSolution.mappers.EleveMapperImpl;
import org.togo.rikCorpSolution.repositories.AdresseRepository;
import org.togo.rikCorpSolution.repositories.EleveRepository;
import org.togo.rikCorpSolution.services.EleveService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EleveServiceImpl implements EleveService {

    private final EleveRepository eleveRep;
    private final AdresseRepository adresseRep;

    @Autowired
    public EleveServiceImpl(EleveRepository eleveRep,AdresseRepository adresseRep){
        this.adresseRep=adresseRep;
        this.eleveRep=eleveRep;
    }
    @Override
    public EleveDTO ajouter(String nom, String prenom, String email, String telephone, Date dateDeNaissance, String lieuDeNaissance, Genre genre, String nomParentTuteur, String fonctionParentTuteur, long idAdresse) {
        Eleve eleve = new Eleve();
        eleve.setAdresse(adresseRep.findById(idAdresse).get());
        eleve.setEmail(email);
        eleve.setGenre(genre);
        eleve.setDateDeNaissance(dateDeNaissance);
        eleve.setNom(nom);
        eleve.setPrenom(prenom);
        eleve.setTelephone(telephone);
        eleve.setLieuDeNaissance(lieuDeNaissance);
        eleve.setNomParentTuteur(nomParentTuteur);
        eleve.setFonctionParentTuteur(fonctionParentTuteur);
        return EleveMapperImpl.fromEleve(eleveRep.save(eleve));
    }

    @Override
    public void supprimer(long id) {
        eleveRep.deleteById(id);
    }

    @Override
    public EleveDTO mettreAJour(EleveDTO eleveDTO,long idAdresse) {
        eleveDTO.setAdresseDTO(AdresseMapperImpl.fromAdresse(adresseRep.findById(idAdresse).get()));
        return EleveMapperImpl.fromEleve(eleveRep.save(EleveMapperImpl.fromEleveDTO(eleveDTO)));
    }

    @Override
    public EleveDTO rechercher(long id) {
        return EleveMapperImpl.fromEleve(eleveRep.findById(id).get());
    }

    @Override
    public List<EleveDTO> afficherTout() {
        return eleveRep.displayAllStudentOrderedByNameAndSurname().stream().map(eleve -> EleveMapperImpl.fromEleve(eleve)).collect(Collectors.toList());
    }

    @Override
    public List<EleveDTO> displayAllByName(String name) {
        return eleveRep.displayAllByName(name).stream().map(eleve -> EleveMapperImpl.fromEleve(eleve)).collect(Collectors.toList());
    }

    @Override
    public List<EleveDTO> displayAllByPrenom(String prenom) {
        return eleveRep.displayAllByPrenom(prenom).stream().map(eleve -> EleveMapperImpl.fromEleve(eleve)).collect(Collectors.toList());
    }
}
