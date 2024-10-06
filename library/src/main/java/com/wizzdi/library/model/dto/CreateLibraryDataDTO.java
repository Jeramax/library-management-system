package com.wizzdi.library.model.dto;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class CreateLibraryDataDTO {

    // Book data
    private String bookName;
    private String bookDescription;
    private List<String> genreIds;   // IDs of existing Genres
    private List<String> authorIds;  // IDs of existing Authors

    // BookInstance data
    private String serialNumber;
    private boolean blocked;

    // LenderToBookInstance data
    private String lenderId;
    private OffsetDateTime lentDate;
    private OffsetDateTime dueBackDate;
    private OffsetDateTime returnedDate;

}