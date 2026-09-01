package org.example.tutorialappspringbe.services;

import org.example.tutorialappspringbe.models.TaskItemModel;
import org.example.tutorialappspringbe.repositories.TaskItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskItemService {
    TaskItemRepository taskItemRepository;

    public TaskItemService(TaskItemRepository taskItemRepository){
        this.taskItemRepository = taskItemRepository;
    }

    public Optional<TaskItemModel> getTask(int id){
        return taskItemRepository.findById(id);
    }

    public List<TaskItemModel> getAllTasks(){
        return taskItemRepository.findAll();
    }

    public TaskItemModel saveTask(TaskItemModel taskItemModel){
        return taskItemRepository.save(taskItemModel);
    }

    public void deleteTask(int id){
       taskItemRepository.deleteById(id);
    }

    public boolean taskExists(int id){
        return taskItemRepository.existsById(id);
    }

}
