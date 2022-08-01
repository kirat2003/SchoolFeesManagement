package org.togo.rikCorpSolution.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.togo.rikCorpSolution.dtos.InscriptionDTO;
import org.togo.rikCorpSolution.entities.Inscription;

@Service
public class InscriptionMapperImpl {

    public static InscriptionDTO fromInscription(Inscription inscription){
        InscriptionDTO inscriptionDTO = new InscriptionDTO();
        BeanUtils.copyProperties(inscription,inscriptionDTO);
        inscriptionDTO.setAnneeDTO(AnneeMapperImpl.fromAnnee(inscription.getAnnee()));
        inscriptionDTO.setClasseDTO(ClasseMapperImpl.fromClasse(inscription.getClasse()));
        inscriptionDTO.setEleveDTO(EleveMapperImpl.fromEleve(inscription.getEleve()));
        return inscriptionDTO;
    }
    public static Inscription fromInscriptionDTO(InscriptionDTO inscriptionDTO){
        Inscription inscription = new Inscription();
        BeanUtils.copyProperties(inscriptionDTO,inscription);
        inscription.setAnnee(AnneeMapperImpl.fromAnneeDTO(inscriptionDTO.getAnneeDTO()));
        inscription.setClasse(ClasseMapperImpl.fromClasseDTO(inscriptionDTO.getClasseDTO()));
        inscription.setEleve(EleveMapperImpl.fromEleveDTO(inscriptionDTO.getEleveDTO()));
        return inscription;
    }
}
