package se.iths.librarysystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import se.iths.librarysystem.dto.Task;
import se.iths.librarysystem.entity.TaskEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest(TaskService.class)
class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        TaskEntity taskEntity = new TaskEntity("980786985", 38L).setId(25L);
        Task task = new Task().setId(25L).setPath("/api/tasks/25").setStatus("pending");

        when(taskRepository.save(any(TaskEntity.class))).thenReturn(taskEntity);
        when(modelMapper.map(any(TaskEntity.class), eq(Task.class))).thenReturn(task);
    }

    @Test
    void createTaskShouldReturnCreatedTaskEntity() {
        var result = taskService.createTask(new TaskEntity());

        assertThat(result).isEqualTo(new TaskEntity("980786985", 38L).setId(25L));
    }


    @Test
    void getByIdShouldReturnTaskDTO() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(new TaskEntity()));

        var result = taskService.getById(25L);

        assertThat(result).isEqualTo(new Task().setId(25L).setPath("/api/tasks/25").setStatus("pending"));
    }

    @Test
    void getByIdShouldReturnErrorMessage() {
        when(taskRepository.findById(anyLong())).thenThrow(new IdNotFoundException("task", 25L));

        assertThatThrownBy(() -> taskService.getById(25L))
                .hasMessage("task with Id 25 not found.")
                .isInstanceOf(IdNotFoundException.class);

    }

    @Test
    void getAllTasksShouldReturnListOfTasks() {
        Iterable<TaskEntity> taskEntities = List.of(new TaskEntity(), new TaskEntity(), new TaskEntity());

        when(taskRepository.findAll()).thenReturn(taskEntities);

        var result = taskService.getAllTasks();

        assertThat(result).hasSize(3).contains(new Task().setId(25L).setPath("/api/tasks/25").setStatus("pending"));

    }
}