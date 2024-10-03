package com.wizzdi.library.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookDTO {
    private String name;
    private String description;
    private List<String> genreIds;  // List of Genre IDs
    private List<String> authorIds;  // List of Author IDs
}
