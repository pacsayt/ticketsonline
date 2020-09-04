package springboot.ticketsonline.services;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.ticketsonline.entities.BookedTicket;
import springboot.ticketsonline.repositories.BookedTicketRepository;

public class BookedTicketService
{
  @Autowired
  private BookedTicketRepository bookedTicketRepository;

  public BookedTicket save( BookedTicket bookedTicketToSave)
  {
    
  }
}
