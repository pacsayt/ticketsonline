package springboot.ticketsonline.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springboot.ticketsonline.entities.Event;
import springboot.ticketsonline.entities.EventPlace;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookedTicketTest
{
  private BookedTicketService ;

  @Test
  public void testSaveBookedTicket()
  {
    EventPlace eventPlaceToSave;
    EventPlace eventPlaceSaved;

    eventPlaceToSave = new EventPlace( 111L, "Name_1", 10); // (Long iniID, String iniName, Integer iniNoOfSeats)
    eventPlaceSaved = eventPlaceService.save( eventPlaceToSave);

    Event eventToSave = new Event( 0L, "EventName_55", stringToDate( "2020-09-03 11:32:41.00"), eventPlaceSaved);

    Event savedEvent = eventService.save( eventToSave);

  }
}
