import java.util.Scanner;
public class HighRoll {
  public static void main( String[] args ) {
     Scanner scan = new Scanner(System.in);
     if (args.length == 0){
       System.out.println("Give a number of dice and sides please");
       System.exit(0);
     }
     if (args.length == 0){
       System.out.println("Give a number of sides please");
       System.exit(0);
     }
     DiceSet ds = new DiceSet(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
     int highscore = 0;
     boolean x = true;
     while(x){
       System.out.println("ROLL ALL THE DICE : 1");
       System.out.println("ROLL A SINGLE DIE : 2 <which die>");
       System.out.println("CALCULATE THE SCORE FOR THIS SET : 3");
       System.out.println("SAVE THIS SCORE AS HIGH SCORE : 4");
       System.out.println("DISPLAY THE HIGH SCORE : 5");
       System.out.println("ENTER 'Q' TO QUIT THE PROGRAM : 6");
       System.out.print("Enter number ");
       String input = scan.nextLine();
       String[] parts = input.split(" ");
       System.out.println("");
       if(Integer.parseInt(parts[0]) == 1){
         ds.roll();
         System.out.println(ds);
       }
       else if(Integer.parseInt(parts[0]) == 2){
         if (parts.length == 2){
           System.out.println(ds.rollIndividual(Integer.parseInt(parts[1])));
         }
         else if (parts.length == 1){
           System.out.println("Forgot which one you want to roll");
         }
         else{
           System.out.println("Too many arguments you whack");
         }
       }
       else if(Integer.parseInt(parts[0]) == 3){
         System.out.println(ds.sum());
       }
       else if(Integer.parseInt(parts[0]) == 4){
         if(highscore < ds.sum()){
            highscore = ds.sum();
            System.out.println("Saved new highscore");
         }
         else {
           System.out.println("Old highscore is better");
         }
       }
       else if(Integer.parseInt(parts[0]) == 5){
         System.out.println(highscore);
       }
       else if(parts[0].equals("Q")){
         System.out.println("Goodbye");
         System.exit(0);
       }
       else{
         System.out.println("You whack");
       }
       System.out.println("");
     }
  }
}
