package com.wizzdi.library.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@MappedSuperclass
@Data
public abstract  class Basic {
    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private String name;

    @Lob
    private String description;

    @Column(nullable = false)
    private OffsetDateTime dateCreated = OffsetDateTime.now();
    @Column(nullable = false)
    private OffsetDateTime dateUpdated;
    private boolean softDelete = false;

    @PrePersist
    public void prePersist() {
        dateCreated = OffsetDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        dateUpdated = OffsetDateTime.now();
    }

    public Basic() {
        this.dateCreated = OffsetDateTime.now();
        this.dateUpdated = OffsetDateTime.now(); // Initialize dateUpdated
        this.softDelete = false;
    }
}