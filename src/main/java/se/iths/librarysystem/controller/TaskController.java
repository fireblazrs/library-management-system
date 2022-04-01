package se.iths.librarysystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.librarysystem.dto.Task;
import se.iths.librarysystem.service.LoanTaskService;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

    private final LoanTaskService taskService;

    public TaskController(LoanTaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> getTaskById (@PathVariable Long id) {
        Task task = taskService.getById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

}
