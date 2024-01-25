package org.bedu.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.bedu.model.Interviewer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class InterviewerRepositoryTest {
  
  @Autowired
  private InterviewerRepository repository;

  @Autowired
  private TestEntityManager manager;

  @Test
  @DisplayName("Repository should be injected")
  public void smoke() {
    assertNotNull(repository);
  }

  @Test
  @DisplayName("Repository should retrieve all interviewers")
  public void findAll() {
    Interviewer interviewer = new Interviewer();

    interviewer.setName("John Doe");
    interviewer.setEmail("john.doe@test.com");

    manager.persist(interviewer);

    List<Interviewer> result = repository.findAll();

    assertEquals(1, result.size());
    assertEquals(interviewer, result.get(0));
  }

  @Test
  @DisplayName("Repository should save an interviewer")
  public void save() {
    Interviewer interviewer = new Interviewer();

    interviewer.setName("John Doe");
    interviewer.setEmail("john.doe@test.com");

    Interviewer result = repository.save(interviewer);

    assertNotNull(result);
  }
}
