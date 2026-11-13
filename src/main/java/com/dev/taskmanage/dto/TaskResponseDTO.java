package com.dev.taskmanage.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        String status,
        String priority,
        LocalDate dueDate,
        LocalDateTime completedAt
) {}