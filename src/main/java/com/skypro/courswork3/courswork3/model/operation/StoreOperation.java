package com.skypro.courswork3.courswork3.model.operation;

import com.skypro.courswork3.courswork3.exception.OperationType;
import com.skypro.courswork3.courswork3.model.socks.SocksBatch;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreOperation {
    private OperationType type;
    private SocksBatch socksBatch;
    private final LocalDateTime dateTime = LocalDateTime.now();
        }
