package com.wizzdi.library.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookFilterDTO {

    private String name;
    private String description;
    private int page;
    private int size;
}