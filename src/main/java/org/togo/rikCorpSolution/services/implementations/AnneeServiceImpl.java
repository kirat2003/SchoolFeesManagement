package org.togo.rikCorpSolution.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.togo.rikCorpSolution.dtos.AnneeDTO;
import org.togo.rikCorpSolution.entities.Annee;
import org.togo.rikCorpSolution.exceptions.AnneeNotFoundException;
import org.togo.rikCorpSolution.mappers.AnneeMapperImpl;
import org.togo.rikCorpSolution.repositories.AnneeRepository;
import org.togo.rikCorpSolution.services.AnneeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnneeServiceImpl implements AnneeService {

    private final AnneeRepository anneeRep;

    @Autowired
    public AnneeServiceImpl(AnneeRepository anneeRep){
        this.anneeRep = anneeRep;
    }
    @Override
    public AnneeDTO ajouter(AnneeDTO anneeDTO) {
        Annee saveAnnee = AnneeMapperImpl.fromAnneeDTO(anneeDTO);
        saveAnnee.setAnneeEnCours(false);
        return AnneeMapperImpl.fromAnnee(anneeRep.save(saveAnnee));
    }

    @Override
    public void supprimer(long id) {
        anneeRep.deleteById(id);
    }

    @Override
    public AnneeDTO mettreAJour(AnneeDTO anneeDTO) {
        Annee anneeUpdated = AnneeMapperImpl.fromAnneeDTO(anneeDTO);
        return AnneeMapperImpl.fromAnnee(anneeRep.save(anneeUpdated));
    }

    @Override
    public AnneeDTO rechercher(long id) throws AnneeNotFoundException {
        if(anneeRep.findById(id).get() == null)
            throw new AnneeNotFoundException("Year not found");
        return AnneeMapperImpl.fromAnnee(anneeRep.findById(id).get());
    }

    @Override
    public List<AnneeDTO> afficherTout() {
        return anneeRep.displayAllOrderByLib().stream().map(annee -> AnneeMapperImpl.fromAnnee(annee)).collect(Collectors.toList());
    }

    @Override
    public List<AnneeDTO> afficherToutParRapportAKeyword(String libelle) {
        return anneeRep.displayAllByLib(libelle).stream().map(annee -> AnneeMapperImpl.fromAnnee(annee)).collect(Collectors.toList());
    }

    @Override
    public AnneeDTO getCurrentYear() {
        return AnneeMapperImpl.fromAnnee(anneeRep.selectCurrentYear());
    }

    @Override
    public AnneeDTO setItToTrue(long id) {
        Annee annee = anneeRep.findById(id).get();
        annee.setAnneeEnCours(true);
        List<Annee> annees = anneeRep.selectAllWithoutThisId(id);
        annees.forEach(annee1 -> {
            annee1.setAnneeEnCours(false);
        });
        anneeRep.saveAll(annees);
        return AnneeMapperImpl.fromAnnee(anneeRep.save(annee));
    }
}
