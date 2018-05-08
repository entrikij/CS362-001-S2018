/** A JUnit test class to test the class CalDay. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;


public class CalDayTest{

  @Test(timeout = 10000)
  public void test00()  throws Throwable  {
    //get todays date
    Calendar rightnow = Calendar.getInstance();
    int thisMonth = rightnow.get(Calendar.MONTH);
    int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
    int thisYear = rightnow.get(Calendar.YEAR);
    GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);

    CalDay test1 = new CalDay();
    Iterator iterator = test1.iterator();
    String string1 = test1.toString();

    CalDay test2 = new CalDay(today);
    assertTrue(test2.isValid());
    iterator = test2.iterator();
    String string2 = test2.toString();
    int sizeAppts = test2.getSizeAppts();

    Appt appt1 = new Appt(15, 30, Calendar.DAY_OF_MONTH, 0, Calendar.YEAR, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    appt1.setValid();
    test2.addAppt(appt1);
    appt1.setStartMonth(Calendar.MONTH);
    appt1.setValid();
    test2.addAppt(appt1);

    string2 = test2.toString();
  }
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
    //get todays date
    Calendar rightnow = Calendar.getInstance();
    int thisMonth = rightnow.get(Calendar.MONTH);
    int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
    int thisYear = rightnow.get(Calendar.YEAR);
    GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);

    CalDay test1 = new CalDay(today);
    String string1 = test1.getFullInfomrationApp(test1);

    Appt appt1 = new Appt(15, 30, Calendar.DAY_OF_MONTH, 6, Calendar.YEAR, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    Appt appt2 = new Appt(5, 5, Calendar.DAY_OF_MONTH, 5, Calendar.YEAR, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    Appt appt3 = new Appt(5, 3, Calendar.YEAR, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    Appt appt4 = new Appt(0, 5,5, 3, Calendar.YEAR, "Birthday Party", "This is my birthday party", "xyz@gmail.com");

    LinkedList<Appt> appts = test1.getAppts();
    appts.add(appt1);
    appts.add(appt2);
    string1 = test1.getFullInfomrationApp(test1);
    String string2 = test1.toString();

    test1.addAppt(appt3);
    test1.addAppt(appt4);

    string1 = test1.getFullInfomrationApp(test1);

    appts = null;


  }

}
