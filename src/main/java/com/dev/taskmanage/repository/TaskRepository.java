package com.dev.taskmanage.repository;

import com.dev.taskmanage.model.Task;
import com.dev.taskmanage.model.TaskPriority;
import com.dev.taskmanage.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Filtar tarefas pelo status
    List<Task> findByStatus(TaskStatus status);

    // Filtar tarefas pela prioridade
    List<Task> findByPriority(TaskPriority priority);
}
