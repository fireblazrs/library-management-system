package se.iths.librarysystem.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import se.iths.librarysystem.dto.Task;
import se.iths.librarysystem.entity.TaskEntity;
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

    public TaskEntity createTask(TaskEntity loanTask) {
        return taskRepository.save(loanTask);
    }

    public void updateTask(TaskEntity loanTask) {
        taskRepository.save(loanTask);
    }

    public Task getById(Long id) {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(() -> new IdNotFoundException("task", id));
        return modelMapper.map(taskEntity, Task.class);
    }

    public List<Task> getAllTasks() {
        Iterable<TaskEntity> taskEntities = taskRepository.findAll();
        List<TaskEntity> taskEntityList = new ArrayList<>();
        taskEntities.forEach(taskEntityList::add);
        return taskEntityList.stream().map(task -> modelMapper.map(task, Task.class)).toList();
    }
}
