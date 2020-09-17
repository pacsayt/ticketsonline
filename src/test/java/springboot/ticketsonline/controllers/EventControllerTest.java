package springboot.ticketsonline.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import springboot.ticketsonline.repositories.EventRepository;
import springboot.ticketsonline.services.EventService;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Perform test here using MockWebMvc for a change :
 * https://dimitr.im/testing-your-rest-controllers-and-clients-with-spring
 *
 * https://dimitr.im/testing-your-rest-controllers-and-clients-with-spring
 *
 *
 * https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-rest-api/
 *
 * https://reflectoring.io/spring-boot-web-controller-test/
 *
 * Command line test :
 * GET http://localhost:8080/event/11
 * http GET http://localhost:8080/event/11
 */

/**
 *  As of Spring Boot 2.1, we no longer need to load the SpringExtension because it's included as a
 *  meta annotation in the Spring Boot test annotations like @DataJpaTest, @WebMvcTest, and @SpringBootTest.
 *
 */

@ExtendWith(SpringExtension.class) // pt++ :  As of Spring Boot 2.1, we no longer need to load the SpringExtension
                                   // because it's included as a meta annotation in the Spring Boot test
                                   //  annotations like @DataJpaTest, @WebMvcTest, and @SpringBootTest.
@WebMvcTest( controllers = EventController.class) // pt++ : controllers - all other controllers will be omitted from injector
public class EventControllerTest                  // pt++ : -> @MockBean - for those not in injector and not under test
                                                  // pt++ : however, Spring Boot has to create a new application context for each single test
{
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private EventService mockEventService;

  @MockBean
  private EventRepository mockEventRepository;

  @Test
  public void testHTTPRequestMatching() throws Exception
  {
    // pt++ : Verifying HTTP Request Matching
//    mockMvc.perform( post("/event").contentType( MediaType.APPLICATION_JSON))
//                                             .andExpect( status().isOk());

    mockMvc.perform( get("/event/11").contentType( "application/json")).andExpect(status().isOk());
  }


}