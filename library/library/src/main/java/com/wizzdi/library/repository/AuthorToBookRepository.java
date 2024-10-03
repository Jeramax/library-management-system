package com.wizzdi.library.repository;

import com.wizzdi.library.model.entity.AuthorToBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorToBookRepository extends JpaRepository<AuthorToBook, String> {}
