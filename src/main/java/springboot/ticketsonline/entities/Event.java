package springboot.ticketsonline.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * called persistent classes in Hibernate
 *
 * rules of persistent classes (no hard requirements)
 * - default constructor
 * - contain an ID
 * - getXXX()/setXXX() - JavaBean style
 * - central feature of Hibernate, proxies, depends upon the persistent class being either non-final,
 *   or the implementation of an interface that declares all public methods.
 * - no EJB  
 */
@Entity
public class Event
{
  @Id
  private Long iD;
  private String name;
  private Date date;
  private EventPlace eventPlace;

  public Event()
  {
  }

  public Event(Long iniID, String iniName, Date iniDate, EventPlace iniEventPlace)
  {
    iD = iniID;
    name = iniName;
    date = iniDate;
    eventPlace = iniEventPlace;
  }

  @Id // pt++ : should this annotation be added here or to the member definition ?
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  public Long getId() {
    return iD;
  }

  public void setiD(Long iniId)
  {
    iD = iniId;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String iniName)
  {
    name = iniName;
  }

  public Date getDate()
  {
    return date;
  }

  public void setDate(Date iniDate)
  {
    date = iniDate;
  }

  public EventPlace getEventPlace()
  {
    return eventPlace;
  }

  public void setEventPlace(EventPlace iniEventPlace)
  {
    eventPlace = iniEventPlace;
  }
}
