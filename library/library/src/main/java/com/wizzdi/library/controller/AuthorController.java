package com.wizzdi.library.controller;

import com.wizzdi.library.model.dto.AuthorDTO;
import com.wizzdi.library.model.dto.EntityFilterDTO;
import com.wizzdi.library.model.entity.Author;
import com.wizzdi.library.model.entity.Book;
import com.wizzdi.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/filter")
    public ResponseEntity<List<Author>> getFilteredAuthors(@RequestBody EntityFilterDTO filter) {
        List<Author> authors = authorService.getFilteredAuthors(filter);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorDTO authorDTO) {
        Author author = authorService.createAuthor(authorDTO);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }
}