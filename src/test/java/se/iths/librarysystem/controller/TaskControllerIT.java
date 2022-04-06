package se.iths.librarysystem.controller;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.librarysystem.dto.Task;
import se.iths.librarysystem.entity.TaskEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.mocks.WithMockAdmin;
import se.iths.librarysystem.repository.TaskRepository;
import se.iths.librarysystem.security.SecurityConfig;
import se.iths.librarysystem.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Import({TaskService.class, SecurityConfig.class})
@WebMvcTest(TaskController.class)
@AutoConfigureMockMvc
class TaskControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private ModelMapper modelMapper;

    @WithMockUser
    @Test
    void whenNonAdminUserGetAllTasksShouldReturnStatus403() throws Exception {
        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isForbidden());
    }

    @WithMockAdmin
    @Test
    void whenAdminGetAllTasksShouldReturnStatus200AndListOfTasks() throws Exception {
        Iterable<TaskEntity> taskEntities = List.of(new TaskEntity(), new TaskEntity());
        LocalDateTime dateTime = LocalDateTime.of(2022, 4, 6, 10, 42, 25);
        Task task = new Task("/api/task/1", "completed", true, true, dateTime);
        task.setId(1L);

        when(taskRepository.findAll()).thenReturn(taskEntities);
        when(modelMapper.map(any(TaskEntity.class), eq(Task.class))).thenReturn(task);

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].path", is("/api/task/1")))
                .andExpect(jsonPath("$[0].success", is(true)))
                .andExpect(jsonPath("$[0].completed", is(true)))
                .andExpect(jsonPath("$[0].status", is("completed")));
        verify(taskRepository).findAll();
    }

    @WithMockUser
    @Test
    void whenRoleIsUserGetTaskByIdShouldReturnStatus200AndTasks() throws Exception {
        LocalDateTime dateTime = LocalDateTime.of(2022, 4, 6, 10, 42, 25);
        Optional<TaskEntity> taskEntity = Optional.of(new TaskEntity("90876542345", 35L));

        Task task = new Task("/api/task/1", "completed", true, true, dateTime);
        task.setId(1L);

        when(taskRepository.findById(any(Long.class))).thenReturn(taskEntity);
        when(modelMapper.map(any(TaskEntity.class), eq(Task.class))).thenReturn(task);

        mockMvc.perform(get("/api/tasks/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.completed", is(true)))
                .andExpect(jsonPath("$.status", is("completed")))
                .andExpect(jsonPath("$.registered", is("06-04-2022 10:42:25")));
        verify(taskRepository).findById(1L);
    }

    @WithMockUser
    @Test
    void getTaskByIdNotShouldThrowException() throws Exception {
        when(taskRepository.findById(any(Long.class))).thenThrow(new IdNotFoundException("task", 101L));

        mockMvc.perform(get("/api/tasks/{id}", 101))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("task with Id 101 not found.")))
                .andExpect(jsonPath("$.path", is("/api/tasks/101")));
    }

    @WithAnonymousUser
    @Test
    void whenAnonymousUserGetTaskByIdNotShouldReturn401() throws Exception {
        mockMvc.perform(get("/api/tasks/{id}", 101))
                .andExpect(status().isUnauthorized());
    }

}