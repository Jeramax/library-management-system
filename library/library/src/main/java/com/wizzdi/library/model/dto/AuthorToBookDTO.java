package com.wizzdi.library.model.dto;

import lombok.Data;

@Data
public class AuthorToBookDTO {
    private String authorId;
    private String bookId;
    private boolean mainAuthor;
}