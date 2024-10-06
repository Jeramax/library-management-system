package com.wizzdi.library.service;


import com.wizzdi.library.model.dto.LenderToBookInstanceDTO;
import com.wizzdi.library.model.entity.LenderToBookInstance;
import com.wizzdi.library.repository.LenderToBookInstanceRepository;
import com.wizzdi.library.repository.BookInstanceRepository;
import com.wizzdi.library.repository.LenderRepository;
import com.wizzdi.library.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LenderToBookInstanceService {

    @Autowired
    private LenderToBookInstanceRepository lenderToBookInstanceRepository;

    @Autowired
    private LenderRepository lenderRepository;

    @Autowired
    private BookInstanceRepository bookInstanceRepository;

    // Fetch all LenderToBookInstances
    public List<LenderToBookInstance> getAllLenderToBookInstances() {
        return lenderToBookInstanceRepository.findAll();
    }

    // Create a new LenderToBookInstance
    public LenderToBookInstance createLenderToBookInstance(LenderToBookInstanceDTO lenderToBookInstanceDTO) {
        LenderToBookInstance lenderToBookInstance = new LenderToBookInstance();

        lenderToBookInstance.setLender(lenderRepository.findById(lenderToBookInstanceDTO.getLenderId())
                .orElseThrow(() -> new ResourceNotFoundException("Lender not found")));
        lenderToBookInstance.setBookInstance(bookInstanceRepository.findById(lenderToBookInstanceDTO.getBookInstanceId())
                .orElseThrow(() -> new ResourceNotFoundException("Book Instance not found")));

        lenderToBookInstance.setLent(lenderToBookInstanceDTO.getLent());
        lenderToBookInstance.setDueBack(lenderToBookInstanceDTO.getDueBack());
        lenderToBookInstance.setReturned(lenderToBookInstanceDTO.getReturned());

        return lenderToBookInstanceRepository.save(lenderToBookInstance);
    }

    // Get a LenderToBookInstance by ID
    public LenderToBookInstance getLenderToBookInstanceById(String id) {
        return lenderToBookInstanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LenderToBookInstance not found"));
    }

    // Update a LenderToBookInstance
    public LenderToBookInstance updateLenderToBookInstance(String id, LenderToBookInstanceDTO lenderToBookInstanceDTO) {
        LenderToBookInstance lenderToBookInstance = getLenderToBookInstanceById(id);
        lenderToBookInstance.setLent(lenderToBookInstanceDTO.getLent());
        lenderToBookInstance.setDueBack(lenderToBookInstanceDTO.getDueBack());
        lenderToBookInstance.setReturned(lenderToBookInstanceDTO.getReturned());

        return lenderToBookInstanceRepository.save(lenderToBookInstance);
    }

    // Delete a LenderToBookInstance
    public void deleteLenderToBookInstance(String id) {
        LenderToBookInstance lenderToBookInstance = getLenderToBookInstanceById(id);
        lenderToBookInstance.setSoftDelete(true);
        lenderToBookInstanceRepository.save(lenderToBookInstance);
    }
}