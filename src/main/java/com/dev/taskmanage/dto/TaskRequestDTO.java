package com.dev.taskmanage.dto;

import com.dev.taskmanage.model.Task;
import com.dev.taskmanage.model.TaskPriority;
import com.dev.taskmanage.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskRequestDTO(
        @NotBlank(message = "O título precisa está preenchido.")
        @Size(min = 3, max = 100)
        String title,

        @Size(max = 500)
        String description,

        @NotNull(message = "O status precisa está preenchido.")
        TaskStatus status,

        @NotNull(message = "O nível de prioridade precisa está preenchido.")
        TaskPriority priority,

        LocalDate dueDate,
        LocalDateTime completedAt
) {}
