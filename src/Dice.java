import java.util.Random;

public class Dice {
   private Random rand;
   Dice(){
      rand = new Random();
   }

   public int roll() {
      int first = rand.nextInt(6) + 1;
      int second = rand.nextInt(6) + 1;
      return first+second;
   }
}