package org.togo.rikCorpSolution.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.togo.rikCorpSolution.dtos.FraisDTO;
import org.togo.rikCorpSolution.entities.Frais;

@Service
public class FraisMapperImpl {

    public static FraisDTO fromFrais(Frais frais){
        FraisDTO fraisDTO = new FraisDTO();
        BeanUtils.copyProperties(frais,fraisDTO);
        fraisDTO.setTypeFraisDTO(TypeFraisMapperImpl.fromTypeFrais(frais.getTypeFrais()));
        fraisDTO.setClasseDTO(ClasseMapperImpl.fromClasse(frais.getClasse()));
        return fraisDTO;
    }

    public static Frais fromFraiDTO(FraisDTO fraisDTO){
        Frais frais = new Frais();
        BeanUtils.copyProperties(fraisDTO,frais);
        frais.setTypeFrais(TypeFraisMapperImpl.fromTypeFraisDTO(fraisDTO.getTypeFraisDTO()));
        frais.setClasse(ClasseMapperImpl.fromClasseDTO(fraisDTO.getClasseDTO()));
        return frais;
    }
}
