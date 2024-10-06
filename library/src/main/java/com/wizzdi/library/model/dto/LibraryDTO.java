package com.wizzdi.library.model.dto;

import jakarta.validation.constraints.NotBlank;

public class LibraryDTO {

    @NotBlank
    private String name;

    private String address;
    private String phoneNumber;
}
