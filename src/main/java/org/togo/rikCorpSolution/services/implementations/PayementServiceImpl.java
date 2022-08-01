package org.togo.rikCorpSolution.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.togo.rikCorpSolution.dtos.PayementDTO;
import org.togo.rikCorpSolution.entities.Payement;
import org.togo.rikCorpSolution.mappers.FraisMapperImpl;
import org.togo.rikCorpSolution.mappers.InscriptionMapperImpl;
import org.togo.rikCorpSolution.mappers.PayementMapperImpl;
import org.togo.rikCorpSolution.repositories.FraisRepository;
import org.togo.rikCorpSolution.repositories.InscriptionRepository;
import org.togo.rikCorpSolution.repositories.PayementRepository;
import org.togo.rikCorpSolution.services.PayementService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PayementServiceImpl implements PayementService {

    private final InscriptionRepository inscriptionRep;
    private final FraisRepository fraisRep;
    private final PayementRepository payementRep;

    @Autowired
    public PayementServiceImpl(InscriptionRepository inscriptionRep,PayementRepository payementRep,FraisRepository fraisRep){
        this.inscriptionRep=inscriptionRep;
        this.payementRep=payementRep;
        this.fraisRep=fraisRep;
    }

    @Override
    public PayementDTO ajouter(double montantVerse, Date dateDuPayement, String nomPayeur, long idInscription,long idFrais) {
        Payement payement = new Payement();
        payement.setDateDuPayement(dateDuPayement);
        payement.setMontantVerse(montantVerse);
        payement.setNomPayeur(nomPayeur);
        payement.setInscription(inscriptionRep.findById(idInscription).get());
        payement.setFrais(fraisRep.findById(idFrais).get());
        return PayementMapperImpl.fromPayement(payementRep.save(payement));
    }

    @Override
    public void supprimer(long id) {
        payementRep.deleteById(id);
    }

    @Override
    public PayementDTO mettreAJour(PayementDTO payementDTO,long idInscription,long idFrais) {
        payementDTO.setInscriptionDTO(InscriptionMapperImpl.fromInscription(inscriptionRep.findById(idInscription).get()));
        payementDTO.setFraisDTO(FraisMapperImpl.fromFrais(fraisRep.findById(idFrais).get()));
        return PayementMapperImpl.fromPayement(payementRep.save(PayementMapperImpl.fromPayementDTO(payementDTO)));
    }

    @Override
    public PayementDTO rechercher(long id) {
        return PayementMapperImpl.fromPayement(payementRep.findById(id).get());
    }

    @Override
    public List<PayementDTO> afficherTout() {
        return payementRep.displayAllPayementOrderedByDatePayement().stream().map(payement -> PayementMapperImpl.fromPayement(payement)).collect(Collectors.toList());
    }

    @Override
    public List<PayementDTO> searchByKwNom(String nom) {
        return payementRep.searchByKwNom(nom).stream().map(payement -> PayementMapperImpl.fromPayement(payement)).collect(Collectors.toList());
    }

    @Override
    public List<PayementDTO> searchByKwPrenom(String prenom) {
        return payementRep.searchByKwPrenom(prenom).stream().map(payement -> PayementMapperImpl.fromPayement(payement)).collect(Collectors.toList());
    }

    @Override
    public List<PayementDTO> getAllByIdInscription(long id) {
        return payementRep.getAllByIdInscription(id).stream().map(payement -> PayementMapperImpl.fromPayement(payement)).collect(Collectors.toList());
    }
}
