package com.dev.taskmanage.service;

import com.dev.taskmanage.dto.TaskRequestDTO;
import com.dev.taskmanage.dto.TaskResponseDTO;
import com.dev.taskmanage.exception.ResourceNotFoundException;
import com.dev.taskmanage.model.Task;
import com.dev.taskmanage.model.TaskPriority;
import com.dev.taskmanage.model.TaskStatus;
import com.dev.taskmanage.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    // Injeção de dependências via construtor
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Método para cadastro de novas tarefas
    @Transactional
    public TaskResponseDTO registerTask(TaskRequestDTO dto) {
        Task task = new Task();

        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
        task.setPriority(dto.priority());
        task.setDueDate(dto.dueDate());
        task.setCompletedAt(dto.completedAt());

        Task savedTask = taskRepository.save(task);

        return toResponseDTO(savedTask);
    }

    // Método para listar todas as tarefas
    @Transactional(readOnly = true)
    public List<TaskResponseDTO> listTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // Método para procurar tarefa pelo ID
    @Transactional(readOnly = true)
    public TaskResponseDTO findById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada: " + id));

        return toResponseDTO(task);
    }

    // Método para listar as tarefas pelo status
    @Transactional(readOnly = true)
    public List<TaskResponseDTO> findByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // Método para listar as tarefas pela prioridade
    @Transactional(readOnly = true)
    public List<TaskResponseDTO> findByPriority(TaskPriority priority) {
        return taskRepository.findByPriority(priority)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // Método para listar as tarefas por período
    @Transactional(readOnly = true)
    public List<TaskResponseDTO> filterByPeriod(LocalDate createdAt, LocalDate dueDate) {
        return taskRepository.filterByPeriod(createdAt, dueDate)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // Converte Entity em DTO de resposta
    public TaskResponseDTO toResponseDTO(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.getCompletedAt()
        );
    }
}
