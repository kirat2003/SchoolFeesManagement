package org.togo.rikCorpSolution.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.togo.rikCorpSolution.dtos.AdresseDTO;
import org.togo.rikCorpSolution.entities.Adresse;

@Service
public class AdresseMapperImpl {

    public static AdresseDTO fromAdresse(Adresse adresse){
        AdresseDTO adresseDTO = new AdresseDTO();
        BeanUtils.copyProperties(adresse,adresseDTO);
        return adresseDTO;
    }

    public static Adresse fromAdresseDTO(AdresseDTO adresseDTO){
        Adresse adresse = new Adresse();
        BeanUtils.copyProperties(adresseDTO,adresse);
        return adresse;
    }
}
