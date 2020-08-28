package springboot.ticketsonline.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springboot.ticketsonline.entities.EventPlace;

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
  public void saveEventPlaceSucceeds()
  {
    EventPlace eventPlaceToSave = new EventPlace( 12345L, "Name_1", 123); // (Long iniID, String iniName, Integer iniNoOfSeats)
    Long newId = eventPlaceService.saveEventPlace( eventPlaceToSave);

    assertTrue( newId != 0L);
    assertEquals( newId, 1L);
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
