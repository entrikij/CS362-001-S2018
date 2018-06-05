package calendar;

import java.util.Calendar;
import java.util.Random;

import org.junit.Test;



import static org.junit.Assert.*;



/**
 * Random Test Generator  for Appt class.
 */

public class ApptRandomTest {

	//private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final long TestTimeout = 500 * 1;
	private static final int NUM_TESTS=100;

	/**
	 * Return a randomly selected method to be tests !.
	 */
    public static String RandomSelectMethod(Random random){
        String[] methodArray = new String[] {/*"setTitle",*/"setRecurrence","setValid","isOn"};// The list of the of methods to be tested in the Appt class

    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
    	            
        return methodArray[n] ; // return the method name 
        }
	/**
	 * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
	 */
    public static int RandomSelectRecur(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_BY_WEEKLY,Appt.RECUR_BY_MONTHLY,Appt.RECUR_BY_YEARLY};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return the value of the  appointments to recur 
        }	
	/**
	 * Return a randomly selected appointments to recur forever or Never recur  !.
	 */
    public static int RandomSelectRecurForEverNever(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_NUMBER_FOREVER,Appt.RECUR_NUMBER_NEVER};// The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return appointments to recur forever or Never recur 
        }	
   /**
     * Generate Random Tests that tests Appt Class.
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

				 int startHour=ValuesGenerator.getRandomIntBetween(random, 1, 11);
				 int startMinute=ValuesGenerator.getRandomIntBetween(random, 1, 11);
				 int startDay=ValuesGenerator.getRandomIntBetween(random, -20, -10);
				 int startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 11);
				 int startYear=ValuesGenerator.getRandomIntBetween(random, 2018, 2018);
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

				if(!appt.getValid())continue;
				for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = ApptRandomTest.RandomSelectMethod(random);

//					if (methodName.equals("setTitle")){
//					   String newTitle=(String) ValuesGenerator.getString(random);
//					   appt.setTitle(newTitle);
//					}
					if (methodName.equals("setRecurrence")){
					   int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
						int[] recurDays;
					   int coinflip = ValuesGenerator.getRandomIntBetween(random, 0, 1);
					   if(coinflip == 0){
						   recurDays=null;
					   }else{
						   recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
					   }
					   int recur=ApptRandomTest.RandomSelectRecur(random);
					   int recurIncrement = ValuesGenerator.RandInt(random);
					   int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
					   appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
					}
					else if(methodName.equals("setValid")) {
						if(i != 0){
							Random random2 = new Random(randomseed);

							startHour = ValuesGenerator.getRandomIntBetween(random, -10, 34);
							startMinute = ValuesGenerator.getRandomIntBetween(random, -20, 80);
							startDay=ValuesGenerator.getRandomIntBetween(random, -5, 40);
							startMonth=ValuesGenerator.getRandomIntBetween(random, -5, 15);
							startYear=ValuesGenerator.getRandomIntBetween(random, -1000, 2018);

							appt.setStartHour(startHour);
							appt.setStartMinute(startMinute);
							appt.setStartDay(startDay);
							appt.setStartMonth(startMonth);
							appt.setStartYear(startYear);

							appt.setValid();
						}
					}
					else if(methodName.equals("isOn")){
						int day = ValuesGenerator.getRandomIntBetween(random, appt.getStartDay()-1, appt.getStartDay()+1);
						int month = ValuesGenerator.getRandomIntBetween(random, appt.getStartMonth()-1, appt.getStartMonth()+1);
						int year = ValuesGenerator.getRandomIntBetween(random, appt.getStartYear()-1, appt.getStartYear()+1);

						boolean value = appt.isOn(day,month,year);
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
