package com.wizzdi.library.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "genre")
public class Genre extends Basic {
    // No additional fields for now.
}