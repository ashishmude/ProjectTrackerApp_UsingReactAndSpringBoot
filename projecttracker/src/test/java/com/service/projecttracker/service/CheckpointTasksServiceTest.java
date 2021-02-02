package com.service.projecttracker.service;

import com.service.projecttracker.model.CheckpointTask;
import com.service.projecttracker.repository.CheckpointTasksRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CheckpointTasksServiceTest {

  @Mock
  CheckpointTasksRepository checkpointTasksRepository;
  private CheckpointTasksService checkpointTasksService;

  @BeforeAll
  public void setUp() throws Exception {
    initMocks(this);
    checkpointTasksService = new CheckpointTasksService(checkpointTasksRepository);
  }

  public void mockDataForSuccess() {
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

    when(checkpointTasksRepository.findTasksForCheckpoint(1))
        .thenReturn(Arrays.asList(cpTask1, cpTask2));
  }

  @Test
  public void testGetCheckpointTasks() {
    mockDataForSuccess();
    List<CheckpointTask> checkpointTaskList = checkpointTasksService.getCheckpointTasks(1);
    assertNotNull(checkpointTaskList);
    assertEquals(2, checkpointTaskList.size());
    assertFalse(checkpointTaskList.get(0).isCompleted());
    assertTrue(checkpointTaskList.get(1).isCompleted());
  }

  @Test
  public void testGetCheckpointTasksForNonAvailableCheckpoint() {
    mockDataForSuccess();
    List<CheckpointTask> checkpointTaskList = checkpointTasksService.getCheckpointTasks(2);
    assertNotNull(checkpointTaskList);
    assertEquals(0, checkpointTaskList.size());
  }
}