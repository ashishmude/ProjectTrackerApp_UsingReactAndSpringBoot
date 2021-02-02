package com.service.projecttracker.repository;

import com.service.projecttracker.model.ProjectCheckpoint;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProjectCheckpointRepository extends CrudRepository<ProjectCheckpoint, Integer> {

  @Query(value = "SELECT pc FROM ProjectCheckpoint pc WHERE pc.projectId = :projectId")
  List<ProjectCheckpoint> findCheckpointsForProject(@Param("projectId") final int projectId);
}
