package com.wizzdi.library.controller;

import com.wizzdi.library.model.dto.AuthorDTO;
import com.wizzdi.library.model.dto.EntityFilterDTO;
import com.wizzdi.library.model.entity.Author;
import com.wizzdi.library.model.entity.Book;
import com.wizzdi.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/filter")
    public ResponseEntity<Page<Author>> getFilteredAuthors(@RequestBody EntityFilterDTO filter) {
        Page<Author> authors = authorService.getFilteredAuthors(filter);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorDTO authorDTO) {
        Author author = authorService.createAuthor(authorDTO);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable String id) {
        Author author = authorService.getAuthorById(id);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    // Update an author
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable String id, @RequestBody AuthorDTO authorDTO) {
        Author updatedAuthor = authorService.updateAuthor(id, authorDTO);
        return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
    }

    // Delete an author
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable String id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}