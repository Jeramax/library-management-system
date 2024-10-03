package com.wizzdi.library.model.dto;

import lombok.Data;

@Data
public class BookInstanceDTO {
    private String bookId;
    private String serialNumber;
    private boolean blocked;
}
