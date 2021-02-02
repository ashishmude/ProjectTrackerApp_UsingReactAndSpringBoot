package com.service.projecttracker.model;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CheckpointTasks")
public class CheckpointTask {

  @Id
  @GeneratedValue
  private int id;
  @NotNull
  private String taskName;
  private String expectedCompletionDate;
  @NotNull
  private int checkpointId;
  private String comments;
  private boolean completed;

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public String getExpectedCompletionDate() {
    return expectedCompletionDate;
  }

  public void setExpectedCompletionDate(String expectedCompletionDate) {
    this.expectedCompletionDate = expectedCompletionDate;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public int getCheckpointId() {
    return checkpointId;
  }

  public void setCheckpointId(int checkpointId) {
    this.checkpointId = checkpointId;
  }

}
