package se.iths.librarysystem.service;

import org.springframework.stereotype.Service;
import se.iths.librarysystem.entity.LoanTaskEntity;
import se.iths.librarysystem.repository.LoanTaskRepository;

@Service
public class LoanTaskService {

    private final LoanTaskRepository taskRepository;

    public LoanTaskService(LoanTaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public LoanTaskEntity createTask(LoanTaskEntity loanTask) {
        return taskRepository.save(loanTask);
    }

    public void updateTask(LoanTaskEntity loanTask) {
        taskRepository.save(loanTask);
    }
}
