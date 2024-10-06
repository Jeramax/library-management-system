package com.wizzdi.library.model.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class AuthorDTO {
    private String email;
    private String name;
    private String address;
    private String phoneNumber;
    private String gender;
    private OffsetDateTime birthDate;
    private String socialSecurityNumber;
}
