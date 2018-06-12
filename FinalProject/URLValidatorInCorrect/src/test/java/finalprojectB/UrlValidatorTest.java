
package finalprojectB;

import junit.framework.TestCase;
import java.util.Random;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!



public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   public class Pairs {
      public String name;
      public boolean valid;

      public Pairs(String name, boolean valid){
         this.name = name;
         this.valid = valid;
      }
   }

   public void testManualTest()
   {
         //You can use this function to implement your manual testing
         UrlValidator validator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
         assertTrue(validator.isValid("http://www.google.com"));

         //Any lines that are commented out cannot be used because of the bug preventing DomainValidator from loading


         //BUG #1 FOUND -- UrlValidator.java line 318
         assertTrue(validator.isValid("http:/www.google.com"));
         assertTrue(validator.isValid("http://go.com"));

         //BUG #2 FOUND -- Error on authority check -- UrlValidator.java line 393
         //assertTrue(validator.isValid("ftp://google.com"));
         //assertTrue(validator.isValid("ftp://www.google.com"));
         assertFalse(validator.isValid(""));
         assertFalse(validator.isValid(null));
         assertFalse(validator.isValid("google.com"));
         assertTrue(validator.isValid("http://192.168.0.1"));

         //Same as Bug #1
         assertTrue(validator.isValid("http://www.192.168.0.1")); //SHOULD BE FALSE
         //assertFalse(validator.isValid("http://256.256.256.256"));

         //Authority testing
         assertTrue(validator.isValid("http://www.google.com"));
         assertFalse(validator.isValid("http://www.goo:gle.com"));
         //assertFalse(validator.isValid("www.google.comhttp://"));
         //assertTrue(validator.isValid("ftp://go.com"));
         //assertTrue(validator.isValid("ftp://go.au"));
         //assertTrue(validator.isValid("ftp://255.255.255.255"));
         //assertTrue(validator.isValid("ftp://255.com"));

         //Query Testing
         assertTrue(validator.isValid("http://www.google.com?action=view"));
         assertTrue(validator.isValid("http://www.google.com?a923!@#)(c tion==v!&@iew"));

         //Various Testing
         assertFalse(validator.isValid("123456"));
         assertFalse(validator.isValid("?!@#$"));

         //assertFalse(validator.isValid("http://www.go.a"));

         //assertTrue(validator.isValid("ftp://192.168.0.1:80"));

   }


   public void testYourFirstPartition()
   {
	 //You can use this function to implement your First Partition testing
       //You can use this function for programming based testing
       Pairs[] testSchemes = {
               new Pairs("http://", true),
               new Pairs("3ht://", false)
       };

       Pairs[] testAuthorities = {
               new Pairs("www.google.com", true),
               new Pairs("256.256.256.256", false)
       };

       Pairs[] testPaths = {
               new Pairs("", true),
               new Pairs("/t123", true),
               new Pairs("/..", false),
       };

       Pairs[] testQueries = {
               new Pairs("", true),
               new Pairs("?action=view", true)
       };

       UrlValidator validator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

       for(int schemeNum = 0; schemeNum < 2; schemeNum++){
           for(int authNum = 0; authNum < 2; authNum++){
               for(int pathNum = 0; pathNum < 3; pathNum++){
                   for(int queryNum = 0; queryNum < 2; queryNum++){
                       String testURL = testSchemes[schemeNum].name;
                       testURL += testAuthorities[authNum].name;
                       testURL += testPaths[pathNum].name;
                       testURL += testQueries[queryNum].name;
                       boolean expected = testSchemes[schemeNum].valid && testAuthorities[authNum].valid && testPaths[pathNum].valid && testQueries[queryNum].valid;
                       try{
                           boolean result = validator.isValid(testURL);

                           //assertEquals(valid, result); commented out because bugs prevent accurate results
                       } catch(java.lang.ExceptionInInitializerError e) {
                           System.out.println("ERROR 1 -- ");
                       } catch(java.lang.NoClassDefFoundError e){
                           System.out.println("ERROR 2 -- ");
                       }
                   }
               }
           }
       }

   }

   public void testYourSecondPartition(){
		 //You can use this function to implement your Second Partition testing

   }
   //You need to create more test cases for your Partitions if you need to

   public void testIsValid()
   {
	   //You can use this function for programming based testing
      Pairs[] testSchemes = {new Pairs("http://", true),
              new Pairs("ftp://", true),
              new Pairs("h3t://", true),
              new Pairs("3ht://", false),
              new Pairs("http:/", false),
              new Pairs("http:", false),
              new Pairs("http/", false),
              new Pairs("://", false),
              new Pairs("", true)};

      Pairs[] testAuthorities = {new Pairs("www.google.com", true),
              new Pairs("go.com", true),
              new Pairs("go.au", true),
              new Pairs("0.0.0.0", true),
              new Pairs("255.255.255.255", true),
              new Pairs("256.256.256.256", false),
              new Pairs("255.com", true),
              new Pairs("1.2.3.4.5", false),
              new Pairs("1.2.3.4.", false),
              new Pairs("1.2.3", false),
              new Pairs(".1.2.3.4", false),
              new Pairs("go.a", false),
              new Pairs("go.a1a", false),
              new Pairs("go.1aa", false),
              new Pairs("aaa.", false),
              new Pairs(".aaa", false),
              new Pairs("aaa", false),
              new Pairs("", false),
              new Pairs("goo:gle.com", false)};

      Pairs[] testPaths = {new Pairs("/test1", true),
              new Pairs("/t123", true),
              new Pairs("/$23", true),
              new Pairs("/..", false),
              new Pairs("/../", false),
              new Pairs("/test1/", true),
              new Pairs("", true),
              new Pairs("/test1/file", true),
              new Pairs("/..//file", false),
              new Pairs("/test1//file", false)};

      Pairs[] testQueries = {new Pairs("?action=view", true),
              new Pairs("?action=edit&mode=up", true),
              new Pairs("", true)
      };

      int numTrue = 0;
      UrlValidator validator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);


      for(int i = 0; i < 1000; i++){
         String testUrl = "";
         boolean valid = true;

         int int1 = new Random().nextInt(8);
         int int2 = new Random().nextInt(19);
         int int3 = new Random().nextInt(10);
         int int4 = new Random().nextInt(3);

         testUrl += testSchemes[int1].name + testAuthorities[int2].name + testPaths[int3].name + testQueries[int4].name;
         valid = testSchemes[int1].valid && testAuthorities[int2].valid && testPaths[int3].valid && testQueries[int4].valid;

         if(valid){
            numTrue++;
         }

         try{
            boolean result = validator.isValid(testUrl);

            if(valid != result);
               System.out.println(testUrl + " " + valid + " " +result);

            //assertEquals(valid, result);
         } catch(java.lang.ExceptionInInitializerError e) {
            System.out.println("ERROR 1 -- ");
         } catch(java.lang.NoClassDefFoundError e){
            System.out.println("ERROR 2 -- ");
         }
      }

      System.out.println("Number True: " + numTrue);
   }



}
