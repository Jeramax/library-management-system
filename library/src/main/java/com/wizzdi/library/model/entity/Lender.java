package com.wizzdi.library.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "lender")
public class Lender extends Person {
    private boolean blocked;
}