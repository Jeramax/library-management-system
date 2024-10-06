package com.wizzdi.library.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

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
    @ToString.Exclude
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<BookToGenre> genres;

    @ToString.Exclude
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AuthorToBook> authors;


}