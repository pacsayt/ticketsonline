package springboot.ticketsonline.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springboot.ticketsonline.entities.EventPlace;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The “Testing with Spring Boot” Series,
 * Integration Tests vs. Unit Tests
 * https://reflectoring.io/spring-boot-test/
 *
 * Test Auto-configuration Annotations
 * https://reflectoring.io/spring-boot-test/
 */
@ExtendWith(SpringExtension.class) // pt++ : JUnit 5 : @ExtendWith(SpringExtension.class), JUnit 4 : @RunWith(SpringRunner.class)
@SpringBootTest  // pt++ : starts the whole container that is not always necessary, and can lead to time consuming tests
                 //        vs. : @WebMvcTest / @DataJpaTest
@DisplayName("A special test case")
public class EventPlaceTest
{
  @Autowired
  private EventPlaceService eventPlaceService;

  @BeforeAll
  static void initAll()
  {
  }

  @BeforeEach
  void init()
  {
  }

  // @Fast ???
  // @FastTest ???
  @Tag( "fast")
  @Test
  @DisplayName("@DisplayName : saveEventPlaceSucceeds()")
  public void testSaveEventPlaceSucceeds()
  {
    EventPlace eventPlaceToSave;
    EventPlace savedEventPlace;
    Long newId;

    eventPlaceToSave = new EventPlace( 111L, "Name_1", 10); // (Long iniID, String iniName, Integer iniNoOfSeats)
    savedEventPlace = eventPlaceService.save( eventPlaceToSave);
    System.out.println( "testSaveEventPlaceSucceeds() : savedEventPlace.iD=" + savedEventPlace.getiD());

    eventPlaceToSave = new EventPlace( 222L, "Name_2", 20); // (Long iniID, String iniName, Integer iniNoOfSeats);
    savedEventPlace = eventPlaceService.save( eventPlaceToSave);
    System.out.println( "testSaveEventPlaceSucceeds() : savedEventPlace.iD=" + savedEventPlace.getiD());

    List<EventPlace> allEventPlaces = eventPlaceService.findAll();

    System.out.println( "testSaveEventPlaceSucceeds() : allEventPlaces=" + Arrays.toString( allEventPlaces.toArray()));
  }

  @AfterEach
  void tearDown()
  {
  }

  @AfterAll
  static void tearDownAll()
  {
  }
}
