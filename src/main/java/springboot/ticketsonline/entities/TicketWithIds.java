package springboot.ticketsonline.entities;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * In order to be able to test ConstructorResult in https://www.baeldung.com/jpa-sql-resultset-mapping
 */
public class TicketWithIds
{
  private Long iD;

  private Integer seatNo;

  private Long eventId;

  private Integer ticketPrice;

  public TicketWithIds( Long iniId, Integer iniSeatNo, Integer iniTicketPrice, Long iniEventId)
  {
    iD = iniId;
    seatNo = iniSeatNo;
    eventId = iniEventId;
    ticketPrice = iniTicketPrice;
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

  public Long getEventId()
  {
    return eventId;
  }

  public void setEventId(Long iniEventId)
  {
    eventId = iniEventId;
  }

  public Integer getTicketPrice()
  {
    return ticketPrice;
  }

  public void setTicketPrice(Integer iniTicketPrice)
  {
    ticketPrice = iniTicketPrice;
  }

}
