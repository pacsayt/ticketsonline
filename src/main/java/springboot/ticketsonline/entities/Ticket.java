package springboot.ticketsonline.entities;

public class Ticket
{
  private Integer iD;
  private Integer seatNo;
  private Event event;

  public Ticket()
  {
  }

  public Ticket(Integer iniId, Integer iniSeatNo, Event iniEvent)
  {
    iD = iniId;
    seatNo = iniSeatNo;
    event = iniEvent;
  }
}
