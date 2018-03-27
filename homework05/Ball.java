import java.text.DecimalFormat;

public class Ball {

    private static final double INCHES_FEET = 12;
    private static final double radius = 4.45/INCHES_FEET;
    private static final double poleX = 300;
    private static final double poleY = 300;

    private double xVal;
    private double yVal;
    private double xVelocity;
    private double yVelocity;
    private double tick = 1.0;
    private double totalSeconds = 0;
    private boolean hasCollided = false;

    public Ball(double x, double y, double xVel, double yVel){
        xVal = x;
        yVal = y;
        xVelocity = xVel;
        yVelocity = yVel;
        totalSeconds = 0;
    }

    public Ball(double x, double y, double xVel, double yVel, double time){
        xVal = x;
        yVal = y;
        xVelocity = xVel;
        yVelocity = yVel;
        tick = time;
        totalSeconds = 0;
    }

    public double convertToInches(double foot){
        return foot * INCHES_FEET;
    }

    public double convertToFeet(double inches){
        return inches / INCHES_FEET;
    }

    public boolean isPossible(Ball[] list){
        for(Ball ball:list){
            if (convertToInches(ball.xVelocity - (ball.xVelocity * .01) * this.tick) > 1 || convertToInches(ball.yVelocity - (ball.yVelocity * .01) * this.tick) > 1 ){
              return true;
            }
        }
        return false;
    }
    public void movement(Ball[] list){
        for(int i = 0; i < list.length; i++){
            Ball ball = list[i];
            ball.totalSeconds += tick;
            if(Math.abs(convertToInches(ball.xVelocity - (ball.xVelocity * .01) * this.tick)) > 1){
              ball.xVal += ball.xVelocity;
              ball.xVelocity -= (ball.xVelocity * .01) * this.tick;
            }
            if(Math.abs(convertToInches(ball.yVelocity - (ball.yVelocity * .01) * this.tick)) > 1){
              ball.yVal += ball.yVelocity;
              ball.yVelocity -= (ball.yVelocity * .01) * this.tick;
            }
        }
    }

    public String toString(){
      DecimalFormat df = new DecimalFormat("#.##");
      return "This ball is currently at (" + df.format(this.xVal) + ", " + df.format(this.yVal) + ") and a velocity in the x of " + df.format(this.xVelocity) + " and a velocity in the y of " + df.format(this.yVelocity) + " a time slice of " + df.format(this.tick);
    }

    public boolean collision(Ball ball1, Ball ball2){
        double diameter = radius*2;
        double distanceX = Math.abs(ball1.xVal - ball2.xVal);
        double distanceY = Math.abs(ball1.yVal - ball2.yVal);
        double distanceXP= Math.abs(poleX - ball1.xVal);
        double distanceYP = Math.abs(poleY - ball1.yVal);
        double distanceXP2= Math.abs(poleX - ball2.xVal);
        double distanceYP2 = Math.abs(poleY - ball2.yVal);

        if(diameter > distanceXP || diameter > distanceYP){
            System.out.println(ball1.yVal + " and collided with a pole");
            hasCollided = true;
            return false;
        }
        if(diameter > distanceXP2 || diameter > distanceYP2){
            System.out.println(ball2 + " and collided with a pole");
            hasCollided = true;
            return false;
        }
        if (diameter > distanceX || diameter > distanceY){
            hasCollided = true;
            return true;
        }
        return false;
    }

    public void collide(Ball[] list){
        for(int i = 0; i < list.length; i++){
          for(int j = i+1; j < list.length; j++){
              if(collision(list[i], list[j])){
                System.out.println("Ball " + (i+1) + " " + list[i] + " collided with Ball " + (j+1) + " " + list[j] + "\n");
              }
          }
        }
    }

    public boolean getCollided(){
        return hasCollided;
    }
    public double getTotalSeconds(){
        return totalSeconds;
    }

    public static void main( String args[] ) {
        Ball a = new Ball(1,1,1,1);
        Ball b = new Ball(1,1,2,2);
        Ball c = new Ball(2,2,0,0);
        Ball d = new Ball(2,2,2,0);
        Ball[] list = {a, b, c};
        Ball[] list2 = {c};
        Ball[] list3 = {d};
        if(a.isPossible(list)){
            System.out.println("Correct should be possible moves");
        }
        if(!(c.isPossible(list2))){
            System.out.println("Correct shouldn't be able to move");
        }
        if(d.isPossible(list3)){
            System.out.println("Correct should be able to move");
        }
        b.collide(list);
        b.movement(list);
        System.out.println(b);
    }
}
