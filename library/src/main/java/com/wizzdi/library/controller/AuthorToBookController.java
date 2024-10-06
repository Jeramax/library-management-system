package com.wizzdi.library.controller;

import com.wizzdi.library.model.dto.AuthorToBookDTO;
import com.wizzdi.library.model.entity.AuthorToBook;
import com.wizzdi.library.service.AuthorToBookService;
import com.wizzdi.library.util.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors-to-books")
public class AuthorToBookController {

    @Autowired
    private AuthorToBookService authorToBookService;

    // Get all AuthorToBook associations
    @GetMapping
    public List<AuthorToBook> getAllAuthorToBooks() {
        return authorToBookService.getAllAuthorToBooks();
    }

    // Create a new AuthorToBook association
    @PostMapping
    public AuthorToBook createAuthorToBook(@Valid @RequestBody AuthorToBookDTO authorToBookDTO) {
        return authorToBookService.createAuthorToBook(authorToBookDTO);
    }

    // Get an AuthorToBook association by ID
    @GetMapping("/{id}")
    public ResponseEntity<AuthorToBook> getAuthorToBookById(@PathVariable(value = "id") String authorToBookId) {
        AuthorToBook authorToBook = authorToBookService.getAuthorToBookById(authorToBookId)
                .orElseThrow(() -> new ResourceNotFoundException("AuthorToBook association not found with id: " + authorToBookId));
        return ResponseEntity.ok().body(authorToBook);
    }

    // Update an AuthorToBook association
    @PutMapping("/{id}")
    public ResponseEntity<AuthorToBook> updateAuthorToBook(@PathVariable(value = "id") String authorToBookId,
                                                           @Valid @RequestBody AuthorToBookDTO authorToBookDetails) {
        AuthorToBook updatedAuthorToBook = authorToBookService.updateAuthorToBook(authorToBookId, authorToBookDetails);
        return ResponseEntity.ok(updatedAuthorToBook);
    }

    // Delete an AuthorToBook association
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthorToBook(@PathVariable(value = "id") String authorToBookId) {
        authorToBookService.deleteAuthorToBook(authorToBookId);
        return ResponseEntity.ok().build();
    }
}