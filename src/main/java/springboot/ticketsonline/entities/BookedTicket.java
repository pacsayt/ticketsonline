package springboot.ticketsonline.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table( name="bookedticket")
public class BookedTicket
{
  @Id
  @GeneratedValue( strategy = GenerationType.AUTO, generator = "") // pt++ : both default values, just to show them ...
  @Column(name = "bookedticket_id") // pt++ : just to show it ...
  private Long iD;

  @OneToOne( targetEntity = Ticket.class) // pt++ : fetch = FetchType.EAGER - default value
//  @Column(name = "booked_ticket")
  private Ticket bookedTicket;

  public BookedTicket()
  {
  }

  public Ticket getBookedTicket()
  {
    return bookedTicket;
  }

  public void setBookedTickets(Ticket iniBookedTicket)
  {
    bookedTicket = iniBookedTicket;
  }
}