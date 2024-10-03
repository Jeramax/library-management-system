package com.wizzdi.library.model.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class LenderToBookInstanceDTO {
    private String lenderId;
    private String bookInstanceId;
    private OffsetDateTime lent;
    private OffsetDateTime dueBack;
    private OffsetDateTime returned;
}