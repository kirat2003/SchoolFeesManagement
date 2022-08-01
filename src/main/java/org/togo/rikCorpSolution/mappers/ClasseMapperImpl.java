package org.togo.rikCorpSolution.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.togo.rikCorpSolution.dtos.ClasseDTO;
import org.togo.rikCorpSolution.dtos.FraisDTO;
import org.togo.rikCorpSolution.entities.Classe;

@Service
public class ClasseMapperImpl {

    public static ClasseDTO fromClasse(Classe classe){
        ClasseDTO classeDTO = new ClasseDTO();
        BeanUtils.copyProperties(classe,classeDTO);
        return classeDTO;
    }
    public static Classe fromClasseDTO(ClasseDTO classeDTO){
        Classe classe = new Classe();
        BeanUtils.copyProperties(classeDTO,classe);
        return classe;
    }
}
