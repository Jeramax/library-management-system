package com.wizzdi.library.service;

import com.wizzdi.library.model.dto.AuthorDTO;
import com.wizzdi.library.model.dto.BookFilterDTO;
import com.wizzdi.library.model.dto.EntityFilterDTO;
import com.wizzdi.library.model.entity.Author;
import com.wizzdi.library.model.entity.Book;
import com.wizzdi.library.model.entity.Person;
import com.wizzdi.library.repository.AuthorRepository;
import com.wizzdi.library.util.AuthorSpecification;
import com.wizzdi.library.util.BooksSpecification;
import com.wizzdi.library.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    private final AuthorSpecification authorSpecification;

    public AuthorService(AuthorRepository authorRepository, AuthorSpecification authorSpecification) {
        this.authorRepository = authorRepository;
        this.authorSpecification = authorSpecification;
    }

    public Page<Author> getFilteredAuthors(EntityFilterDTO filter) {
        return authorSpecification.filter(filter);
    }

    public Author createAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(UUID.randomUUID().toString()); // Manually assign ID
        author.setEmail(authorDTO.getEmail());
        author.setName(authorDTO.getName());
        author.setAddress(authorDTO.getAddress());
        author.setPhoneNumber(authorDTO.getPhoneNumber());
        author.setGender(Person.Gender.valueOf(authorDTO.getGender()));
        author.setBirthDate(authorDTO.getBirthDate());
        author.setSocialSecurityNumber(authorDTO.getSocialSecurityNumber());

        author.setDateCreated(OffsetDateTime.now());
        author.setDateUpdated(OffsetDateTime.now());

        return authorRepository.save(author);
    }

    public Author getAuthorById(String id) {
        return authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found with ID: " + id));
    }

    // Update an author
    public Author updateAuthor(String id, AuthorDTO authorDTO) {
        Author author = getAuthorById(id);
        author.setName(authorDTO.getName());
        author.setName(authorDTO.getName());
        return authorRepository.save(author);
    }

    // Delete an author
    public void deleteAuthor(String id) {
        Author author = getAuthorById(id);
        author.setSoftDelete(true);
        authorRepository.save(author);
    }
}