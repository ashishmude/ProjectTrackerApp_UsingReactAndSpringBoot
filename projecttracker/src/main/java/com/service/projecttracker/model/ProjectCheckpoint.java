package com.service.projecttracker.model;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "project_checkpoints")
public class ProjectCheckpoint {

  @Id
  private int id;
  @NotNull
  private String checkpointName;
  private String description;
  @NotNull
  private int projectId;
  private BigDecimal completionPercentage;
  @OneToMany
  private List<CheckpointTask> taskList;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCheckpointName() {
    return checkpointName;
  }

  public void setCheckpointName(String checkpointName) {
    this.checkpointName = checkpointName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getProjectId() {
    return projectId;
  }

  public void setProjectId(int projectId) {
    this.projectId = projectId;
  }

  public BigDecimal getCompletionPercentage() {
    return completionPercentage;
  }

  public void setCompletionPercentage(BigDecimal completionPercentage) {
    this.completionPercentage = completionPercentage;
  }

  public List<CheckpointTask> getTaskList() {
    return taskList;
  }

  public void setTaskList(List<CheckpointTask> taskList) {
    this.taskList = taskList;
  }
}

