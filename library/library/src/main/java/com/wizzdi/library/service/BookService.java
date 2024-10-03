package com.wizzdi.library.service;

import com.wizzdi.library.model.dto.BookDTO;
import com.wizzdi.library.model.dto.EntityFilterDTO;
import com.wizzdi.library.model.entity.*;
import com.wizzdi.library.repository.AuthorRepository;
import com.wizzdi.library.repository.BookRepository;
import com.wizzdi.library.repository.GenreRepository;
import com.wizzdi.library.util.AuthorSpecification;
import org.springframework.beans.factory.annotation.Autowired;
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
}
