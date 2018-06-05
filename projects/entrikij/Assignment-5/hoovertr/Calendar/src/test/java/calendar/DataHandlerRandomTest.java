package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for DataHandler class.
 */

public class DataHandlerRandomTest {

	//private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final long TestTimeout = 500 * 1;
	private static final int NUM_TESTS=100;

	/**
	 * Return a randomly selected method to be tests !.
	 */
	public static String RandomSelectMethod(Random random){
		String[] methodArray = new String[] {"deleteAppt","getApptRange"};// The list of the of methods to be tested in the Appt class

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

				DataHandler dataHandler;

				int coinflip = ValuesGenerator.getRandomIntBetween(random, 0, 2);
				if(coinflip == 0){
					dataHandler = new DataHandler("calendar.xml",true);
				}
				else if(coinflip == 1){
					dataHandler = new DataHandler();
				}
				else{
					dataHandler = new DataHandler("calendar.xml",false);
				}


				for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = DataHandlerRandomTest.RandomSelectMethod(random);

					if (methodName.equals("deleteAppt")){
						int startHour = ValuesGenerator.getRandomIntBetween(random, -10, 34);
						int startMinute = ValuesGenerator.getRandomIntBetween(random, -20, 80);
						int startDay=ValuesGenerator.getRandomIntBetween(random, -5, 40);
						int startMonth=ValuesGenerator.getRandomIntBetween(random, -5, 15);
						int startYear=ValuesGenerator.getRandomIntBetween(random, -1000, 2018);

						Appt appt1 = new Appt(startHour, startMinute, startDay, startMonth, startYear, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
						appt1.setValid();

						int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
						int[] recurDaysArr = ValuesGenerator.generateRandomArray(random, sizeArray);
						appt1.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, ValuesGenerator.getRandomIntBetween(random, 0, 8), ValuesGenerator.getRandomIntBetween(random, 0, 8));

						dataHandler.saveAppt(appt1);

						coinflip = ValuesGenerator.getRandomIntBetween(random, 0, 1);

						if(coinflip == 0){
							appt1.setXmlElement(null);
						}
						else{

						}

						dataHandler.deleteAppt(appt1);

					}
					else if (methodName.equals("getApptRange")){
						int startHour = ValuesGenerator.getRandomIntBetween(random, -10, 34);
						int startMinute = ValuesGenerator.getRandomIntBetween(random, -20, 80);
						int startDay=ValuesGenerator.getRandomIntBetween(random, -5, 40);
						int startMonth=ValuesGenerator.getRandomIntBetween(random, -5, 15);
						int startYear=ValuesGenerator.getRandomIntBetween(random, -1000, 2018);

						Appt appt1 = new Appt(startHour, startMinute, startDay, startMonth, startYear, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
						appt1.setValid();

						int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
						int[] recurDaysArr = ValuesGenerator.generateRandomArray(random, sizeArray);
						appt1.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, ValuesGenerator.getRandomIntBetween(random, 0, 8), ValuesGenerator.getRandomIntBetween(random, 0, 8));

						dataHandler.saveAppt(appt1);

						long randomseed2 =System.currentTimeMillis(); //10
						Random random2 = new Random(randomseed);
						int startDay2 =ValuesGenerator.getRandomIntBetween(random, -5, 40);
						int startMonth2 =ValuesGenerator.getRandomIntBetween(random, -5, 15);
						int startYear2 =ValuesGenerator.getRandomIntBetween(random, -1000, 2018);

						long randomseed3 =System.currentTimeMillis(); //10
						Random random3 = new Random(randomseed);
						int startDay3 =ValuesGenerator.getRandomIntBetween(random, -5, 40);
						int startMonth3 =ValuesGenerator.getRandomIntBetween(random, -5, 15);
						int startYear3 =ValuesGenerator.getRandomIntBetween(random, -1000, 2018);

						GregorianCalendar day1 = new GregorianCalendar(startYear2, startMonth2, startDay2);
						GregorianCalendar day2 = new GregorianCalendar(startYear2 + 100, startMonth3, startDay3);


//						coinflip = ValuesGenerator.getRandomIntBetween(random, 0, 1);
//
//						if(coinflip == 0){
//							appt1.setXmlElement(null);
//						}
//						else{
//
//						}

						try{
							dataHandler.getApptRange(day1,day2);
						}catch(DateOutOfRangeException e){
							System.out.println("Date in wrong order");
							continue;
						}

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
