package springboot.ticketsonline.services;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springboot.ticketsonline.entities.Event;
import springboot.ticketsonline.entities.EventPlace;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class) // pt++ : JUnit 5 : @ExtendWith(SpringExtension.class), JUnit 4 : @RunWith(SpringRunner.class)
@SpringBootTest
@DisplayName("Event test cases")
public class EventServiceTest extends TestBase
{
  @Autowired
  private EventPlaceService eventPlaceService;

  @Autowired
  private EventService eventService;

  @Autowired
  private TicketService ticketService;

  @BeforeAll
  public static void beforeAll()
  {
  }

  @BeforeEach
  public void beforeEach()
  {
  }

  @Test
  public void testFindingOneByExistingId()
  {
    Optional<Event> optionalEvent = eventService.findById( 11L);

    assertTrue( optionalEvent.isPresent());

    Event eventFound = optionalEvent.get();

    assertEquals( eventFound.getName(), "EventName_11");
  }

  @Test
  public void testFindOneNotExisting()
  {
    Optional<Event> optionalEvent = eventService.findById( 0L);

    assertFalse( optionalEvent.isPresent());
  }


  @Test
  public void testFindAll()
  {
    List<Event> eventFound = eventService.findAll();

    assertTrue( eventFound.size() == 4);
  }

  @Test
  public void testSave() throws ParseException
  {
    EventPlace eventPlaceToSave;
    EventPlace eventPlaceSaved;

    eventPlaceToSave = new EventPlace( 111L, "Name_1", 10); // (Long iniID, String iniName, Integer iniNoOfSeats)
    eventPlaceSaved = eventPlaceService.save( eventPlaceToSave);

    Event eventToSave = new Event( 0L, "EventName_55", stringToDate( "2020-09-03 11:32:41.00"), eventPlaceSaved);

    Event savedEvent = eventService.save( eventToSave);

    assertTrue( savedEvent.getId() != 0L);
  }

  @Test
  public void testDeletionNonExistingEvent() // pt++ : it seems, it goes OK with not existing Ids
  {
    try
    {
      EventPlace eventPlaceToSave = new EventPlace( 0L, "Name_1", 10); // (Long iniID, String iniName, Integer iniNoOfSeats)

      Event eventToBeDeleted = new Event( 0L, "EventName_55", stringToDate( "2020-09-03 11:32:41.00"), eventPlaceToSave);

      eventService.delete(eventToBeDeleted);
    }
    catch( Exception exception)
    {
      fail( exception);
    }
  }

  @Test
  public void testDeleteExistingEvent() // pt++ : will this delete the EventPlace as well ?
  {
    Optional<Event> optionalEvent = eventService.findById( 22L);

    assertTrue( optionalEvent.isPresent());

    eventService.delete( optionalEvent.get());

    Optional<Event> optionalJustDeletedEvent = eventService.findById( 22L);

    assertFalse( optionalJustDeletedEvent.isPresent());

    Optional<EventPlace> optionalEventPlace = eventPlaceService.findById( 22L);

    assertTrue( optionalEventPlace.isPresent());
  }

  @Test
  public void testDeletionOfEventWhereTicketsExist() throws ParseException
  {
    Event eventToBeDeleted = new Event( 11L, "not existent", stringToDate( "2020-09-03 11:32:41.00"), null);

    Long countTicketBefore = ticketService.count();

    eventService.delete( eventToBeDeleted);

    Long countTicketAfter = ticketService.count();

    System.out.println( "testDeletionOfEventWhereTicketsExist() : countTicketBefore=" + countTicketBefore + " countTicketAfter=" + countTicketAfter);
  }
}