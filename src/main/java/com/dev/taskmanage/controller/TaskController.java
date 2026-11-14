package com.dev.taskmanage.controller;

import com.dev.taskmanage.dto.TaskRequestDTO;
import com.dev.taskmanage.dto.TaskResponseDTO;
import com.dev.taskmanage.model.TaskPriority;
import com.dev.taskmanage.model.TaskStatus;
import com.dev.taskmanage.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    // Injeção de dependências via construtor
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Endpoint 1: Cadastrar tarefa
    @PostMapping
    public ResponseEntity<TaskResponseDTO> registerTask(@Valid @RequestBody TaskRequestDTO dto) {
        TaskResponseDTO responseDTO = taskService.registerTask(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDTO.id())
                .toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }

    // Endpoint 2: listar tarefas
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> registerTask() {
        List<TaskResponseDTO> responseDTO = taskService.listTasks();

        return ResponseEntity.ok(responseDTO);
    }

    // Endpoint 3: Filtrar tarefa pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> findById(@PathVariable Long id) {
        TaskResponseDTO responseDTOS = taskService.findById(id);

        return ResponseEntity.ok(responseDTOS);
    }

    // Endpoint 4: Filtrar tarefas pelo status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskResponseDTO>> findByStatus(@PathVariable TaskStatus status) {
        List<TaskResponseDTO> responseDTOS = taskService.findByStatus(status);

        return ResponseEntity.ok(responseDTOS);
    }

    // Endpoint 5: Filtar tarefas pela prioridade
    @GetMapping("/priority/{priority}")
    private ResponseEntity<List<TaskResponseDTO>> findByPriority(@PathVariable TaskPriority priority) {
        List<TaskResponseDTO> responseDTOS = taskService.findByPriority(priority);

        return ResponseEntity.ok(responseDTOS);
    }

    // Endpoint 6: Filtrar tarefas por período
    @GetMapping("/period")
    public ResponseEntity<List<TaskResponseDTO>> filterByPeriod(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<TaskResponseDTO> responseDTO = taskService.filterByPeriod(startDate, endDate);

        return ResponseEntity.ok(responseDTO);
    }
}
