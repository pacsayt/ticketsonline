package springboot.ticketsonline.entities;

import javax.persistence.*;

@Entity
@Table( name = "ticket")
public class Ticket
{
  @Id
  @GeneratedValue( strategy = GenerationType.AUTO, generator = "") // pt++ : both default values, just to show them ...
  @Column( name = "ticket_id") // pt++ : just to show it ...
  private Long iD;

  @Column( name = "seat_no", precision = 255, scale = 0) // pt++ : just to show it ...
  private Integer seatNo;

  @ManyToOne
  @JoinColumn( name="event_id", nullable=false)
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
