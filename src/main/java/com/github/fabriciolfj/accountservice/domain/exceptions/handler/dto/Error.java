package com.github.fabriciolfj.accountservice.domain.exceptions.handler.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Error {

    private LocalDateTime dateTime;
    private String message;
}
