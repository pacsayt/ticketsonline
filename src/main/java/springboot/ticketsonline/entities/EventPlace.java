package springboot.ticketsonline.entities;

public class EventPlace
{
  private Integer iD;
  private String  name;
  private Integer noOfSeats;

  public EventPlace()
  {
  }

  public EventPlace(Integer iniID, String iniName, Integer iniNoOfSeats)
  {
    iD = iniID;
    name = iniName;
    noOfSeats = iniNoOfSeats;
  }
}
