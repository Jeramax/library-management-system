package com.wizzdi.library.controller;


import com.wizzdi.library.model.dto.CreateLibraryDataDTO;
import com.wizzdi.library.model.entity.Book;
import com.wizzdi.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @PostMapping("/create")
    public ResponseEntity<Book> createLibraryData(@RequestBody CreateLibraryDataDTO dto) {
        Book book = libraryService.createLibraryData(dto);
        return ResponseEntity.ok(book);
    }
}