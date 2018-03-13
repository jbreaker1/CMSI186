/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
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
public class ClockSolver {
  /**
   *  Class field definintions go here
   */
   private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
   private final double EPSILON_VALUE              = 5;      // small value for double-precision comparisons
   private double targetAngle = 0.0;
   private double targetSlice = 0.0;
   public Clock clock;

  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   public ClockSolver() {
      super();
   }

  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */
   public void handleInitialArguments( String args[] ) {
     // args[0] specifies the angle for which you are looking
     //  your simulation will find all the angles in the 12-hour day at which those angles occur
     // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds
     // you may want to consider using args[2] for an "angle window"
      Clock c = new Clock();
      System.out.println( "\n   Hello world, from the ClockSolver program!!\n\n" ) ;
      if( 0 == args.length ) {
         System.out.println( "   Sorry you must enter at least one argument\n" +
                             "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                             "   Please try again..........." );
         System.exit( 1 );
      }
      else if(args.length == 1){
        targetAngle = c.validateAngleArg(args[0]);
        clock = new Clock(targetAngle);
        if(targetAngle == -1){
          throw new IllegalArgumentException("Not a valid angle must be between 0 and 360");
        }
      }
      else if(args.length == 2){
        targetAngle = c.validateAngleArg(args[0]);
        targetSlice = c.validateTimeSliceArg(args[1]);
        clock = new Clock(targetAngle, targetSlice);
        if(targetAngle == -1){
          throw new IllegalArgumentException("Not a valid angle must be between 0 and 360");
        }
        if(targetSlice == -1){
          throw new IllegalArgumentException("Not a valid timeslice");
        }
      }
      else{
        throw new IllegalArgumentException("Too many arguments");
      }
   }

   public double getEpsilon(){
      return EPSILON_VALUE;
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   */
   public static void main( String args[] ) {
      ClockSolver cse = new ClockSolver();
      cse.handleInitialArguments( args );
      double[] timeValues = new double[3];
      int count = 0;
      while(cse.clock.stop()) {
          if (cse.clock.getHandAngle() - Double.parseDouble(args[0]) <= cse.getEpsilon() && cse.clock.getHandAngle() - Double.parseDouble(args[0]) >= 0
              || Double.parseDouble(args[0])-cse.clock.getHandAngle() <= cse.getEpsilon() && Double.parseDouble(args[0]) - cse.clock.getHandAngle() >= 0){
            System.out.println(cse.clock);
            count++;
          }
          cse.clock.tick();
      }
      if(count == 0){
          System.out.println("No angles found");
      }
      System.exit( 0 );
   }
}
