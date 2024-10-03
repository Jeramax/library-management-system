package com.wizzdi.library.model.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import java.time.OffsetDateTime;


@Data
@MappedSuperclass
public abstract class Person extends Basic {
    private String email;
    private String address;
    private String phoneNumber;
    private Gender gender;
    private OffsetDateTime birthDate;
    private String socialSecurityNumber;

    public enum Gender {
        MALE, FEMALE, OTHER
    }
}