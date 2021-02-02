package com.service.projecttracker.service;

import com.service.projecttracker.model.Project;
import com.service.projecttracker.repository.ProjectRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

  private final ProjectRepository projectRepository;

  @Autowired
  public ProjectService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  public Optional<Project> getProjectById(int projectId) {
    return projectRepository.findProjectById(projectId);
  }

}
