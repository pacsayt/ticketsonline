package springboot.ticketsonline.entities;

import javax.persistence.*;

@Entity
@Table( name = "tickets")
public class Ticket
{
  @Id
  @GeneratedValue( strategy = GenerationType.AUTO, generator = "") // pt++ : both default values, just to show them ...
  @Column(name = "id") // pt++ : just to show it ...
  private Long iD;
  private Integer seatNo;
  private Event event;

  public Ticket()
  {
  }

  public Ticket(Long iniId, Integer iniSeatNo, Event iniEvent)
  {
    iD = iniId;
    seatNo = iniSeatNo;
    event = iniEvent;
  }

  public Long getiD()
  {
    return iD;
  }

  public void setiD(Long iniId)
  {
    iD = iniId;
  }

  public Integer getSeatNo()
  {
    return seatNo;
  }

  public void setSeatNo(Integer iniSeatNo)
  {
    seatNo = iniSeatNo;
  }

  public Event getEvent()
  {
    return event;
  }

  public void setEvent(Event iniEvent)
  {
    event = iniEvent;
  }
}
