package com.wizzdi.library.controller;

import com.wizzdi.library.model.dto.BookInstanceDTO;
import com.wizzdi.library.model.entity.BookInstance;
import com.wizzdi.library.service.BookInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-instances")
public class BookInstanceController {

    @Autowired
    private BookInstanceService bookInstanceService;

    // Get all BookInstances
    @GetMapping
    public List<BookInstance> getAllBookInstances() {
        return bookInstanceService.getAllBookInstances();
    }

    // Create a new BookInstance
    @PostMapping
    public ResponseEntity<BookInstance> createBookInstance(@RequestBody BookInstanceDTO bookInstanceDTO) {
        BookInstance createdBookInstance = bookInstanceService.createBookInstance(bookInstanceDTO);
        return ResponseEntity.ok(createdBookInstance);
    }

    // Get a BookInstance by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookInstance> getBookInstanceById(@PathVariable String id) {
        BookInstance bookInstance = bookInstanceService.getBookInstanceById(id);
        return ResponseEntity.ok(bookInstance);
    }

    // Update a BookInstance
    @PutMapping("/{id}")
    public ResponseEntity<BookInstance> updateBookInstance(@PathVariable String id, @RequestBody BookInstanceDTO bookInstanceDTO) {
        BookInstance updatedBookInstance = bookInstanceService.updateBookInstance(id, bookInstanceDTO);
        return ResponseEntity.ok(updatedBookInstance);
    }

    // Delete a BookInstance
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookInstance(@PathVariable String id) {
        bookInstanceService.deleteBookInstance(id);
        return ResponseEntity.noContent().build();
    }
}