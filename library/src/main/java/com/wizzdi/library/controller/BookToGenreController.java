package com.wizzdi.library.controller;


import com.wizzdi.library.model.dto.BookToGenreDTO;
import com.wizzdi.library.model.entity.BookToGenre;
import com.wizzdi.library.service.BookToGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-to-genre")
public class BookToGenreController {

    @Autowired
    private BookToGenreService bookToGenreService;

    // Get all BookToGenre associations
    @GetMapping
    public List<BookToGenre> getAllBookToGenres() {
        return bookToGenreService.getAllBookToGenres();
    }

    // Create a new BookToGenre association
    @PostMapping
    public BookToGenre createBookToGenre(@RequestBody BookToGenreDTO bookToGenreDTO) {
        return bookToGenreService.createBookToGenre(bookToGenreDTO);
    }

    // Get a specific BookToGenre association by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookToGenre> getBookToGenreById(@PathVariable String id) {
        BookToGenre bookToGenre = bookToGenreService.getBookToGenreById(id);
        return ResponseEntity.ok(bookToGenre);
    }

    // Update an existing BookToGenre association
    @PutMapping("/{id}")
    public BookToGenre updateBookToGenre(@PathVariable String id, @RequestBody BookToGenreDTO bookToGenreDTO) {
        return bookToGenreService.updateBookToGenre(id, bookToGenreDTO);
    }

    // Delete a BookToGenre association
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookToGenre(@PathVariable String id) {
        bookToGenreService.deleteBookToGenre(id);

        return ResponseEntity.noContent().build();
    }
}
