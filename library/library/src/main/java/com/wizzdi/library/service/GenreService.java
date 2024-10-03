package com.wizzdi.library.service;

import com.wizzdi.library.model.dto.GenreDTO;
import com.wizzdi.library.model.entity.Genre;
import com.wizzdi.library.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public Genre createGenre(GenreDTO genreDTO) {
        Genre genre = new Genre();
        genre.setId(UUID.randomUUID().toString());
        genre.setName(genreDTO.getName());// Manually assign ID
        genre.setDescription(genreDTO.getDescription());

        return genreRepository.save(genre);
    }
}