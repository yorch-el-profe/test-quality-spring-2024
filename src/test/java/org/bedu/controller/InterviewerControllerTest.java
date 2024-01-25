package org.bedu.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.bedu.dto.CreateInterviewerDTO;
import org.bedu.dto.InterviewerDTO;
import org.bedu.service.InterviewerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class InterviewerControllerTest {

  @Autowired
  private InterviewerController controller;
  
  @MockBean
  private InterviewerService service;

  @Test
  @DisplayName("Controller should be injected")
  public void smoke() {
    assertNotNull(controller);
  }

  @Test
  @DisplayName("Controller should return a list of interviewers")
  public void findAll() {
    List<InterviewerDTO> data = new LinkedList<>();

    InterviewerDTO interviewer = new InterviewerDTO();

    interviewer.setId(1);
    interviewer.setEmail("john.doe@test.com");
    interviewer.setName("John Doe");

    data.add(interviewer);

    when(service.findAll()).thenReturn(data);

    List<InterviewerDTO> result = controller.findAll();

    assertEquals(data, result);
  }

  @Test
  @DisplayName("Controller should save and return the saved interviewer")
  public void save() {
    CreateInterviewerDTO data = new CreateInterviewerDTO();

    data.setName("John Doe");
    data.setEmail("john.doe@test.com");

    InterviewerDTO saved = new InterviewerDTO();

    saved.setId(1);
    saved.setName("John Doe");
    saved.setEmail("john.doe@test.com");

    when(service.save(any(CreateInterviewerDTO.class))).thenReturn(saved);

    InterviewerDTO result = controller.save(data);

    assertEquals(saved, result);
  }
}
