package com.wizzdi.library.util;

import com.wizzdi.library.model.entity.Author;
import com.wizzdi.library.model.entity.Book;
import com.wizzdi.library.model.dto.BookFilterDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class BooksSpecification {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Page<Book> filterBooks(BookFilterDTO filter) {
        // Create a new CriteriaBuilder and CriteriaQuery
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);
        // Create a list to hold predicates
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getName() != null && !filter.getName().isEmpty()) {
            predicates.add(cb.like(root.get("name").as(String.class), "%" + filter.getName() + "%"));
        }

        if (filter.getDescription() != null && !filter.getDescription().isEmpty()) {
            predicates.add(cb.like(root.get("description").as(String.class), "%" + filter.getDescription() + "%"));
        }

        query.select(root).distinct(true).where(predicates.toArray(new Predicate[0]));
        PageRequest pageable = PageRequest.of(filter.getPage(), filter.getSize());
        TypedQuery<Book> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Book> books = typedQuery.getResultList();
        long totalRecords = getTotalRecords(predicates);

        return new PageImpl<>(books, pageable, totalRecords);
    }

    private long getTotalRecords(List<Predicate> predicates) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Book> countRoot = countQuery.from(Book.class);
         countQuery.select(cb.count(countRoot));
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}