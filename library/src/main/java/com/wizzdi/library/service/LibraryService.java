package com.wizzdi.library.service;

import com.wizzdi.library.model.dto.CreateLibraryDataDTO;
import com.wizzdi.library.model.entity.*;
import com.wizzdi.library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LibraryService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookInstanceRepository bookInstanceRepository;

    @Autowired
    private LenderRepository lenderRepository;

    @Autowired
    private LenderToBookInstanceRepository lenderToBookInstanceRepository;

    @Transactional
    public Book createLibraryData(CreateLibraryDataDTO dto) {
        // Step 1: Create Book entity
        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setName(dto.getBookName());
        book.setDescription(dto.getBookDescription());
        book.setDateCreated(OffsetDateTime.now());
        book.setDateUpdated(OffsetDateTime.now());

        // Step 2: Fetch genres and map to BookToGenre
        List<Genre> genres = genreRepository.findAllById(dto.getGenreIds());
        List<BookToGenre> bookToGenres = genres.stream()
                .map(genre -> {
                    BookToGenre bookToGenre = new BookToGenre(book, genre);
                    bookToGenre.setId(UUID.randomUUID().toString());
                    bookToGenre.setName("Test");
                    return bookToGenre;
                })
                .collect(Collectors.toList());
        book.setGenres(bookToGenres);

        // Step 3: Fetch authors and map to AuthorToBook
        List<Author> authors = authorRepository.findAllById(dto.getAuthorIds());
        List<AuthorToBook> authorToBooks = authors.stream()
                .map(author -> {
                    AuthorToBook authorToBook = new AuthorToBook(author, book, true);
                    // Manually set the ID
                    authorToBook.setId(UUID.randomUUID().toString());
                    authorToBook.setName("Test");
                    return authorToBook;
                })
                .collect(Collectors.toList());
        book.setAuthors(authorToBooks);

        // Step 4: Save the Book entity
        bookRepository.save(book);

        // Step 5: Create BookInstance
        BookInstance bookInstance = new BookInstance();
        bookInstance.setId(UUID.randomUUID().toString());
        bookInstance.setName("TEST");
        bookInstance.setBook(book);
        bookInstance.setSerialNumber(dto.getSerialNumber());
        bookInstance.setBlocked(dto.isBlocked());
        bookInstance.setDateCreated(OffsetDateTime.now());
        bookInstance.setDateUpdated(OffsetDateTime.now());

        // Save the BookInstance entity
        bookInstanceRepository.save(bookInstance);

        // Step 6: Link LenderToBookInstance if lenderId is provided
        if (dto.getLenderId() != null) {
            Lender lender = lenderRepository.findById(dto.getLenderId())
                    .orElseThrow(() -> new RuntimeException("Lender not found with ID: " + dto.getLenderId()));

            LenderToBookInstance lenderToBookInstance = new LenderToBookInstance();
            lenderToBookInstance.setId(UUID.randomUUID().toString());
            lenderToBookInstance.setName("TEST");
            lenderToBookInstance.setLender(lender);
            lenderToBookInstance.setBookInstance(bookInstance);
            lenderToBookInstance.setLent(dto.getLentDate());
            lenderToBookInstance.setDueBack(dto.getDueBackDate());
            lenderToBookInstance.setReturned(dto.getReturnedDate());

            // Save the LenderToBookInstance entity
            lenderToBookInstanceRepository.save(lenderToBookInstance);
        }

        return book;
    }
}
