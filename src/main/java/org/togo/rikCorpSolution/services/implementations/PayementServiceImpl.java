package org.togo.rikCorpSolution.services.implementations;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.togo.rikCorpSolution.dtos.PayementDTO;
import org.togo.rikCorpSolution.entities.Payement;
import org.togo.rikCorpSolution.mappers.FraisMapperImpl;
import org.togo.rikCorpSolution.mappers.InscriptionMapperImpl;
import org.togo.rikCorpSolution.mappers.PayementMapperImpl;
import org.togo.rikCorpSolution.repositories.FraisRepository;
import org.togo.rikCorpSolution.repositories.InscriptionRepository;
import org.togo.rikCorpSolution.repositories.PayementRepository;
import org.togo.rikCorpSolution.services.PayementService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
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
        Double sommeTotalVerse=payementRep.sommeVerse(idInscription,idFrais);
        Payement payement = new Payement();
        payement.setDateDuPayement(dateDuPayement);
        payement.setMontantVerse(montantVerse);
        payement.setNomPayeur(nomPayeur);
        payement.setInscription(inscriptionRep.findById(idInscription).get());
        payement.setFrais(fraisRep.findById(idFrais).get());
        if(sommeTotalVerse == null){
            payement.setResteApayer(fraisRep.findById(idFrais).get().getMontantFrais()-(0+montantVerse));
        }else {
            payement.setResteApayer(fraisRep.findById(idFrais).get().getMontantFrais()-(sommeTotalVerse+montantVerse));
        }
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
        payementDTO.setResteApayer(fraisRep.findById(idFrais).get().getMontantFrais()-payementRep.sommeVerse(idInscription,idFrais));
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

    @Override
    public Double sommeVerse(long idPayement, long idFrais) {
        return payementRep.sommeVerse(idPayement,idFrais);
    }
    @Override
    public String exportReport(long idPayement) throws FileNotFoundException, JRException {
        String path="C:\\Users\\Utilisateur\\Desktop\\argon-dashboard-angular-master\\src\\assets\\report";
        List<Payement> payement = payementRep.getPayementById(idPayement);
        File file = ResourceUtils.getFile("classpath:reports\\payement_review.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        //JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(payement);
        Payement p = payement.get(0);
        System.out.println(p.getId());
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("datePayement",p.getDateDuPayement());
        parameters.put("annee.libelle",p.getInscription().getAnnee().getLibelle());
        parameters.put("id",p.getId());
        parameters.put("nom",p.getInscription().getEleve().getNom());
        parameters.put("prenom",p.getInscription().getEleve().getPrenom());
        parameters.put("classe",p.getInscription().getClasse().getDesignation());
        parameters.put("somme",p.getMontantVerse()+" FCFA");
        parameters.put("motif",p.getFrais().getDesignation());
        parameters.put("reste à payer",p.getResteApayer()+" FCFA");
        parameters.put("sommeApayer",p.getFrais().getMontantFrais()+" FCFA");
        //parameters.put("createdBy","BAGNA Tarik");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        //Create for PDF
        JasperExportManager.exportReportToPdfFile(jasperPrint, path +"\\payement"+idPayement+".pdf");
        return "assets/report"+"/payement"+idPayement+".pdf";

    }
    @Override
    public double selectAmoutAllFromPayementToday(Date date){
        double montant = 0;
        List<Payement> payements = payementRep.findAll();
        List<Payement> todaysPayements = new ArrayList<>();
        payements.forEach(payement -> {
            if(payement.getDateDuPayement().getDay()==date.getDay()&&payement.getDateDuPayement().getMonth()==date.getMonth()&&payement.getDateDuPayement().getYear()==date.getYear()){
                todaysPayements.add(payement);
            }
        });
        for (Payement todaysPayement : todaysPayements) {
            montant = montant + todaysPayement.getMontantVerse();
        }
        return montant;
    }
}
