package com.wizzdi.library.util;

import com.wizzdi.library.model.dto.EntityFilterDTO;
import com.wizzdi.library.model.entity.Author;
import com.wizzdi.library.model.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;


import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorSpecification {

    @PersistenceContext
    private EntityManager entityManager;

    public Page<Author> filter(EntityFilterDTO filter) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> query = cb.createQuery(Author.class);
        Root<Author> root = query.from(Author.class);

        List<Predicate> predicates = new ArrayList<>();
        if (filter.getNamePattern() != null && !filter.getNamePattern().isEmpty()) {
            predicates.add(cb.like(root.get("name").as(String.class), "%" + filter.getNamePattern() + "%"));
        }

        // Filter by referenced IDs
        if (filter.getReferencedIds() != null && !filter.getReferencedIds().isEmpty()) {
            predicates.add(root.get("id").as(String.class).in(filter.getReferencedIds()));
        }

        // Filter by date range
        if (filter.getStartDate() != null && filter.getEndDate() != null) {
            predicates.add(cb.between(root.get("dateCreated").as(OffsetDateTime.class), filter.getStartDate(), filter.getEndDate()));
        }

        query.select(root).distinct(true).where(predicates.toArray(new Predicate[0]));
        PageRequest pageable = PageRequest.of(filter.getPage(), filter.getSize());
        TypedQuery<Author> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Author> authors = typedQuery.getResultList();
        long totalRecords = getTotalRecords(predicates);

        return new PageImpl<>(authors, pageable, totalRecords);
    }

    private long getTotalRecords(List<Predicate> predicates) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Book> countRoot = countQuery.from(Book.class);
        countQuery.select(cb.count(countRoot));
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}