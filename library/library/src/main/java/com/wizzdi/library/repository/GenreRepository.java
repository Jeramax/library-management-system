package com.wizzdi.library.repository;

import com.wizzdi.library.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, String> {}

