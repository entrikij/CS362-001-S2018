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
    Appt appt2 = new Appt(15, 5, Calendar.DAY_OF_MONTH, 5, Calendar.YEAR, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    Appt appt3 = new Appt(10, 3, Calendar.YEAR, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    Appt appt4 = new Appt(5, 5,5, 3, Calendar.YEAR, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    Appt appt5 = new Appt(5, 10,5, 3, Calendar.YEAR, "Birthday Party", "This is my birthday party", "xyz@gmail.com");


    LinkedList<Appt> appts = test1.getAppts();
    int size = test1.getSizeAppts();
    assertEquals(0, size);
    appts.add(appt1);
    appts.add(appt2);
    string1 = test1.getFullInfomrationApp(test1);
    String string2 = test1.toString();

    assertNotEquals(null, string2);

    test1.addAppt(appt3);
    test1.addAppt(appt4);

    size = test1.getSizeAppts();
    assertEquals(4, size);

    appts = null;

    test1.addAppt(appt5);

    LinkedList<Appt> appts2 = test1.getAppts();
    assertNotEquals(15, appts2.get(0).getStartHour());
    assertEquals(5, appts2.get(1).getStartMinute());

  }
  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
    //get todays date
    Calendar rightnow = Calendar.getInstance();
    int thisMonth = rightnow.get(Calendar.MONTH);
    int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
    int thisYear = rightnow.get(Calendar.YEAR);
    GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);

    CalDay test1 = new CalDay(today);

    assertEquals(thisMonth - 1, test1.getMonth());
    assertEquals(thisDay, test1.getDay());
    assertEquals(thisYear, test1.getYear());
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
    //get todays date
    Calendar rightnow = Calendar.getInstance();
    int thisMonth = rightnow.get(Calendar.MONTH);
    int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
    int thisYear = rightnow.get(Calendar.YEAR);
    GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);

    CalDay test1 = new CalDay(today);
    Appt appt1 = new Appt(15, 30, Calendar.DAY_OF_MONTH, 6, Calendar.YEAR, "Birthday Party", "This is my birthday party", "xyz@gmail.com");

    String string1 = test1.getFullInfomrationApp(test1);
    assertEquals("4-4-2018 ", string1);

    test1.addAppt(appt1);
    int size = test1.getSizeAppts();
    assertEquals(1, size);

    string1 = test1.getFullInfomrationApp(test1);
    assertEquals("4-4-2018 \n" +
            "\t3:30PM Birthday Party This is my birthday party ", string1);



    CalDay test2 = new CalDay(today);
    Appt appt2 = new Appt(12, 10, Calendar.DAY_OF_MONTH, 6, Calendar.YEAR, "Birthday Party", "This is my birthday party", "xyz@gmail.com");

    test2.addAppt(appt2);
    String string2 = test2.getFullInfomrationApp(test2);

    assertEquals("4-4-2018 \n" +
            "\t0:10AM Birthday Party This is my birthday party ", string2);

    String string3 = test2.toString();

    assertEquals("\t --- 5/4/2018 --- \n" +
            " --- -------- Appointments ------------ --- \n" +
            "\t6/5/1 at 12:10pm ,Birthday Party, This is my birthday party\n \n", string3);
  }

}
