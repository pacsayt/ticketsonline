package springboot.ticketsonline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ticketsonline.entities.BookedTicket;
import springboot.ticketsonline.entities.Event;
import springboot.ticketsonline.entities.Ticket;

import java.util.List;

public interface BookedTicketRepository extends JpaRepository<BookedTicket, Long>
{
  List<BookedTicket> findByBookedTicketEvent(Event event);
}