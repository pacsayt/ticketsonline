package springboot.ticketsonline.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// pt++ : some third party stuff, not JUnit
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import springboot.ticketsonline.entities.EventPlace;

import java.util.List;

/**
 *  The Guide to RestTemplate
 *  https://www.baeldung.com/rest-template
 *
 *  it's a good idea to use WebClient.
 *  Moving forward, RestTemplate will be deprecated in future versions. pt++ " !!!
 *
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // pt++ : @WebMvcTest
public class EventPlaceControllerTest
{
  @LocalServerPort
  private int portNumber; // pt++ : annotated primitive type ...

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  public void testFindById()
  {
    EventPlace eventPlaceExpected;

    eventPlaceExpected = new EventPlace( 11L, "Name_11", 11); // (Long iniID, String iniName, Integer iniNoOfSeats)

    ResponseEntity<EventPlace> responseEntity = testRestTemplate.getForEntity("http://localhost:" + portNumber + "/eventplace/11", EventPlace.class);

//    List<EventPlace> eventPlacesFound = testRestTemplate.getForObject("http://localhost:" + portNumber + "/eventplace/11", List.class); // pt++ : EventPlace.class
    assertThat( responseEntity.getStatusCode(), equalTo( HttpStatus.OK));

    assertThat( responseEntity., is( eventPlaceExpected));
  }

  @Test
  public void testFindAll()
  {
    EventPlace eventPlaceExpected;

    eventPlaceExpected = new EventPlace( 11L, "Name_11", 11); // (Long iniID, String iniName, Integer iniNoOfSeats)

    ResponseEntity<List> responseEntity = testRestTemplate.getForEntity("http://localhost:" + portNumber + "/eventplace", List.class);

    List<EventPlace> eventPlacesFound = testRestTemplate.getForObject("http://localhost:" + portNumber + "/eventplace", List.class); // pt++ : EventPlace.class

    assertThat( eventPlacesFound, is( eventPlaceExpected));
  }
}