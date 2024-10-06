package com.wizzdi.library.service;

import com.wizzdi.library.model.dto.BookDTO;
import com.wizzdi.library.model.dto.BookFilterDTO;
import com.wizzdi.library.model.dto.EntityFilterDTO;
import com.wizzdi.library.model.entity.*;
import com.wizzdi.library.repository.AuthorRepository;
import com.wizzdi.library.repository.BookRepository;
import com.wizzdi.library.repository.GenreRepository;
import com.wizzdi.library.util.AuthorSpecification;
import com.wizzdi.library.util.BooksSpecification;
import com.wizzdi.library.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    private final BooksSpecification booksSpecification;

    @Autowired
    public BookService(BooksSpecification booksSpecification) {
        this.booksSpecification = booksSpecification;
    }
    public Page<Book> getFilteredBooks(BookFilterDTO filter) {
        return booksSpecification.filterBooks(filter);
    }

    public Book createBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setDescription(bookDTO.getDescription());

        // Set genres
        List<BookToGenre> genres = bookDTO.getGenreIds().stream()
                .map(id -> {
                    Genre genre = genreRepository.findById(UUID.fromString(id).toString()).orElseThrow();
                    return new BookToGenre(book, genre);
                }).toList();
        book.setGenres(genres);

        // Set authors
        List<AuthorToBook> authors = bookDTO.getAuthorIds().stream()
                .map(id -> {
                    Author author = authorRepository.findById(UUID.fromString(id).toString()).orElseThrow();
                    return new AuthorToBook(author, book);
                }).toList();
        book.setAuthors(authors);
        book.setId(UUID.randomUUID().toString());
        return bookRepository.save(book);
    }

    // Get book by ID
    public Book getBookById(String id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));
    }

    // Update a book
    public Book updateBook(String id, BookDTO bookDTO) {
        Book book = getBookById(id);
        // Update the Book entity with the new data
        book.setName(bookDTO.getName());
        book.setDescription(bookDTO.getDescription());
        // Save the updated book
        return bookRepository.save(book);
    }

    // Delete a book
    public void deleteBook(String id) {
        Book book = getBookById(id);
        book.setSoftDelete(true);
        bookRepository.save(book);
    }
}
