public class CountTheDays{
    public static void main(String[] args) {
      try{
          if(args.length == 6){
            CalendarStuff x = new CalendarStuff();
            System.out.println(x.daysBetween(Long.parseLong(args[0]), Long.parseLong(args[1]), Long.parseLong(args[2]), Long.parseLong(args[3]),
            Long.parseLong(args[4]),Long.parseLong(args[5])) + " days");
          }
          else{System.out.println("Too many arguments");}
      }
      catch(Exception e){
        System.out.println("Data is invalid");
      }
    }
}
