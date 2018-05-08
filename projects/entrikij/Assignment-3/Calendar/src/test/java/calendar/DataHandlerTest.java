
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


public class DataHandlerTest{

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
    DataHandler dataHandler;
    dataHandler = new DataHandler();

    //get todays date
    Calendar rightnow = Calendar.getInstance();
    int thisMonth = rightnow.get(Calendar.MONTH);
    int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
    int thisYear = rightnow.get(Calendar.YEAR);

    // Get a calendar which is set to a specified date.
    Calendar calendar = new GregorianCalendar(thisYear, thisMonth, thisDay);
    calendar.add(calendar.DAY_OF_MONTH,1);

    Appt appt1 = new Appt(15, 30, Calendar.DAY_OF_MONTH, Calendar.MONTH, Calendar.YEAR, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    Appt appt2 = new Appt(5, 5, Calendar.DAY_OF_MONTH, Calendar.MONTH, Calendar.YEAR, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    Appt appt3 = new Appt(Calendar.DAY_OF_MONTH, Calendar.MONTH, Calendar.YEAR, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    Appt appt4 = new Appt(0, 5, Calendar.DAY_OF_MONTH, Calendar.MONTH, Calendar.YEAR, "Birthday Party", "This is my birthday party", "xyz@gmail.com");

    int[] recurDaysArr={2,3,4};
    appt1.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, 3);
    appt2.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, 3);
    appt3.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, 3);
    appt4.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, 3);


    dataHandler.saveAppt(appt1);
    dataHandler.saveAppt(appt2);
    dataHandler.saveAppt(appt3);
    dataHandler.saveAppt(appt4);

    //get a list of appointments for one day, which is today
    GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
    GregorianCalendar tomorrow = new GregorianCalendar(thisYear,thisMonth,thisDay+1);

    List<CalDay> CalDays;
    CalDays = dataHandler.getApptRange(today, tomorrow);

    tomorrow.add(today.DAY_OF_MONTH,1);

    assertTrue(dataHandler.deleteAppt(appt2));

    DataHandler dataHandler2 = new DataHandler("calendar2.xml",true);

    dataHandler2.saveAppt(appt2);

  }
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
    DataHandler dataHandler;
    dataHandler = new DataHandler();

    //get todays date
    Calendar rightnow = Calendar.getInstance();
    int thisMonth = rightnow.get(Calendar.MONTH);
    int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
    int thisYear = rightnow.get(Calendar.YEAR);

    // Get a calendar which is set to a specified date.
    Calendar calendar = new GregorianCalendar(thisYear, thisMonth, thisDay);
    calendar.add(calendar.DAY_OF_MONTH,1);

    Appt appt1 = new Appt(25, 30, Calendar.DAY_OF_MONTH, Calendar.MONTH, Calendar.YEAR, "Birthday Party", "This is my birthday party", "xyz@gmail.com");

    int[] recurDaysArr={2,3,4};
    appt1.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, 3);

    assertTrue(dataHandler.saveAppt(appt1));
    assertTrue(dataHandler.deleteAppt(appt1));

  }

}
