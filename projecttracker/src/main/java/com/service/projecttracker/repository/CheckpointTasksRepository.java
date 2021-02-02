package com.service.projecttracker.repository;

import com.service.projecttracker.model.CheckpointTask;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CheckpointTasksRepository extends CrudRepository<CheckpointTask, Integer> {

  @Query(value = "SELECT pc FROM CheckpointTask pc WHERE pc.checkpointId = :checkpointId")
  List<CheckpointTask> findTasksForCheckpoint(@Param("checkpointId") final int checkpointId);

}
