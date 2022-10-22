package org.togo.rikCorpSolution.services.implementations;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.togo.rikCorpSolution.dtos.EleveDTO;
import org.togo.rikCorpSolution.dtos.InscriptionDTO;
import org.togo.rikCorpSolution.entities.Inscription;
import org.togo.rikCorpSolution.enums.Genre;
import org.togo.rikCorpSolution.mappers.*;
import org.togo.rikCorpSolution.repositories.*;
import org.togo.rikCorpSolution.services.InscriptionService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
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
        System.out.println(inscriptionRep.save(InscriptionMapperImpl.fromInscriptionDTO(inscriptionDTO)).getIsInscrit());
        return InscriptionMapperImpl.fromInscription(inscriptionRep.save(InscriptionMapperImpl.fromInscriptionDTO(inscriptionDTO)));
    }

    @Override
    public InscriptionDTO rechercher(long id) {
        System.out.println(inscriptionRep.findById(id).get().getEleve().getConcat());
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
    public List<InscriptionDTO> getStudentByClasseId(long idClasse) {
        return inscriptionRep.getStudentByClasseId(idClasse).stream().map(inscription -> InscriptionMapperImpl.fromInscription(inscription)).collect(Collectors.toList());
    }
    @Override
    public List<InscriptionDTO> getStudentByClasseIdAndTrueOrFalse(long idClasse) {
        return inscriptionRep.getStudentByClasseIdAndTrueOrFalse(idClasse).stream().map(inscription -> InscriptionMapperImpl.fromInscription(inscription)).collect(Collectors.toList());
    }

    @Override
    public InscriptionDTO setTrue(long id) {
        Inscription i = inscriptionRep.findById(id).get();
        i.setIsInscrit(true);
        return InscriptionMapperImpl.fromInscription(inscriptionRep.save(i));
    }

    @Override
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path="C:\\Users\\Utilisateur\\Desktop\\reports";
        List<Inscription> inscriptions = inscriptionRep.findAll();
        File file = ResourceUtils.getFile("classpath:reports\\ListeEleve.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(inscriptions);

        Map<String,Object> parameters = new HashMap<>();
        parameters.put("createdBy","BAGNA Tarik");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        //Create for HTML
        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path +"\\ListeEleve.html");
        }
        //Create for PDF
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, path +"\\ListeEleve.pdf");
        }
        return "Report generated in path"+path;
    }
    @Override
    public String exportListOfStudentByIdClassEquals(long idClasse) throws JRException, FileNotFoundException {
        String path="C:\\Users\\Utilisateur\\Desktop\\argon-dashboard-angular-master\\src\\assets\\report";
        List<Inscription> inscriptions = inscriptionRep.getStudentByClasseId(idClasse);
        List<Inscription> inscriptionsWithConcatAndAdresseTotal = new ArrayList<>();
        inscriptions.forEach(inscription -> {
            if(inscription.getIsInscrit()){
                inscription.setStatut("Inscrit");
            }else {
                inscription.setStatut("En attente");
            }
            inscription.getEleve().setConcat(inscription.getEleve().getNom()+' '+inscription.getEleve().getPrenom());
            inscription.getEleve().setAdresseTotal(inscription.getEleve().getAdresse().getPays()+", "+inscription.getEleve().getAdresse().getVille()+", "+inscription.getEleve().getAdresse().getQuartier());
            inscriptionsWithConcatAndAdresseTotal.add(inscription);
        });
        File file = ResourceUtils.getFile("classpath:reports\\listeDesEleveParClasse.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(inscriptionsWithConcatAndAdresseTotal);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, path +"\\listeDesElevesDeLaClasseDontLeIdEst"+idClasse+".pdf");
        return "assets/report"+"/listeDesElevesDeLaClasseDontLeIdEst"+idClasse+".pdf";
    }
    @Override
    public int nbreGarcons(){
        return inscriptionRep.nbreDeGarconsFilles(Genre.MASCULIN);
    }
    @Override
    public int nbreFilles(){
        return inscriptionRep.nbreDeGarconsFilles(Genre.FEMININ);
    }
    @Override
    public int recupNbInscrAuj(Date date){
        List<Inscription> inscriptions = inscriptionRep.displayAllByDateInscriptionAndTrue();
        List<Inscription> inscriptions1 = new ArrayList<>();
        inscriptions.forEach(inscription -> {
            if(inscription.getDateInscription().getDay()==date.getDay()&&inscription.getDateInscription().getMonth()==date.getMonth()&&inscription.getDateInscription().getYear()==date.getYear()){
                inscriptions1.add(inscription);
            }
        });
        return inscriptions1.size();
    }
}
