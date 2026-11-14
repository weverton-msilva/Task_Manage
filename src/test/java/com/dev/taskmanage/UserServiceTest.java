package com.dev.taskmanage;

import com.dev.taskmanage.dto.TaskRequestDTO;
import com.dev.taskmanage.dto.TaskResponseDTO;
import com.dev.taskmanage.exception.ResourceNotFoundException;
import com.dev.taskmanage.model.Task;
import com.dev.taskmanage.model.TaskPriority;
import com.dev.taskmanage.model.TaskStatus;
import com.dev.taskmanage.repository.TaskRepository;
import com.dev.taskmanage.service.TaskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    @DisplayName("Deve cadastrar uma nova tarefa com sucesso")
    void shouldRegisterTaskSuccessfully() {
        // Arrange
        TaskRequestDTO requestDTO = new TaskRequestDTO(
                "Estudar JUnit",
                "Praticar testes unitários com Mockito",
                TaskStatus.IN_PROGRESS,
                TaskPriority.HIGH,
                LocalDate.now().plusDays(5),
                null
        );

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle(requestDTO.title());
        savedTask.setDescription(requestDTO.description());
        savedTask.setStatus(requestDTO.status());
        savedTask.setPriority(requestDTO.priority());
        savedTask.setDueDate(requestDTO.dueDate());

        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        // Act
        TaskResponseDTO response = taskService.registerTask(requestDTO);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.title()).isEqualTo("Estudar JUnit");
        assertThat(response.status()).isEqualTo(TaskStatus.IN_PROGRESS);

        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    @DisplayName("Deve preencher completedAt automaticamente quando o status for COMPLETED no cadastro")
    void shouldSetCompletedAtWhenStatusIsCompletedOnRegister() {
        // Arrange
        TaskRequestDTO requestDTO = new TaskRequestDTO(
                "Finalizar projeto",
                "Concluir código do backend",
                TaskStatus.COMPLETED,
                TaskPriority.MEDIUM,
                LocalDate.now(),
                null
        );

        ArgumentCaptor<Task> taskArgumentCaptor = ArgumentCaptor.forClass(Task.class);

        Task savedTask = new Task();
        savedTask.setId(2L);
        savedTask.setTitle(requestDTO.title());
        savedTask.setStatus(TaskStatus.COMPLETED);
        savedTask.setCompletedAt(LocalDate.now());

        when(taskRepository.save(taskArgumentCaptor.capture())).thenReturn(savedTask);

        // Act
        taskService.registerTask(requestDTO);

        // Assert
        Task capturedTask = taskArgumentCaptor.getValue();
        assertThat(capturedTask.getStatus()).isEqualTo(TaskStatus.COMPLETED);
        assertThat(capturedTask.getCompletedAt()).isNotNull();
        assertThat(capturedTask.getCompletedAt()).isEqualTo(LocalDate.now());
    }

    @Test
    @DisplayName("Deve listar todas as tarefas com sucesso")
    void shouldListAllTasksSuccessfully() {
        // Arrange
        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Tarefa 1");

        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Tarefa 2");

        when(taskRepository.findAll()).thenReturn(List.of(task1, task2));

        // Act
        List<TaskResponseDTO> responseList = taskService.listTasks();

        // Assert
        assertThat(responseList).hasSize(2);
        assertThat(responseList.get(0).title()).isEqualTo("Tarefa 1");
        assertThat(responseList.get(1).title()).isEqualTo("Tarefa 2");

        verify(taskRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar uma tarefa pelo ID com sucesso")
    void shouldFindByIdSuccessfully() {
        // Arrange
        Long taskId = 1L;
        Task task = new Task();
        task.setId(taskId);
        task.setTitle("Estudar Mockito");

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        // Act
        TaskResponseDTO response = taskService.findById(taskId);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(taskId);
        assertThat(response.title()).isEqualTo("Estudar Mockito");
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException quando o ID não for encontrado")
    void shouldThrowResourceNotFoundExceptionWhenIdNotFound() {
        // Arrange
        Long taskId = 99L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            taskService.findById(taskId);
        });

        assertThat(exception.getMessage()).isEqualTo("Tarefa não encontrada: " + taskId);
        verify(taskRepository, times(1)).findById(taskId);
    }

    @Test
    @DisplayName("Deve atualizar uma tarefa existente com sucesso")
    void shouldUpdateTaskSuccessfully() {
        // Arrange
        Long taskId = 1L;
        Task existingTask = new Task();
        existingTask.setId(taskId);
        existingTask.setTitle("Título Antigo");

        TaskRequestDTO updateDTO = new TaskRequestDTO(
                "Título Novo",
                "Descrição Nova",
                TaskStatus.IN_PROGRESS,
                TaskPriority.HIGH,
                LocalDate.now(),
                null
        );

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        TaskResponseDTO response = taskService.changeTask(taskId, updateDTO);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.title()).isEqualTo("Título Novo");
        assertThat(response.status()).isEqualTo(TaskStatus.IN_PROGRESS);

        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    @DisplayName("Deve deletar uma tarefa com sucesso")
    void shouldDeleteTaskSuccessfully() {
        // Arrange
        Long taskId = 1L;
        Task task = new Task();
        task.setId(taskId);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        doNothing().when(taskRepository).delete(task);

        // Act
        taskService.deleteTask(taskId);

        // Assert
        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).delete(task);
    }
}
