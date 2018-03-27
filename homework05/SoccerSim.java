public class SoccerSim {
  private Ball[] list;

  public SoccerSim(Ball[] listOfBalls){
      list = listOfBalls;
  }



  public static void main( String args[] ) {
      if (args.length > 3){
         Ball[] list = new Ball[args.length / 4];
         if (args.length % 4 == 0){
            for(int i = 0; i < args.length; i+=4){
              Ball b = new Ball(Double.parseDouble(args[i]), Double.parseDouble(args[i+1]), Double.parseDouble(args[i+2]), Double.parseDouble(args[i+3]));
              list[i/4] = b;
            }
         }
         else if(args.length % 4 == 1){
           for(int i = 0; i < args.length-1; i+=4){
             Ball b = new Ball(Double.parseDouble(args[i]), Double.parseDouble(args[i+1]), Double.parseDouble(args[i+2]), Double.parseDouble(args[i+3]), Double.parseDouble(args[args.length-1]));
             list[i/4] = b;
           }
         }
         else{
            System.out.println("You have entered an invalid number of arguments");
            System.exit( 1 );
         }
         SoccerSim sim = new SoccerSim(list);
         while (list[0].isPossible(list)){
            list[0].movement(list);
            list[0].collide(list);
         }
      }
    else{
        System.out.println("Entered to few arguments must be minimum 4");
    }
  }
}