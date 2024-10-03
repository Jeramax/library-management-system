package com.wizzdi.library.model.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class LenderDTO {
    private String email;
    private String address;
    private String phoneNumber;
    private String gender;
    private OffsetDateTime birthDate;
    private String socialSecurityNumber;
    private boolean blocked;
}