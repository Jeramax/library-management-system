package com.wizzdi.library.repository;

import com.wizzdi.library.model.entity.Lender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LenderRepository extends JpaRepository<Lender, String> {}
