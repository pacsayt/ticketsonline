package springboot.ticketsonline.entities;

import java.util.List;

public class Events
{
  private List<Event> events;

  public Events( List<Event> iniEvents)
  {
    events = iniEvents;
  }

  public List<Event> getEvents()
  {
    return events;
  }
}
