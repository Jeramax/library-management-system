package com.wizzdi.library.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "lender_to_book_instance")
public class LenderToBookInstance extends Basic {
    @ManyToOne
    @JoinColumn(name = "lender_id")
    private Lender lender;

    @ManyToOne
    @JoinColumn(name = "book_instance_id")
    private BookInstance bookInstance;

    private OffsetDateTime lent;
    private OffsetDateTime dueBack;
    private OffsetDateTime returned;
}