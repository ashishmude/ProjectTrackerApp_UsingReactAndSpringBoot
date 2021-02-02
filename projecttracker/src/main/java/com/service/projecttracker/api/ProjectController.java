package com.service.projecttracker.api;

import com.service.projecttracker.exception.ResourceNotFoundException;
import com.service.projecttracker.model.Project;
import com.service.projecttracker.service.ProjectService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/project/{projectId}")
public class ProjectController {

  private final ProjectService projectService;

  @Autowired
  public ProjectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @GetMapping(value = {"", "/"})
  public Project getProject(@PathVariable final int projectId) {
    final Optional<Project> projectOptional = projectService.getProjectById(projectId);
    if (!projectOptional.isPresent()) {
      throw new ResourceNotFoundException("No project exists with projectId=" + projectId);
    }
    return projectOptional.get();
  }

}
