package springboot.ticketsonline.entities;

public class EventPlace
{
  private Long iD;
  private String  name;
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
