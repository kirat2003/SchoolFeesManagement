package org.togo.rikCorpSolution.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.togo.rikCorpSolution.dtos.PayementDTO;
import org.togo.rikCorpSolution.entities.Payement;

@Service
public class PayementMapperImpl {

    public static PayementDTO fromPayement(Payement payement){
        PayementDTO payementDTO = new PayementDTO();
        BeanUtils.copyProperties(payement,payementDTO);
        payementDTO.setInscriptionDTO(InscriptionMapperImpl.fromInscription(payement.getInscription()));
        payementDTO.setFraisDTO(FraisMapperImpl.fromFrais(payement.getFrais()));
        return payementDTO;
    }

    public static Payement fromPayementDTO(PayementDTO payementDTO){
        Payement payement = new Payement();
        BeanUtils.copyProperties(payementDTO,payement);
        payement.setInscription(InscriptionMapperImpl.fromInscriptionDTO(payementDTO.getInscriptionDTO()));
        payement.setFrais(FraisMapperImpl.fromFraiDTO(payementDTO.getFraisDTO()));
        return payement;
    }
}
