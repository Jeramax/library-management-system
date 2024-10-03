package com.wizzdi.library.controller;

import com.wizzdi.library.model.dto.GenreDTO;
import com.wizzdi.library.model.entity.Genre;
import com.wizzdi.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody GenreDTO genreDTO) {
        Genre createdGenre = genreService.createGenre(genreDTO);
        return ResponseEntity.ok(createdGenre);
    }
}