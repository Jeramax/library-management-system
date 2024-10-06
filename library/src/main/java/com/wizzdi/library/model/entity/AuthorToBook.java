package com.wizzdi.library.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "author_to_book")
@AllArgsConstructor
@NoArgsConstructor
public class AuthorToBook extends Basic {
    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private Author author;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private boolean mainAuthor;

    public AuthorToBook(Author author, Book book) {
        this.author = author;
        this.book = book;
    }
}