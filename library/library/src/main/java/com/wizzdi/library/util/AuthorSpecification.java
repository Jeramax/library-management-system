package com.wizzdi.library.util;

import com.wizzdi.library.model.dto.EntityFilterDTO;
import com.wizzdi.library.model.entity.Author;
import com.wizzdi.library.model.entity.Book;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;


import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class AuthorSpecification {

    public static Specification<Author> filter(EntityFilterDTO filter) {
        return (Root<Author> root, jakarta.persistence.criteria.CriteriaQuery<?> query, jakarta.persistence.criteria.CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filter by name pattern
            if (filter.getNamePattern() != null && !filter.getNamePattern().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getNamePattern() + "%"));
            }

            // Filter by referenced IDs
            if (filter.getReferencedIds() != null && !filter.getReferencedIds().isEmpty()) {
                predicates.add(root.get("id").in(filter.getReferencedIds()));
            }

            // Filter by date range
            if (filter.getStartDate() != null && filter.getEndDate() != null) {
                predicates.add(criteriaBuilder.between(root.get("dateCreated"), filter.getStartDate(), filter.getEndDate()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}