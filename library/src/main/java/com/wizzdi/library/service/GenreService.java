package com.wizzdi.library.service;

import com.wizzdi.library.model.dto.GenreDTO;
import com.wizzdi.library.model.entity.Genre;
import com.wizzdi.library.repository.GenreRepository;
import com.wizzdi.library.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Optional<Genre> getGenreById(String genreId) {
        return genreRepository.findById(genreId);
    }

    // Update a Genre
    public Genre updateGenre(String genreId, GenreDTO genreDetails) {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + genreId));

        genre.setName(genreDetails.getName());
        genre.setDescription(genreDetails.getDescription());
        return genreRepository.save(genre);
    }

    // Delete a Genre
    public void deleteGenre(String genreId) {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + genreId));
        genre.setSoftDelete(true);
        genreRepository.save(genre);
    }
}