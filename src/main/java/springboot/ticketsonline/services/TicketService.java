package springboot.ticketsonline.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.ticketsonline.entities.Event;
import springboot.ticketsonline.entities.EventPlace;
import springboot.ticketsonline.entities.Ticket;
import springboot.ticketsonline.repositories.TicketRepository;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * pt++ : https://www.baeldung.com/spring-component-repository-service
 *
 * @Component is a generic stereotype for any Spring-managed component
 * @Service annotates classes at the service layer
 * @Repository annotates classes at the persistence layer, which will act as a database repository
 *
 * Guide to the Hibernate EntityManager
 * https://www.baeldung.com/hibernate-entitymanager
 * @PersistenceContext
 */

@Service
public class TicketService
{
  @Autowired // pt++ : @PersistenceContext( type = ...)
             // PersistenceContextType.TRANSACTION - EntityManager to use the transaction persistence context
             // PersistenceContextType.EXTENDED -
             // injector -> the container is in charge of beginning the transaction, as well as committing or rolling
             //             it back
  private EntityManager entityManager;

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

  public List<Ticket> findAll()
  {
    return ticketRepository.findAll();
  }

  public Optional<Ticket> findById(Long iD)
  {
    return ticketRepository.findById( iD);
  }

  public List<Ticket> findByEvent(Event event)
  {
    return ticketRepository.findByEvent( event);
  }

  // pt++ : not sure if this is useful as event name is with date unique only
  public List<Ticket> findByEventName( String name)
  {
    return ticketRepository.findByEventName( name);
  }

  public void delete( Ticket ticketToBeDeleted)
  {
    ticketRepository.delete( ticketToBeDeleted);
  }

  public EntityManager getEntityManager()
  {
    return entityManager;
  }

  public Ticket getOne(Long iD)
  {
    return ticketRepository.getOne( iD);
  }
}