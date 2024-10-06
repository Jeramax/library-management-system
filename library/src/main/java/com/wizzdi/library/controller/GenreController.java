package com.wizzdi.library.controller;

import com.wizzdi.library.model.dto.GenreDTO;
import com.wizzdi.library.model.entity.Genre;
import com.wizzdi.library.service.GenreService;
import com.wizzdi.library.util.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody GenreDTO genreDTO) {
        Genre createdGenre = genreService.createGenre(genreDTO);
        return ResponseEntity.ok(createdGenre);
    }
    @GetMapping
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable(value = "id") String genreId) {
        Genre genre = genreService.getGenreById(genreId)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + genreId));
        return ResponseEntity.ok().body(genre);
    }

    // Update a Genre
    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable(value = "id") String genreId,
                                             @Valid @RequestBody GenreDTO genreDetails) {
        Genre updatedGenre = genreService.updateGenre(genreId, genreDetails);
        return ResponseEntity.ok(updatedGenre);
    }

    // Delete a Genre
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGenre(@PathVariable(value = "id") String genreId) {
        genreService.deleteGenre(genreId);
        return ResponseEntity.ok().build();
    }
}