package springboot.ticketsonline.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table( name="bookedtickets")
public class BookedTickets
{
  @Id
  @GeneratedValue( strategy = GenerationType.AUTO, generator = "") // pt++ : both default values, just to show them ...
  @Column(name = "bookedtickets_id") // pt++ : just to show it ...
  private Long iD;

  @OneToMany
  private Set<Ticket> bookedTickets;

  public BookedTickets()
  {
  }

  public Set<Ticket> getBookedTickets()
  {
    return bookedTickets;
  }

  public void setBookedTickets(Set<Ticket> iniBookedTickets)
  {
    bookedTickets = iniBookedTickets;
  }
}
