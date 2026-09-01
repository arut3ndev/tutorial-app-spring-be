package org.example.tutorialappspringbe.controllers;

import org.example.tutorialappspringbe.dtos.TaskItemDTO;
import org.example.tutorialappspringbe.models.TaskItemModel;
import org.example.tutorialappspringbe.services.TaskItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/taskItem")
public class TaskItemController {
    public final TaskItemService taskItemService;
    public TaskItemController(TaskItemService taskItemService){
        this.taskItemService = taskItemService;
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskItemDTO> getTaskItem(@PathVariable int taskId){
        Optional<TaskItemModel> taskItemModel = taskItemService.getTask(taskId);
        if(taskItemModel.isPresent()){
            return ResponseEntity.ok(new TaskItemDTO(taskItemModel.get()));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<TaskItemDTO>> getAllTaskItems(){
        List<TaskItemModel> taskItemModelList = taskItemService.getAllTasks();
        List<TaskItemDTO> taskItemDTOList = taskItemModelList.stream()
                .map(TaskItemDTO::new)
                .toList();
        return ResponseEntity.ok(taskItemDTOList);
    }

    @PostMapping
    public ResponseEntity<TaskItemDTO> postTaskItem(@RequestBody TaskItemDTO taskItemDTO) {
        TaskItemModel taskItemModel = new TaskItemModel(taskItemDTO);
        TaskItemModel saved = taskItemService.saveTask(taskItemModel);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(new TaskItemDTO(saved));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskItemDTO> putTaskItem(@PathVariable int taskId, @RequestBody TaskItemDTO taskItemDTO){
        Optional<TaskItemModel> existingTaskItemModel = taskItemService.getTask(taskId);
        if(existingTaskItemModel.isPresent()){
            TaskItemModel taskItemModel = existingTaskItemModel.get();
            taskItemModel.setTitle(taskItemDTO.getTitle());
            taskItemModel.setIsComplete(taskItemDTO.getIsComplete());
            taskItemModel.setCreatedAt(taskItemDTO.getCreatedAt());
            TaskItemModel saved = taskItemService.saveTask(taskItemModel);
            return ResponseEntity.ok().body(new TaskItemDTO(saved));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTaskItem(@PathVariable int taskId){
        if(taskItemService.taskExists(taskId)){
            taskItemService.deleteTask(taskId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}