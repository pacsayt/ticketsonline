package springboot.ticketsonline.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springboot.ticketsonline.entities.EventPlace;

import java.util.Collections;
import java.util.List;

@Repository
public class EventPlaceService
{
  private SessionFactory sessionFactory;

  @Autowired
  public EventPlaceService(SessionFactory iniSessionFactory)
  {
    sessionFactory = iniSessionFactory;
  }

  public Long saveEventPlace(EventPlace eventPlaceToSave)
  {
    Session session = sessionFactory.openSession();

    Transaction transaction = null;

    long eventPlaceId = 0;

    try
    {
      transaction = session.beginTransaction();

      eventPlaceId = (Long) session.save( eventPlaceToSave);

      eventPlaceToSave.setiD( eventPlaceId); // pt++ : ???
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

    return eventPlaceId;
  }

  public List<EventPlace> getAll()
  {
    Session session = sessionFactory.openSession();

    Transaction transaction = null;

    List<EventPlace> allEventPlace = Collections.emptyList();

    try
    {
      transaction = session.beginTransaction();

      allEventPlace = session.createQuery( "from eventplaces").list();

      transaction.commit();
    }
    catch( HibernateException hibernateException)
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

    return allEventPlace;
  }
}
