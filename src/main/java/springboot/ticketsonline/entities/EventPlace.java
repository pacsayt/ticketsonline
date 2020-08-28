package springboot.ticketsonline.entities;

import javax.persistence.*;

@Entity
@Table( name = "eventplaces")
public class EventPlace
{
  @Id
  @GeneratedValue( strategy = GenerationType.AUTO, generator = "")
  private Long iD;

  @Column( name = "name", unique = true)
  private String  name;

  @Column( name="noofseats", precision = 255, scale = 0)
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
