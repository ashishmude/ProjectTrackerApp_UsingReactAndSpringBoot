package com.service.projecttracker.api;

import com.service.projecttracker.exception.ResourceNotFoundException;
import com.service.projecttracker.model.Project;
import com.service.projecttracker.model.ProjectCheckpoint;
import com.service.projecttracker.service.ProjectCheckpointService;
import com.service.projecttracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/project/{projectId}/checkpoints")
public class CheckpointController {

  private final ProjectCheckpointService projectCheckpointService;
  private final ProjectService projectService;

  @Autowired
  public CheckpointController(ProjectCheckpointService projectCheckpointService,
      ProjectService projectService) {
    this.projectCheckpointService = projectCheckpointService;
    this.projectService = projectService;
  }

  @GetMapping(value = "/")
  public List<ProjectCheckpoint> getProjectCheckpoints(@PathVariable final int projectId) {
    Optional<Project> projectOptional = projectService.getProjectById(projectId);
    if (!projectOptional.isPresent()) {
      throw new ResourceNotFoundException("No project exists with projectId=" + projectId);
    }
    final List<ProjectCheckpoint> projectCheckpointList = projectCheckpointService
        .getProjectCheckpoints(projectId);
    if (CollectionUtils.isEmpty(projectCheckpointList)) {
      throw new ResourceNotFoundException("No checkpoints available for projectId=" + projectId);
    }
    return projectCheckpointList;
  }

}
