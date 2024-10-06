package com.wizzdi.library.model.dto;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class EntityFilterDTO {
    private String namePattern;          // Pattern to filter names
    private List<String> referencedIds;  // List of referenced IDs
    private OffsetDateTime startDate;    // Start date for filtering
    private OffsetDateTime endDate;
    private int page;
    private int size;

}