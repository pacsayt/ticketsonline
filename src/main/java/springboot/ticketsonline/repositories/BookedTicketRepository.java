package springboot.ticketsonline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ticketsonline.entities.BookedTicket;

public interface BookedTicketRepository extends JpaRepository<BookedTicket, Long>
{
}