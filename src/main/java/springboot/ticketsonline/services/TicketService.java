package springboot.ticketsonline.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.ticketsonline.entities.EventPlace;
import springboot.ticketsonline.entities.Ticket;
import springboot.ticketsonline.repositories.TicketRepository;

import java.util.Collections;
import java.util.List;

/**
 * pt++ : https://www.baeldung.com/spring-component-repository-service
 *
 * @Component is a generic stereotype for any Spring-managed component
 * @Service annotates classes at the service layer
 * @Repository annotates classes at the persistence layer, which will act as a database repository
 *
 */

@Service
public class TicketService
{
  @Autowired
  private TicketRepository ticketRepository;

  public Long count()
  {
    return ticketRepository.count();
  }

  public Ticket save(Ticket ticketToBeSaved)
  {
    Ticket savedTicket = ticketRepository.save( ticketToBeSaved);

    return savedTicket;
  }
  public Ticket getOne(Long iD)
  {
    return ticketRepository.getOne( iD);
  }

  public List<Ticket> findAll()
  {
    return ticketRepository.findAll();
  }
}