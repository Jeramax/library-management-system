package com.wizzdi.library.service;

import com.wizzdi.library.model.dto.BookToGenreDTO;
import com.wizzdi.library.model.entity.BookToGenre;
import com.wizzdi.library.repository.BookRepository;
import com.wizzdi.library.repository.BookToGenreRepository;
import com.wizzdi.library.repository.GenreRepository;
import com.wizzdi.library.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookToGenreService {


    @Autowired
    private BookToGenreRepository bookToGenreRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    // Fetch all BookToGenre associations
    public List<BookToGenre> getAllBookToGenres() {
        return bookToGenreRepository.findAll();
    }

    // Create a new BookToGenre association
    public BookToGenre createBookToGenre(BookToGenreDTO bookToGenreDTO) {
        var book = bookRepository.findById(bookToGenreDTO.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookToGenreDTO.getBookId()));

        var genre = genreRepository.findById(bookToGenreDTO.getGenreId())
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + bookToGenreDTO.getGenreId()));

        BookToGenre bookToGenre = new BookToGenre();
        bookToGenre.setBook(book);
        bookToGenre.setGenre(genre);

        return bookToGenreRepository.save(bookToGenre);
    }

    // Get BookToGenre association by ID
    public BookToGenre getBookToGenreById(String id) {
        return bookToGenreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BookToGenre not found with id: " + id));
    }

    // Update BookToGenre association
    public BookToGenre updateBookToGenre(String id, BookToGenreDTO bookToGenreDTO) {
        BookToGenre bookToGenre = getBookToGenreById(id);
        var book = bookRepository.findById(bookToGenreDTO.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookToGenreDTO.getBookId()));

        var genre = genreRepository.findById(bookToGenreDTO.getGenreId())
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + bookToGenreDTO.getGenreId()));

        bookToGenre.setBook(book);
        bookToGenre.setGenre(genre);

        return bookToGenreRepository.save(bookToGenre);
    }

    // Delete BookToGenre association
    public void deleteBookToGenre(String id) {
        BookToGenre bookToGenre = getBookToGenreById(id);
        bookToGenre.setSoftDelete(true);
        bookToGenreRepository.save(bookToGenre);
    }
}
