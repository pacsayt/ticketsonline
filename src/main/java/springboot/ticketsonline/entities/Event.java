package springboot.ticketsonline.entities;

import java.util.Date;

public class Event
{
  private Integer iD;
  private String name;
  private Date date;
  private EventPlace eventPlace;

  public Event()
  {
  }

  public Event(Integer iniID, String iniName, Date iniDate, EventPlace iniEventPlace)
  {
    iD = iniID;
    name = iniName;
    date = iniDate;
    eventPlace = iniEventPlace;
  }
}
