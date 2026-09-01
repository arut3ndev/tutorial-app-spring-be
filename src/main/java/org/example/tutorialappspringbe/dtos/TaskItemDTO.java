package org.example.tutorialappspringbe.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.tutorialappspringbe.models.TaskItemModel;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskItemDTO {
    private Integer id;
    private String title;
    private Boolean isComplete;
    private LocalDateTime createdAt;

    public TaskItemDTO(TaskItemModel itemModel){
        this.setId(itemModel.getId());
        this.setTitle(itemModel.getTitle());
        this.setIsComplete(itemModel.getIsComplete());
        this.setCreatedAt(itemModel.getCreatedAt());
    }
}
