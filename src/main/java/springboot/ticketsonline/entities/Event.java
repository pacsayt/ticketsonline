package springboot.ticketsonline.entities;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.sql.Timestamp;
// import java.util.Date;

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
@Table( name = "event", uniqueConstraints={@UniqueConstraint(columnNames = {"name" , "date"})}) // pt++
public class Event
{
  @Id
  @GeneratedValue( strategy = GenerationType.AUTO, generator = "")
  @Column(name = "event_id")
  private Long iD;

  @Column( name="name", length = 255, nullable = true, unique = false)
  private String name;

  @Column( name="date")
  private Timestamp date;

  @ManyToOne
//  @JoinColumn( name="event_place_id", nullable=false)
  private EventPlace eventPlace;

  public Event()
  {
  }

  @Autowired
  public Event(Long iniID, String iniName, Timestamp iniDate, EventPlace iniEventPlace)
  {
    iD = iniID;
    name = iniName;
    date = iniDate;
    eventPlace = iniEventPlace;
  }

/*
  @Id // pt++ : should this annotation be added here or to the member definition ?
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
*/
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

  public Timestamp getDate()
  {
    return date;
  }

  public void setDate(Timestamp iniDate)
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
