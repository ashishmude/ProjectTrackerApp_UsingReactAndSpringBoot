package com.service.projecttracker.service;

import com.service.projecttracker.model.CheckpointTask;
import com.service.projecttracker.model.ProjectCheckpoint;
import com.service.projecttracker.repository.CheckpointTasksRepository;
import com.service.projecttracker.repository.ProjectCheckpointRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectCheckpointService {

  private final ProjectCheckpointRepository projectCheckpointRepository;
  private final CheckpointTasksRepository checkpointTasksRepository;

  @Autowired
  public ProjectCheckpointService(ProjectCheckpointRepository projectCheckpointRepository,
      CheckpointTasksRepository checkpointTasksRepository) {
    this.projectCheckpointRepository = projectCheckpointRepository;
    this.checkpointTasksRepository = checkpointTasksRepository;
  }

  public List<ProjectCheckpoint> getProjectCheckpoints(int projectId) {
    List<ProjectCheckpoint> projectCheckpointList = projectCheckpointRepository
        .findCheckpointsForProject(projectId);
    return populateCheckpointTasksAndCompletionPercentage(projectCheckpointList);
  }

  private List<ProjectCheckpoint> populateCheckpointTasksAndCompletionPercentage(
      List<ProjectCheckpoint> projectCheckpointList) {
    //Here, I would have not preferred to call DB to fetch task list but to have separate API to
    //fetch the tasks for checkpoint.
    // But as requirement was to find out the percentage, I anyways had to find the details for
    // tasks and decided to populate task list as well here.*/
    return projectCheckpointList.stream().map(projectCheckpoint -> {
      List<CheckpointTask> taskList = checkpointTasksRepository
          .findTasksForCheckpoint(projectCheckpoint.getId());
      projectCheckpoint.setTaskList(taskList);
      BigDecimal completionPercentage = calculateCompletionPercentage(taskList);
      projectCheckpoint.setCompletionPercentage(completionPercentage);
      return projectCheckpoint;
    }).collect(Collectors.toList());
  }

  private BigDecimal calculateCompletionPercentage(List<CheckpointTask> tasksForCheckpoint) {
    long completedTasks = tasksForCheckpoint.stream()
        .filter(checkpointTask -> checkpointTask.isCompleted()).count();
    if (completedTasks == 0 || tasksForCheckpoint.size() == 0) {
      return BigDecimal.valueOf(0.0);
    }
    BigDecimal percentage = BigDecimal.valueOf(100.0 * completedTasks / tasksForCheckpoint.size())
        .setScale(2, RoundingMode.HALF_UP);
    return percentage;
  }
}
