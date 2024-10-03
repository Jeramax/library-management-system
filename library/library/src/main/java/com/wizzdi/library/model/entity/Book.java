package com.wizzdi.library.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(
        name = "book",
        indexes = {
                @Index(name = "idx_book_name", columnList = "name"),
                @Index(name = "idx_book_date_created", columnList = "dateCreated"),
                @Index(name = "idx_book_date_updated", columnList = "dateUpdated")
        }
)
public class Book extends Basic {
    @OneToMany(mappedBy = "book")
    private List<BookToGenre> genres;

    @OneToMany(mappedBy = "book")
    private List<AuthorToBook> authors;
}