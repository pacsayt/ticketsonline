package springboot.ticketsonline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ticketsonline.entities.Event;
import springboot.ticketsonline.entities.Ticket;

import java.util.List;

/**
 * pt++ :
 * Derived Queries with Spring Data JPA – The Ultimate Guide
 * https://thorben-janssen.com/ultimate-guide-derived-queries-with-spring-data-jpa/
 *
 * Hibernate Logging Guide – Use the right config for development and production
 * https://thorben-janssen.com/hibernate-logging-guide/
 */
public interface TicketRepository extends JpaRepository<Ticket, Long>
{
  List<Ticket> findByTicketPrice(Integer ticketPrice);

  List<Ticket> findByTicketPriceLessThan(Integer ticketPrice);

  List<Ticket> findByTicketPriceBetween(Integer minPrice, Integer maxPrice);

  List<Ticket> findByEventAndTicketPrice(Event event, Integer ticketPrice); // pt++ : referenced object in query param

  List<Ticket> findByEventName( String name); // pt++ : referenced object in query param
}
