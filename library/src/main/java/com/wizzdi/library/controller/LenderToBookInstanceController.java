package com.wizzdi.library.controller;


import com.wizzdi.library.model.dto.LenderToBookInstanceDTO;
import com.wizzdi.library.model.entity.LenderToBookInstance;
import com.wizzdi.library.service.LenderToBookInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lender-to-book-instances")
public class LenderToBookInstanceController {

    @Autowired
    private LenderToBookInstanceService lenderToBookInstanceService;

    // Get all LenderToBookInstances
    @GetMapping
    public List<LenderToBookInstance> getAllLenderToBookInstances() {
        return lenderToBookInstanceService.getAllLenderToBookInstances();
    }

    // Create a new LenderToBookInstance
    @PostMapping
    public ResponseEntity<LenderToBookInstance> createLenderToBookInstance(@RequestBody LenderToBookInstanceDTO lenderToBookInstanceDTO) {
        LenderToBookInstance createdLenderToBookInstance = lenderToBookInstanceService.createLenderToBookInstance(lenderToBookInstanceDTO);
        return ResponseEntity.ok(createdLenderToBookInstance);
    }

    // Get a LenderToBookInstance by ID
    @GetMapping("/{id}")
    public ResponseEntity<LenderToBookInstance> getLenderToBookInstanceById(@PathVariable String id) {
        LenderToBookInstance lenderToBookInstance = lenderToBookInstanceService.getLenderToBookInstanceById(id);
        return ResponseEntity.ok(lenderToBookInstance);
    }

    // Update a LenderToBookInstance
    @PutMapping("/{id}")
    public ResponseEntity<LenderToBookInstance> updateLenderToBookInstance(@PathVariable String id, @RequestBody LenderToBookInstanceDTO lenderToBookInstanceDTO) {
        LenderToBookInstance updatedLenderToBookInstance = lenderToBookInstanceService.updateLenderToBookInstance(id, lenderToBookInstanceDTO);
        return ResponseEntity.ok(updatedLenderToBookInstance);
    }

    // Delete a LenderToBookInstance
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLenderToBookInstance(@PathVariable String id) {
        lenderToBookInstanceService.deleteLenderToBookInstance(id);

        return ResponseEntity.noContent().build();
    }
}