/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Clock {
  /**
   *  Class field definintions go here
   */
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;

  /**
   *  Constructor goes here
   */
   private double ticks;
   private int hours;
   private int minutes;
   private double seconds;
   private double angle;
   private double second;

   public Clock() {
     ticks = 60.0;
     angle = 180.0;
     second = 0.0;
   }

   public Clock(double angle) {
     ticks = 60.0;
     angle = angle;
     second = 0.0;
   }

   public Clock(double angle, double timeSlice) {
     ticks = timeSlice;
     angle = angle;
     second = 0.0;
   }

  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick() {
      second += ticks;
      // seconds += ticks % 60;
      // minutes += (int)(ticks / 60);
      // hours += (int)(ticks / 3600);
      // while(seconds > 60) {
      //     minutes++;
      //     seconds = seconds / 60;
      // }
      // while(minutes > 60) {
      //     hours++;
      //     minutes = minutes / 60;
      // }
      return second;
   }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */
   public double validateAngleArg( String argValue ) throws NumberFormatException {
      double angle = Double.parseDouble(argValue);
      if(angle <= 360 && angle >= 0) {
        return angle;
      }
      return INVALID_ARGUMENT_VALUE;
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public double validateTimeSliceArg( String argValue ) {
      double timeSlice = Double.parseDouble(argValue);
      if (timeSlice < 1800 && timeSlice > 0) {
        this.ticks = timeSlice;
        return timeSlice;
      }
      return INVALID_ARGUMENT_VALUE;
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
      return HOUR_HAND_DEGREES_PER_SECOND * second % 360;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
     return MINUTE_HAND_DEGREES_PER_SECOND * second % 360;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
      double item = Math.abs(getHourHandAngle() - getMinuteHandAngle());
      if(item > 180){
        return 360 - item;
      }
      return item;
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return second;
   }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() {
      return "The time is " + (int)second/3600 + " hours, " + (int)second/60%60 + " minutes, " + second%60 + " seconds";
   }

   public boolean stop() {
      return getTotalSeconds() < 43200;
   }
  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {

      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      Clock clock = new Clock();
      System.out.println( "    New clock created: " + clock.toString() );
      System.out.println( "    Testing validateAngleArg()....");
      System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      try { System.out.println( (12.0 == clock.validateAngleArg( "12.0" )) ? " - Sending 12.0 degrees and got 12.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      try { System.out.println( (300.0 == clock.validateAngleArg( "300.0" )) ? " - Sending 300 .0degrees and got 300.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      try { System.out.println( (-1 == clock.validateAngleArg( "370.0" )) ? " - Sending 370.0 degrees should return -1" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      try { System.out.println( (-1 == clock.validateAngleArg( "ABC" )) ? " - Sending ABC should throw an error" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.println("Testing validateTimeSliceArg");
      try { System.out.println( (-1 == clock.validateTimeSliceArg( "0.0" )) ? " - Sending 0.0 for timeslice got -1" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      try { System.out.println( (12.0 == clock.validateTimeSliceArg( "12.0" )) ? " - Sending 12.0 for timeslice and got 12.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      try { System.out.println( (300.0 == clock.validateTimeSliceArg( "300.0" )) ? " - Sending 300.0 for timeslice and got 300.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      try { System.out.println( (370.0 == clock.validateTimeSliceArg( "370.0" )) ? " - Sending 370.0 for timeslice and got 370.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      try { System.out.println( (1000.0 == clock.validateTimeSliceArg( "1000.0" )) ? " - Sending 1000.0 for timeslice and got 1000.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      try { System.out.println( (-1 == clock.validateTimeSliceArg( "1800.0" )) ? " - Sending 1800.0 for timeslice should get -1" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      try { System.out.println( (-1 == clock.validateTimeSliceArg( "ABC" )) ? " - Should throw error" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      Clock clock2 = new Clock();
      clock2.validateTimeSliceArg("120");
      double minuteAngle = 0.0;
      double hourAngle = 0.0;
      double handAngle = 0.0;
      System.out.println("First 10 angles for a clock with Timeslice of 120");
      for (int i = 0; i < 10; i++){
          try { System.out.println( (handAngle == clock2.getHandAngle()) ? " - Correct hand angle" : " - no joy" ); }
          catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
          try { System.out.println( (minuteAngle == clock2.getMinuteHandAngle()) ? " - Correct minute angle" : " - no joy" ); }
          catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
          System.out.println();
          minuteAngle += 12.0;
          handAngle += 10.9992;
          clock2.tick();
      }
      Clock c3 = new Clock();
      System.out.println("Entire clock cycle with Timeslice of 60");
      while(c3.stop()){
        System.out.println(c3);
        System.out.println("The hour hand angle is " + c3.getHourHandAngle());
        System.out.println("The minute hand angle is " + c3.getMinuteHandAngle());
        System.out.println("The angle between hands is " + c3.getHandAngle() + "\n");
        c3.tick();
      }

    }
}
