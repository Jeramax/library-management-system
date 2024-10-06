package com.wizzdi.library.controller;

import com.wizzdi.library.model.dto.LenderDTO;
import com.wizzdi.library.model.entity.Lender;
import com.wizzdi.library.service.LenderService;
import com.wizzdi.library.util.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lenders")
public class LenderController {

    @Autowired
    private LenderService lenderService;

    @PostMapping
    public ResponseEntity<Lender> createLender(@RequestBody LenderDTO lenderDTO) {
        Lender lender = lenderService.createLender(lenderDTO);
        return new ResponseEntity<>(lender, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Lender> getAllLenders() {
        return lenderService.getAllLenders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lender> getLenderById(@PathVariable(value = "id") String lenderId) {
        Lender lender = lenderService.getLenderById(lenderId)
                .orElseThrow(() -> new ResourceNotFoundException("Lender not found with id: " + lenderId));
        return ResponseEntity.ok().body(lender);
    }

    // Update a Lender
    @PutMapping("/{id}")
    public ResponseEntity<Lender> updateLender(@PathVariable(value = "id") String lenderId,
                                               @Valid @RequestBody LenderDTO lenderDetails) {
        Lender updatedLender = lenderService.updateLender(lenderId, lenderDetails);
        return ResponseEntity.ok(updatedLender);
    }

    // Delete a Lender
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLender(@PathVariable(value = "id") String lenderId) {
        lenderService.deleteLender(lenderId);
        return ResponseEntity.ok().build();
    }
}