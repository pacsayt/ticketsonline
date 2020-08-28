package springboot.ticketsonline.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import springboot.ticketsonline.entities.Ticket;

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

@Repository // @Service
public class TicketService
{
  private SessionFactory sessionFactory;

  @Autowired
  public TicketService(SessionFactory iniSessionFactory)
  {
    sessionFactory = iniSessionFactory;
  }

  public long saveTicket(Ticket ticketToBeSaved)
  {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    long ticketId = 0;

    try
    {
      transaction = session.beginTransaction();

      ticketId = (Long) session.save(ticketToBeSaved);

      ticketToBeSaved.setiD( ticketId); // pt++ : ???
    }
    catch ( HibernateException hibernateException)
    {
      if ( transaction != null )
      {
        transaction.rollback();
      }
    }
    finally
    {
      session.close();
    }

    return ticketId;
  }

  public List<Ticket> getAll()
  {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    List<Ticket> allTickets = Collections.emptyList();

    try
    {
      transaction.begin();

      allTickets = session.createQuery( "from tickets").list();

      transaction.commit();
    }
    catch( HibernateException hibernateException)
    {
      transaction.rollback();
    }
    finally
    {
      session.close();
    }

    return allTickets;
  }

  // pt++ : and the beat goes on ...
}