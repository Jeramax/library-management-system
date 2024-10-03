package com.wizzdi.library.service;

import com.wizzdi.library.model.dto.AuthorDTO;
import com.wizzdi.library.model.dto.EntityFilterDTO;
import com.wizzdi.library.model.entity.Author;
import com.wizzdi.library.model.entity.Book;
import com.wizzdi.library.model.entity.Person;
import com.wizzdi.library.repository.AuthorRepository;
import com.wizzdi.library.util.AuthorSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getFilteredAuthors(EntityFilterDTO filter) {
        Specification<Author> spec = AuthorSpecification.filter(filter);
        return authorRepository.findAll(spec);
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
}