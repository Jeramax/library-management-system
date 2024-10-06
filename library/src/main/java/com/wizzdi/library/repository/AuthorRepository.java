package com.wizzdi.library.repository;

import com.wizzdi.library.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuthorRepository extends JpaRepository<Author, String>, JpaSpecificationExecutor<Author> {}