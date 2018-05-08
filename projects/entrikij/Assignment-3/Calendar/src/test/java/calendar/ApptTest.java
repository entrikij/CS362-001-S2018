/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalendarUtil;
public class ApptTest  {

    @Test(timeout = 4000)
    public void test00()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      String string0 = appt0.toString();
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      assertEquals(0, appt0.getRecurIncrement());
      assertEquals("\t14/9/2018 at 3:30pm ,nullBirthday Party, This is my birthday party\n", string0);

      appt0.setRecurrence(new int[0],2,3,3);
      assertEquals(3, appt0.getRecurIncrement());
      assertEquals(3, appt0.getRecurNumber());
    }
    @Test(timeout = 4000)
    public void test01()  throws Throwable  {
        Appt appt0 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt0.setValid();
        assertFalse(appt0.getValid());
        assertFalse(appt0.isOn(10,14,2018));
        assertTrue(appt0.isOn(9,14,2018));
        assertFalse(appt0.isOn(9,13,2018));
        assertFalse(appt0.isOn(9,13,2017));
        assertEquals(appt0.getEmailAddress(), "xyz@gmail.com");
    }
    @Test(timeout = 4000)
    public void test02()  throws Throwable  {
        Appt appt0 = new Appt( 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        assertFalse(appt0.hasTimeSet());
    }
    @Test(timeout = 4000)
    public void test03()  throws Throwable  {
        Appt appt0 = new Appt(15, 30, 9, 0, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt0.setValid();
        assertFalse(appt0.getValid());

        appt0.setStartMonth(1);
        appt0.setValid();
        assertTrue(appt0.getValid());

        appt0.setStartMonth(12);
        appt0.setValid();
        assertTrue(appt0.getValid());

        appt0.setStartMonth(13);
        appt0.setValid();
        assertFalse(appt0.getValid());

        appt0.setStartMonth(5);
        appt0.setStartHour(24);
        appt0.setValid();
        assertFalse(appt0.getValid());

        appt0.setStartHour(-1);
        appt0.setValid();
        assertFalse(appt0.getValid());

        appt0.setStartHour(0);
        appt0.setValid();
        assertTrue(appt0.getValid());

        appt0.setStartHour(23);
        appt0.setValid();
        assertTrue(appt0.getValid());

        appt0.setStartHour(12);
        appt0.setStartMinute(60);
        appt0.setValid();
        assertFalse(appt0.getValid());

        appt0.setStartMinute(-1);
        appt0.setValid();
        assertFalse(appt0.getValid());

        appt0.setStartMinute(0);
        appt0.setValid();
        assertTrue(appt0.getValid());

        appt0.setStartMinute(59);
        appt0.setValid();
        assertTrue(appt0.getValid());

        appt0.setStartMinute(30);
        appt0.setStartYear(-1);
        appt0.setValid();
        assertFalse(appt0.getValid());

        appt0.setStartYear(0);
        appt0.setValid();
        assertFalse(appt0.getValid());

        appt0.setStartYear(2018);
        appt0.setStartDay(-1);
        appt0.setValid();
        assertFalse(appt0.getValid());

        appt0.setStartDay(1);
        appt0.setValid();
        assertTrue(appt0.getValid());

        appt0.setStartDay(31);
        appt0.setValid();
        assertTrue(appt0.getValid());

        appt0.setStartDay(40);
        appt0.setValid();
        assertFalse(appt0.getValid());

        appt0.setStartDay(10);
        appt0.setValid();
        appt0.setTitle(null);
        appt0.setDescription(null);
        assertTrue(appt0.hasTimeSet());
    }


}
