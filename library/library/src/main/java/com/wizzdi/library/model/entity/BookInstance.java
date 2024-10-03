package com.wizzdi.library.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "book_instance")
public class BookInstance extends Basic {
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private String serialNumber;
    private boolean blocked;
}