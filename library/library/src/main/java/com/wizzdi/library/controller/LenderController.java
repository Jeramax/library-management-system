package com.wizzdi.library.controller;

import com.wizzdi.library.model.dto.LenderDTO;
import com.wizzdi.library.model.entity.Lender;
import com.wizzdi.library.service.LenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}