package com.service.projecttracker;

import com.service.projecttracker.model.Project;
import com.service.projecttracker.model.ProjectCheckpoint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProjectTrackerApplicationTests {


  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void contextLoads() {
  }

  @LocalServerPort
  private int port;

  private String getRootUrl() {
    return "http://localhost:" + port + "/project";
  }

  @Test
  public void testGetAllCheckpoints() {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> entity = new HttpEntity<>(null, headers);
    ResponseEntity<List<ProjectCheckpoint>> projectCheckpointListResponse =
        restTemplate.exchange(getRootUrl() + "/1/checkpoints/", HttpMethod.GET, entity,
            new ParameterizedTypeReference<>() {
            });
    List<ProjectCheckpoint> projectCheckpointList = projectCheckpointListResponse.getBody();

    assertNotNull(projectCheckpointListResponse.getBody());
    assertNotNull(projectCheckpointList);
    assertEquals(projectCheckpointListResponse.getStatusCode(), HttpStatus.OK);
    assertEquals(4, projectCheckpointList.size());
    assertEquals(4, projectCheckpointList.get(0).getTaskList().size());
    assertEquals(3, projectCheckpointList.get(1).getTaskList().size());
    assertEquals(6, projectCheckpointList.get(2).getTaskList().size());
    assertEquals(2, projectCheckpointList.get(3).getTaskList().size());
  }

  @Test
  public void testGetAllCheckpointsForNonExistentProject() {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> entity = new HttpEntity<>(null, headers);

    ResponseEntity<Exception> response = restTemplate
        .exchange(getRootUrl() + "/2/checkpoints/", HttpMethod.GET, entity, Exception.class);
    assertNotNull(response.getBody());
    assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
  }

  @Test
  public void testGetProject() {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> entity = new HttpEntity<>(null, headers);
    ResponseEntity<Project> projectResponse =
        restTemplate.exchange(getRootUrl() + "/1/", HttpMethod.GET, entity, Project.class);

    assertNotNull(projectResponse.getBody());
    assertEquals(projectResponse.getStatusCode(), HttpStatus.OK);
    assertEquals(1, projectResponse.getBody().getId());
    assertEquals("Project - XYZ", projectResponse.getBody().getName());
  }

  @Test
  public void testGetProjectForNonExistentProject() {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> entity = new HttpEntity<>(null, headers);
    ResponseEntity<Exception> response = restTemplate
        .exchange(getRootUrl() + "/2/", HttpMethod.GET, entity, Exception.class);
    assertNotNull(response.getBody());
    assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
  }


}
