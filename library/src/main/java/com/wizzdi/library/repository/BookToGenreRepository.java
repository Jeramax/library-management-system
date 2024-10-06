package com.wizzdi.library.repository;

import com.wizzdi.library.model.entity.BookToGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookToGenreRepository extends JpaRepository<BookToGenre, String> {}
