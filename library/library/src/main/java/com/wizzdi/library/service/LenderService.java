package com.wizzdi.library.service;

import com.wizzdi.library.model.dto.LenderDTO;
import com.wizzdi.library.model.entity.Lender;
import com.wizzdi.library.model.entity.Person;
import com.wizzdi.library.repository.LenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LenderService {

    @Autowired
    private LenderRepository lenderRepository;

    public Lender createLender(LenderDTO lenderDTO) {
        Lender lender = new Lender();
        lender.setEmail(lenderDTO.getEmail());
        lender.setAddress(lenderDTO.getAddress());
        lender.setPhoneNumber(lenderDTO.getPhoneNumber());
        lender.setGender(Person.Gender.valueOf(lenderDTO.getGender()));
        lender.setBirthDate(lenderDTO.getBirthDate());
        lender.setSocialSecurityNumber(lenderDTO.getSocialSecurityNumber());
        lender.setBlocked(lenderDTO.isBlocked());

        return lenderRepository.save(lender);
    }


}
