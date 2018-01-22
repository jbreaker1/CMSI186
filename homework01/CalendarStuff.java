/**
 *  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  <your name here>
 *  Date          :  <date of writing here>
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.  It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time".  It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2017.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
 *  @version 1.0.1  2017-12-25  B.J. Johnson  Updated for Spring 2018
 */
public class CalendarStuff {

  /**
   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
   */
   private static final int SUNDAY = 0;
   private static final int MONDAY = SUNDAY + 1;
   private static final int TUESDAY = MONDAY + 1;
   private static final int WEDNESDAY = TUESDAY    + 1;
   private static final int THURSDAY = WEDNESDAY + 1;
   private static final int FRIDAY = THURSDAY + 1;
   private static final int SATURDAY = FRIDAY + 1;
  // you can finish the rest on your own
  private static final int maxDays = 31;
  private static final int maxMonths = 12;
  private static final int maxYearLength = 4;
  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
   */
   private static final int JANUARY = 0;
   private static final int FEBRUARY = JANUARY + 1;
   private static final int MARCH = FEBRUARY + 1;
   private static final int APRIL = MARCH + 1;
   private static final int MAY = APRIL + 1;
   private static final int JUNE = MAY + 1;
   private static final int JULY = JUNE + 1;
   private static final int AUGUST = JULY + 1;
   private static final int SEPTEMBER = AUGUST + 1;
   private static final int NOVEMBER = SEPTEMBER + 1;
   private static final int DECEMBER = NOVEMBER + 1;
  // you can finish these on your own, too

  /**
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   *  NOTE: this is optional, but suggested
   */
   private static int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
   private static int[] leapDays = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
   public long currentYear;
   public int currentDay;
   public int currentMonth;

  /**
   * The constructor for the class
   */
   public CalendarStuff() {
      currentYear = 0;
      currentDay = SUNDAY;
      currentMonth = JANUARY;
   }

  /**
   * A method to determine if the year argument is a leap year or not<br />
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear( long year ) {
     int length = String.valueOf((int)year).length();
     if (length != maxYearLength){
       throw new IllegalArgumentException("Not a valid year");
     }
     return (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0));
   }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */
   public static long daysInMonth( long month, long year ) {
      int length = String.valueOf((int)year).length();
      if(month > maxMonths || length != maxYearLength){
        throw new IllegalArgumentException("Not a valid month");
      }
      if(isLeapYear(year)){
        return leapDays[(int)month-1];
      }
      else{
        return days[(int)month-1];
      }
   }

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
     int length = String.valueOf((int)year1).length();
     int length2 = String.valueOf((int)year2).length();
     if(check(month1, day1, year1) || check(month2, day2, year2)){
       throw new IllegalArgumentException("Not a valid date");
      }
      return (year1 == year2 && month1 == month2 && day1 == day2);
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
     int length = String.valueOf((int)year1).length();
     int length2 = String.valueOf((int)year2).length();
     if(month1 > maxMonths || day1 > maxDays || month2 > maxMonths || day2 > maxDays || length != maxYearLength || length2 != maxYearLength){
       throw new IllegalArgumentException("Not a valid date");
      }
      if(dateEquals(month1, day1, year1, month2, day2, year2)){
        return 0;
      }
      if(year1 > year2){
        return 1;
      }
      if (year1 == year2){
        if(month1 > month2){
          return 1;
        }
        if(month1 == month2){
          if(day1 > day2){
            return 1;
          }
        }
      }
      return -1;
   }

  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   public static boolean isValidDate( long month, long day, long year ) {
     if (check(month, day, year)){
       return false;
     }
     else{
       if (isLeapYear(year)){
         return (day <= leapDays[(int)month-1]);
       }
       else{
         return (day <= days[(int)month-1]);
       }
     }
   }

  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */
   public static String toMonthString( int month ) {
        String s = "";
        switch (month) {
            case 1:  s = "January";
                     break;
            case 2:  s = "February";
                     break;
            case 3:  s = "March";
                     break;
            case 4:  s= "April";
                     break;
            case 5:  s= "May";
                     break;
            case 6:  s = "June";
                     break;
            case 7:  s= "July";
                     break;
            case 8:  s = "August";
                     break;
            case 9:  s = "September";
                     break;
            case 10: s = "October";
                     break;
            case 11: s = "November";
                     break;
            case 12: s = "December";
                     break;
            default: throw new IllegalArgumentException( "Illegal month value was given." );
        }
        return s;
   }

  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   */
   public static String toDayOfWeekString( int day ) {
     String s = "";
      switch(day) {
          case 1:  s = "Sunday";
                   break;
          case 2:  s = "Monday";
                   break;
          case 3:  s = "Tuesday";
                   break;
          case 4:  s = "Wednesday";
                   break;
          case 5:  s = "Thursday";
                   break;
          case 6:  s = "Friday";
                   break;
          case 7:  s = "Saturday";
                   break;
         default: throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }
      return s;
   }

  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */
   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      long dayCount = 0;
      long monthH = 0, monthL = 0, dayH = 0, dayL = 0, yearH = 0, yearL = 0;
      if (isValidDate(month1, day1, year1) && isValidDate(month1, day1, year1)){
        int compared = compareDate(month1, day1, year1, month2, day2, year2);
        if(compared == 0){return 0;}
        else if(compared > 0){monthH = month1; dayH = day1; yearH = year1; monthL = month2; dayL = day2; yearL=year2; }
        else{monthH = month2; dayH = day2; yearH = year2; monthL = month1; dayL = day1; yearL=year1;}
        while(dayL != dayH){
          dayCount++;
          dayL++;
          if(dayL > daysInMonth(monthL, yearL)){
            dayL = 1;
            monthL++;
            if(monthL > maxMonths){
              monthL = 1;
              yearL++;
            }
          }
        }
        while(monthL != monthH){
          dayCount += daysInMonth(monthL, yearL);
          monthL++;
          if(monthL > maxMonths){
            monthL = 1;
            yearL++;
          }
        }
        while(yearL != yearH){
          yearL++;
          if(isLeapYear(yearL)){
            dayCount += 366;
          }
          else{
            dayCount += 365;
          }
        }
        return dayCount;
      }
      else{
        throw new IllegalArgumentException("Incorrect input");
      }
   }
   public static boolean check(long month, long day, long year){
     int length = String.valueOf((int)year).length();
     return (month > maxMonths || day > maxDays || length != maxYearLength || month < 0 || day < 0);
   }

}
