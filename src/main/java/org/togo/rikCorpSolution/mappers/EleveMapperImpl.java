package org.togo.rikCorpSolution.mappers;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.togo.rikCorpSolution.dtos.EleveDTO;
import org.togo.rikCorpSolution.entities.Eleve;

@Service
public class EleveMapperImpl {

    public static EleveDTO fromEleve(Eleve eleve){
        EleveDTO eleveDTO = new EleveDTO();
        BeanUtils.copyProperties(eleve,eleveDTO);
        eleveDTO.setAdresseDTO(AdresseMapperImpl.fromAdresse(eleve.getAdresse()));
        return eleveDTO;
    }

    public static Eleve fromEleveDTO(EleveDTO eleveDTO){
        Eleve eleve = new Eleve();
        BeanUtils.copyProperties(eleveDTO,eleve);
        eleve.setAdresse(AdresseMapperImpl.fromAdresseDTO(eleveDTO.getAdresseDTO()));
        return eleve;
    }
}
