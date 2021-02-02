package com.service.projecttracker.service;

import com.service.projecttracker.model.CheckpointTask;
import com.service.projecttracker.model.ProjectCheckpoint;
import com.service.projecttracker.repository.CheckpointTasksRepository;
import com.service.projecttracker.repository.ProjectCheckpointRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectCheckpointServiceTest {

  @Mock
  ProjectCheckpointRepository projectCheckpointRepository;
  @Mock
  CheckpointTasksRepository checkpointTasksRepository;

  private ProjectCheckpointService projectCheckpointService;

  @BeforeAll
  public void setUp() throws Exception {
    initMocks(this);
    projectCheckpointService = new ProjectCheckpointService(projectCheckpointRepository,
        checkpointTasksRepository);
  }

  private ProjectCheckpoint GetMockedProjectCheckpoint() {
    ProjectCheckpoint projectCheckpoint1 = new ProjectCheckpoint();
    projectCheckpoint1.setId(1);
    projectCheckpoint1.setCheckpointName("Requirement Gathering");
    projectCheckpoint1.setProjectId(1);
    return projectCheckpoint1;
  }

  public void mockDataForSuccess() {
    ProjectCheckpoint projectCheckpoint1 = GetMockedProjectCheckpoint();

    CheckpointTask cpTask1 = new CheckpointTask();
    cpTask1.setId(1);
    cpTask1.setCompleted(false);
    cpTask1.setCheckpointId(1);
    cpTask1.setExpectedCompletionDate("TBD");

    CheckpointTask cpTask2 = new CheckpointTask();
    cpTask2.setId(2);
    cpTask2.setCompleted(true);
    cpTask2.setCheckpointId(1);
    cpTask2.setExpectedCompletionDate("Completed");

    when(projectCheckpointRepository.findCheckpointsForProject(1))
        .thenReturn(Arrays.asList(projectCheckpoint1));
    when(checkpointTasksRepository.findTasksForCheckpoint(1))
        .thenReturn(Arrays.asList(cpTask1, cpTask2));
  }

  public void mockDataForNoTasksForCheckpoint() {
    ProjectCheckpoint projectCheckpoint1 = GetMockedProjectCheckpoint();
    when(projectCheckpointRepository.findCheckpointsForProject(1))
        .thenReturn(Arrays.asList(projectCheckpoint1));
    when(checkpointTasksRepository.findTasksForCheckpoint(1)).thenReturn(new ArrayList<>());
  }

  @Test
  public void testGetProjectCheckpoints() {
    mockDataForSuccess();
    List<ProjectCheckpoint> projectCheckpointList = projectCheckpointService
        .getProjectCheckpoints(1);
    assertNotNull(projectCheckpointList);
    assertEquals(1, projectCheckpointList.size());
    assertEquals(2, projectCheckpointList.get(0).getTaskList().size());
    assertEquals("50.00", projectCheckpointList.get(0).getCompletionPercentage().toString());
  }

  @Test
  public void testGetProjectCheckpointsForNoTasks() {
    mockDataForNoTasksForCheckpoint();
    List<ProjectCheckpoint> projectCheckpointList = projectCheckpointService
        .getProjectCheckpoints(1);
    assertNotNull(projectCheckpointList);
    assertEquals(1, projectCheckpointList.size());
    assertEquals(0, projectCheckpointList.get(0).getTaskList().size());
    assertEquals(BigDecimal.valueOf(0.0), projectCheckpointList.get(0).getCompletionPercentage());
  }

  @Test
  public void testGetProjectCheckpointsForNoAvailableCheckpoint() {
    mockDataForNoTasksForCheckpoint();
    List<ProjectCheckpoint> projectCheckpointList = projectCheckpointService
        .getProjectCheckpoints(2);
    assertNotNull(projectCheckpointList);
    assertEquals(0, projectCheckpointList.size());
  }
}