package springboot.ticketsonline.entities;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

// import javax.persistence.*;

/**
 * H2 in-memory database. Table not found
 * https://stackoverflow.com/questions/5763747/h2-in-memory-database-table-not-found
 */
@Entity
@Table( name = "event_place")
public class EventPlace
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column( name = "event_place_id", unique = true)
  private Long iD;

  @Column( name = "name", unique = true)
  private String  name;

  @Column( name="no_of_seats", precision = 255, scale = 0)
  private Integer noOfSeats;

  public EventPlace()
  {
  }

  public EventPlace(Long iniID, String iniName, Integer iniNoOfSeats)
  {
    iD = iniID;
    name = iniName;
    noOfSeats = iniNoOfSeats;
  }

  public Long getiD()
  {
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

  public Integer getNoOfSeats()
  {
    return noOfSeats;
  }

  public void setNoOfSeats(Integer iniNoOfSeats)
  {
    noOfSeats = iniNoOfSeats;
  }
}
