package springboot.ticketsonline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ticketsonline.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>
{
}
