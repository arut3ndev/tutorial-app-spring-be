package org.example.tutorialappspringbe.repositories;

import org.example.tutorialappspringbe.models.TaskItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskItemRepository extends JpaRepository<TaskItemModel, Integer> {
    
}
