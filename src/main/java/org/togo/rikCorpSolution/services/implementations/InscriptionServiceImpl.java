package org.togo.rikCorpSolution.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.togo.rikCorpSolution.dtos.AnneeDTO;
import org.togo.rikCorpSolution.dtos.EleveDTO;
import org.togo.rikCorpSolution.dtos.InscriptionDTO;
import org.togo.rikCorpSolution.entities.Inscription;
import org.togo.rikCorpSolution.mappers.*;
import org.togo.rikCorpSolution.repositories.*;
import org.togo.rikCorpSolution.services.InscriptionService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InscriptionServiceImpl implements InscriptionService {

    private final InscriptionRepository inscriptionRep;
    private final ClasseRepository classeRep;
    private final AnneeRepository anneeRep;
    private final AdresseRepository adresseRep;
    private final EleveRepository eleveRep;

    @Autowired
    public InscriptionServiceImpl(InscriptionRepository inscriptionRep,ClasseRepository classeRep,AnneeRepository anneeRep,EleveRepository eleveRep,AdresseRepository adresseRep){
        this.inscriptionRep=inscriptionRep;
        this.anneeRep=anneeRep;
        this.classeRep=classeRep;
        this.eleveRep=eleveRep;
        this.adresseRep=adresseRep;
    }
    @Override
    public InscriptionDTO ajouter(Date dateInscription, long idClasse, EleveDTO eleveDTO,long idAdresse) {
        Inscription inscription = new Inscription();
        inscription.setDateInscription(dateInscription);
        inscription.setAnnee(anneeRep.selectCurrentYear());
        inscription.setClasse(classeRep.findById(idClasse).get());
        eleveDTO.setAdresseDTO(AdresseMapperImpl.fromAdresse(adresseRep.findById(idAdresse).get()));
        inscription.setEleve(EleveMapperImpl.fromEleveDTO(eleveDTO));
        inscription.setIsInscrit(false);
        long i = eleveRep.save(EleveMapperImpl.fromEleveDTO(eleveDTO)).getId();
        inscription.getEleve().setId(i);
        inscription.getClasse().setEffectifDeLaClasse(inscription.getClasse().getEffectifDeLaClasse()+1);
        classeRep.save(inscription.getClasse());
        return InscriptionMapperImpl.fromInscription(inscriptionRep.save(inscription));
    }

    @Override
    public void supprimer(long id) {
        Inscription inscription = inscriptionRep.findById(id).get();
        inscription.getClasse().setEffectifDeLaClasse(inscription.getClasse().getEffectifDeLaClasse()-1);
        classeRep.save(inscription.getClasse());
        inscriptionRep.save(inscription);
        inscriptionRep.deleteById(id);
    }

    @Override
    public InscriptionDTO mettreAJour(long id,long idClasse,long idAdresse,EleveDTO eleveDTO,boolean isInscrit) {

        InscriptionDTO inscriptionDTO = new InscriptionDTO();
        inscriptionDTO.setId(id);
        inscriptionDTO.setDateInscription(inscriptionRep.findById(id).get().getDateInscription());
        inscriptionDTO.setClasseDTO(ClasseMapperImpl.fromClasse(classeRep.findById(idClasse).get()));
        inscriptionDTO.setIsInscrit(isInscrit);
        inscriptionDTO.setAnneeDTO(AnneeMapperImpl.fromAnnee(anneeRep.selectCurrentYear()));
        eleveDTO.setAdresseDTO(AdresseMapperImpl.fromAdresse(adresseRep.findById(idAdresse).get()));
        inscriptionDTO.setEleveDTO(eleveDTO);
        eleveRep.save(EleveMapperImpl.fromEleveDTO(eleveDTO));
        return InscriptionMapperImpl.fromInscription(inscriptionRep.save(InscriptionMapperImpl.fromInscriptionDTO(inscriptionDTO)));
    }

    @Override
    public InscriptionDTO rechercher(long id) {
        return InscriptionMapperImpl.fromInscription(inscriptionRep.findById(id).get());
    }

    @Override
    public List<InscriptionDTO> afficherTout() {
        return inscriptionRep.displayAllByDateInscription().stream().map(inscription -> InscriptionMapperImpl.fromInscription(inscription)).collect(Collectors.toList());
    }

    @Override
    public List<InscriptionDTO> displayAllByName(String nom) {
        return inscriptionRep.displayAllByName(nom).stream().map(inscription -> InscriptionMapperImpl.fromInscription(inscription)).collect(Collectors.toList());
    }

    @Override
    public List<InscriptionDTO> displayAllByClasse(String classe) {
        return inscriptionRep.displayAllByClasse(classe).stream().map(inscription -> InscriptionMapperImpl.fromInscription(inscription)).collect(Collectors.toList());
    }

    @Override
    public List<InscriptionDTO> displayAllByPrenom(String prenom) {
        return inscriptionRep.displayAllByPrenom(prenom).stream().map(inscription -> InscriptionMapperImpl.fromInscription(inscription)).collect(Collectors.toList());
    }

    @Override
    public List<InscriptionDTO> afficherToutEtVrai() {
        return inscriptionRep.displayAllByDateInscriptionAndTrue().stream().map(inscription -> InscriptionMapperImpl.fromInscription(inscription)).collect(Collectors.toList());
    }

    @Override
    public InscriptionDTO setTrue(long id) {
        Inscription i = inscriptionRep.findById(id).get();
        i.setIsInscrit(true);
        return InscriptionMapperImpl.fromInscription(inscriptionRep.save(i));
    }
}
