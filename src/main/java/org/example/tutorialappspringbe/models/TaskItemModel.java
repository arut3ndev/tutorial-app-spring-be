package org.example.tutorialappspringbe.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.tutorialappspringbe.dtos.TaskItemDTO;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskItemModel {
    @Id()
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private Boolean isComplete;
    private LocalDateTime createdAt;

    public TaskItemModel(TaskItemDTO taskItemDTO){
        this.setTitle(taskItemDTO.getTitle());
        this.setIsComplete(taskItemDTO.getIsComplete());
        this.setCreatedAt(taskItemDTO.getCreatedAt() != null ? taskItemDTO.getCreatedAt() : LocalDateTime.now());
    }
}
