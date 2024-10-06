package com.wizzdi.library.service;


import com.wizzdi.library.model.dto.BookInstanceDTO;
import com.wizzdi.library.model.entity.BookInstance;
import com.wizzdi.library.repository.BookInstanceRepository;
import com.wizzdi.library.repository.BookRepository;
import com.wizzdi.library.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookInstanceService {

    @Autowired
    private BookInstanceRepository bookInstanceRepository;

    @Autowired
    private BookRepository bookRepository;

    // Fetch all BookInstances
    public List<BookInstance> getAllBookInstances() {
        return bookInstanceRepository.findAll();
    }

    // Create a new BookInstance
    public BookInstance createBookInstance(BookInstanceDTO bookInstanceDTO) {
        BookInstance bookInstance = new BookInstance();
        bookInstance.setBook(bookRepository.findById(bookInstanceDTO.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found")));
        bookInstance.setSerialNumber(bookInstanceDTO.getSerialNumber());
        bookInstance.setBlocked(bookInstanceDTO.isBlocked());
        return bookInstanceRepository.save(bookInstance);
    }

    // Get a BookInstance by ID
    public BookInstance getBookInstanceById(String id) {
        return bookInstanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BookInstance not found"));
    }

    // Update a BookInstance
    public BookInstance updateBookInstance(String id, BookInstanceDTO bookInstanceDTO) {
        BookInstance bookInstance = getBookInstanceById(id);
        bookInstance.setSerialNumber(bookInstanceDTO.getSerialNumber());
        bookInstance.setBlocked(bookInstanceDTO.isBlocked());
        return bookInstanceRepository.save(bookInstance);
    }

    // Delete a BookInstance
    public void deleteBookInstance(String id) {
        BookInstance bookInstance = getBookInstanceById(id);
        bookInstance.setSoftDelete(true);
        bookInstanceRepository.save(bookInstance);
    }
}