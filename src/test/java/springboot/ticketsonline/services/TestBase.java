package springboot.ticketsonline.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestBase
{
  // pt++ : as there's no reasonable constructor for Date
  protected static Date stringToDate(String dateInStringFormat) throws ParseException
  {
    Date newdate = null;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

    newdate = dateFormat.parse( dateInStringFormat);

    return newdate;
  }
}
