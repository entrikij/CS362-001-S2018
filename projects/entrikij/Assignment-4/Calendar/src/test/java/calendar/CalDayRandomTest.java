package calendar;


import org.junit.Test;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import static org.junit.Assert.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {

	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	//private static final long TestTimeout = 500 * 1;
	private static final int NUM_TESTS=100;

	/**
	 * Return a randomly selected method to be tests !.
	 */
	public static String RandomSelectMethod(Random random){
		String[] methodArray = new String[] {"addAppt"};// The list of the of methods to be tested in the Appt class

		int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)

		return methodArray[n] ; // return the method name
	}

	/**
	 * Generate Random Tests that tests CalDay Class.
	 */
	@Test
	public void randomtest()  throws Throwable  {

		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;


		System.out.println("Start testing...");

		try{
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed =System.currentTimeMillis(); //10
				//			System.out.println(" Seed:"+randomseed );
				Random random = new Random(randomseed);

				Calendar rightnow = Calendar.getInstance();
				int thisMonth = rightnow.get(Calendar.MONTH);
				int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
				int thisYear = rightnow.get(Calendar.YEAR);
				GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);

				CalDay calDay1 = new CalDay(today);

				for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = CalDayRandomTest.RandomSelectMethod(random);

					if (methodName.equals("addAppt")){
						int startHour = ValuesGenerator.getRandomIntBetween(random, -10, 34);
						int startMinute = ValuesGenerator.getRandomIntBetween(random, -20, 80);
						int startDay=ValuesGenerator.getRandomIntBetween(random, -5, 40);
						int startMonth=ValuesGenerator.getRandomIntBetween(random, -5, 15);
						int startYear=ValuesGenerator.getRandomIntBetween(random, -1000, 2018);
						String title="Birthday Party";
						String description="This is my birthday party.";
						String emailAddress="xyz@gmail.com";

						//Construct a new Appointment object with the initial data
						//Construct a new Appointment object with the initial data
						Appt appt = new Appt(startHour,
								startMinute ,
								startDay ,
								startMonth ,
								startYear ,
								title,
								description,
								emailAddress);

						appt.setValid();
						calDay1.addAppt(appt);

					}

				}


				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				if((iteration%10000)==0 && iteration!=0 )
					System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);

			}
		}catch(NullPointerException e){

		}

		System.out.println("Done testing...");
	}


	
}
