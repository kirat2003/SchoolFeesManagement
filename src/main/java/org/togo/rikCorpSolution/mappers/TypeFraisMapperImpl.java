package org.togo.rikCorpSolution.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.togo.rikCorpSolution.dtos.TypeFraisDTO;
import org.togo.rikCorpSolution.entities.TypeFrais;

@Service
public class TypeFraisMapperImpl {

    public static TypeFraisDTO fromTypeFrais(TypeFrais typeFrais){
        TypeFraisDTO typeFraisDTO = new TypeFraisDTO();
        BeanUtils.copyProperties(typeFrais,typeFraisDTO);
        return typeFraisDTO;
    }
    public static TypeFrais fromTypeFraisDTO(TypeFraisDTO typeFraisDTO){
        TypeFrais typeFrais = new TypeFrais();
        BeanUtils.copyProperties(typeFraisDTO,typeFrais);
        return typeFrais;
    }
}
