package se.iths.librarysystem.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import se.iths.librarysystem.dto.Task;
import se.iths.librarysystem.entity.LoanTaskEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.repository.LoanTaskRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanTaskService {

    private final LoanTaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public LoanTaskService(LoanTaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    public LoanTaskEntity createTask(LoanTaskEntity loanTask) {
        return taskRepository.save(loanTask);
    }

    public void updateTask(LoanTaskEntity loanTask) {
        taskRepository.save(loanTask);
    }

    public Task getById(Long id) {
        LoanTaskEntity taskEntity = taskRepository.findById(id).orElseThrow(() -> new IdNotFoundException("task", id));
        return modelMapper.map(taskEntity, Task.class);
    }

    public List<Task> getAllTasks() {
        Iterable<LoanTaskEntity> taskEntities = taskRepository.findAll();
        List<Task> tasks = new ArrayList<>();
        taskEntities.forEach(taskEntity -> tasks.add(modelMapper.map(taskEntities, Task.class)));
        return tasks;
    }
}
