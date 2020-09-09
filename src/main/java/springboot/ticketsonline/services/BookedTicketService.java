package springboot.ticketsonline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.ticketsonline.entities.BookedTicket;
import springboot.ticketsonline.entities.Event;
import springboot.ticketsonline.entities.Ticket;
import springboot.ticketsonline.repositories.BookedTicketRepository;
import springboot.ticketsonline.repositories.EventRepository;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookedTicketService
{
  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private TicketService ticketService;

  @Autowired
  private BookedTicketRepository bookedTicketRepository;

  public Long count()
  {
    return bookedTicketRepository.count();
  }

  public BookedTicket save( BookedTicket bookedTicketToSave)
  {
    return bookedTicketRepository.save( bookedTicketToSave);
  }

  public Optional<BookedTicket> findById( Long iD)
  {
    Optional<BookedTicket> bookedTicketOptional = bookedTicketRepository.findById( iD);

    return bookedTicketOptional;
  }

  public List<BookedTicket> findAll()
  {
    return bookedTicketRepository.findAll();
  }

  public List<BookedTicket> findByBookedTicketEvent( Event event)
  {
    return bookedTicketRepository.findByBookedTicketEvent( event);
  }

  public Integer findAvailableTickets( String name, Timestamp date) // pt++ : some useful functionality to be implemented here
  {
    Integer noOfFreSeatsForTheEvent = 0;

    Optional<Event> optionalEventOnADay = eventRepository.findByNameAndDate( name, date);

    if ( optionalEventOnADay.isPresent() )
    {
// ??? List<Ticket> listTicketsForTheEvent = ticketService.findByEvent( optionalEventOnADay.get());
      Event matchingEvent = optionalEventOnADay.get();
      List<BookedTicket> bookedTicketsForTheEvent = bookedTicketRepository.findByBookedTicketEvent( matchingEvent);

      noOfFreSeatsForTheEvent = matchingEvent.getEventPlace().getNoOfSeats() - bookedTicketsForTheEvent.size();
    }

    return noOfFreSeatsForTheEvent;
  }

  public void deleteById( Long iD)
  {
    bookedTicketRepository.deleteById( iD);
  }

  // pt++ : what is something similar
  public void delete( BookedTicket bookedTicketToBeDeleted)
  {
    bookedTicketRepository.delete( bookedTicketToBeDeleted);
  }
}
