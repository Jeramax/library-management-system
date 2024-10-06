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
        name = "author",
        indexes = {
                @Index(name = "idx_author_name", columnList = "name"),
                @Index(name = "idx_author_date_created", columnList = "dateCreated"),
                @Index(name = "idx_author_date_updated", columnList = "dateUpdated")
        }
)
public class Author extends Person {
    @OneToMany(mappedBy = "author")
    private List<AuthorToBook> books;
}