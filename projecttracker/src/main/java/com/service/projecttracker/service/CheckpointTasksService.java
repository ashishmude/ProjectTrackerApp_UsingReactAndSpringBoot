package com.service.projecttracker.service;

import com.service.projecttracker.model.CheckpointTask;
import com.service.projecttracker.repository.CheckpointTasksRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckpointTasksService {

  private final CheckpointTasksRepository checkpointTasksRepository;

  @Autowired
  public CheckpointTasksService(CheckpointTasksRepository checkpointTasksRepository) {
    this.checkpointTasksRepository = checkpointTasksRepository;
  }

  public List<CheckpointTask> getCheckpointTasks(int checkpointId) {
    return checkpointTasksRepository.findTasksForCheckpoint(checkpointId);
  }
}
