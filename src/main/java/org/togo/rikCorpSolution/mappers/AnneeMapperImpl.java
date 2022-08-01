package org.togo.rikCorpSolution.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.togo.rikCorpSolution.dtos.AnneeDTO;
import org.togo.rikCorpSolution.entities.Annee;

@Service
public class AnneeMapperImpl {

    public static AnneeDTO fromAnnee(Annee annee){
        AnneeDTO anneeDTO = new AnneeDTO();
        BeanUtils.copyProperties(annee,anneeDTO);
        return anneeDTO;
    }

    public static Annee fromAnneeDTO(AnneeDTO anneeDTO){
        Annee annee = new Annee();
        BeanUtils.copyProperties(anneeDTO,annee);
        return annee;
    }
}
