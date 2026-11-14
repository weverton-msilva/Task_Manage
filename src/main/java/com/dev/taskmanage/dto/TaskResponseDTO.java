package com.dev.taskmanage.dto;

import com.dev.taskmanage.model.TaskPriority;
import com.dev.taskmanage.model.TaskStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        TaskStatus status,
        TaskPriority priority,
        LocalDate dueDate,
        LocalDateTime completedAt
) {}