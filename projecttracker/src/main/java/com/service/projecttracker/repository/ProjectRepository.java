package com.service.projecttracker.repository;

import com.service.projecttracker.model.Project;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends CrudRepository<Project, Integer> {

  Optional<Project> findProjectById(@Param("projectId") final int projectId);
}
