package com.service.projecttracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.service.projecttracker.model.Project;
import com.service.projecttracker.repository.ProjectRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectServiceTest {

  @Mock
  ProjectRepository projectRepository;
  private ProjectService projectService;

  @BeforeAll
  public void setUp() throws Exception {
    initMocks(this);
    projectService = new ProjectService(projectRepository);
  }

  public void mockDataForSuccess() {
    Project project = new Project();
    project.setId(1);
    project.setName("XYZ");
    when(projectRepository.findProjectById(1)).thenReturn(Optional.of(project));
  }

  @Test
  public void testGetProject() {
    mockDataForSuccess();
    Optional<Project> projectOptional = projectService.getProjectById(1);
    assertTrue(projectOptional.isPresent());
    assertEquals(1, projectOptional.get().getId());
    assertEquals("XYZ", projectOptional.get().getName());
  }

  @Test
  public void testGetProjectForNonAvailableProject() {
    mockDataForSuccess();
    Optional<Project> projectOptional = projectService.getProjectById(2);
    assertFalse(projectOptional.isPresent());
  }
}