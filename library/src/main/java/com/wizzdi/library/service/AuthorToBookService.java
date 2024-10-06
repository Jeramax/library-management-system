package com.wizzdi.library.service;

import com.wizzdi.library.model.entity.Author;
import com.wizzdi.library.model.entity.Book;
import com.wizzdi.library.repository.AuthorRepository;
import com.wizzdi.library.repository.BookRepository;
import com.wizzdi.library.util.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import com.wizzdi.library.model.dto.AuthorToBookDTO;
import com.wizzdi.library.model.entity.AuthorToBook;
import com.wizzdi.library.repository.AuthorToBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AuthorToBookService {
    @Autowired
    private AuthorToBookRepository authorToBookRepository;

    @Autowired
    private AuthorRepository authorRepository; // Inject AuthorRepository

    @Autowired
    private BookRepository bookRepository;

    // Get all AuthorToBook associations
    public List<AuthorToBook> getAllAuthorToBooks() {
        return authorToBookRepository.findAll();
    }

    // Create a new AuthorToBook association
    public AuthorToBook createAuthorToBook(AuthorToBookDTO authorToBookDTO) {
        Author author = authorRepository.findById(authorToBookDTO.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorToBookDTO.getAuthorId()));

        Book book = bookRepository.findById(authorToBookDTO.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + authorToBookDTO.getBookId()));

        // Create new AuthorToBook entity and set the author, book, and main fields
        AuthorToBook authorToBook = new AuthorToBook();
        authorToBook.setAuthor(author); // Set the Author object
        authorToBook.setBook(book); // Set the Book object
        authorToBook.setMainAuthor(authorToBookDTO.isMainAuthor());

        return authorToBookRepository.save(authorToBook);
    }

    // Get an AuthorToBook association by ID
    public Optional<AuthorToBook> getAuthorToBookById(String authorToBookId) {
        return authorToBookRepository.findById(authorToBookId);
    }

    // Update an AuthorToBook association
    public AuthorToBook updateAuthorToBook(String authorToBookId, AuthorToBookDTO authorToBookDetails) {
        AuthorToBook authorToBook = authorToBookRepository.findById(authorToBookId)
                .orElseThrow(() -> new ResourceNotFoundException("AuthorToBook association not found with id: " + authorToBookId));

        // Retrieve Author and Book entities using their IDs
        Author author = authorRepository.findById(authorToBookDetails.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorToBookDetails.getAuthorId()));

        Book book = bookRepository.findById(authorToBookDetails.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + authorToBookDetails.getBookId()));

        // Update the AuthorToBook association
        authorToBook.setAuthor(author);
        authorToBook.setBook(book);
        authorToBook.setMainAuthor(authorToBookDetails.isMainAuthor());

        return authorToBookRepository.save(authorToBook);
    }

    // Delete an AuthorToBook association
    public void deleteAuthorToBook(String authorToBookId) {
        AuthorToBook authorToBook = authorToBookRepository.findById(authorToBookId)
                .orElseThrow(() -> new ResourceNotFoundException("AuthorToBook association not found with id: " + authorToBookId));
        authorToBook.setSoftDelete(true);
        authorToBookRepository.save(authorToBook);
    }
}
