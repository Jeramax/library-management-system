package com.wizzdi.library.repository;

import com.wizzdi.library.model.entity.BookInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookInstanceRepository extends JpaRepository<BookInstance, String> {}
