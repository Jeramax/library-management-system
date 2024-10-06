package com.wizzdi.library.service;

import com.wizzdi.library.model.dto.LenderDTO;
import com.wizzdi.library.model.entity.Lender;
import com.wizzdi.library.model.entity.Person;
import com.wizzdi.library.repository.LenderRepository;
import com.wizzdi.library.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LenderService {

    @Autowired
    private LenderRepository lenderRepository;

    public Lender createLender(LenderDTO lenderDTO) {
        Lender lender = new Lender();
        lender.setId(UUID.randomUUID().toString());
        lender.setName("lender1");
        lender.setEmail(lenderDTO.getEmail());
        lender.setAddress(lenderDTO.getAddress());
        lender.setPhoneNumber(lenderDTO.getPhoneNumber());
        lender.setGender(Person.Gender.valueOf(lenderDTO.getGender()));
        lender.setBirthDate(lenderDTO.getBirthDate());
        lender.setSocialSecurityNumber(lenderDTO.getSocialSecurityNumber());
        lender.setBlocked(lenderDTO.isBlocked());

        return lenderRepository.save(lender);
    }

    public List<Lender> getAllLenders() {
        return lenderRepository.findAll();
    }

    public Optional<Lender> getLenderById(String lenderId) {
        return lenderRepository.findById(lenderId);
    }

    // Update a Lender
    public Lender updateLender(String lenderId, LenderDTO lenderDetails) {
        Lender lender = lenderRepository.findById(lenderId)
                .orElseThrow(() -> new ResourceNotFoundException("Lender not found with id: " + lenderId));

        lender.setEmail(lenderDetails.getEmail());
        lender.setAddress(lenderDetails.getAddress());
        lender.setPhoneNumber(lenderDetails.getPhoneNumber());
        lender.setGender(Person.Gender.valueOf(lenderDetails.getGender()));
        lender.setBirthDate(lenderDetails.getBirthDate());
        lender.setSocialSecurityNumber(lenderDetails.getSocialSecurityNumber());
        lender.setBlocked(lenderDetails.isBlocked());
        return lenderRepository.save(lender);
    }

    // Delete a Lender
    public void deleteLender(String lenderId) {
        Lender lender = lenderRepository.findById(lenderId)
                .orElseThrow(() -> new ResourceNotFoundException("Lender not found with id: " + lenderId));
        lender.setSoftDelete(true);
        lenderRepository.save(lender);
    }


}
