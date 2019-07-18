import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
   private String name;
   private int id;
   private double balance;
   private int position;
   private ArrayList<Land> landsOfPlayer;


   Player(String name){
      this.name = name;
      this.id = 0;
      this.balance = 1000;
      this.position = 0;
      this.landsOfPlayer = new ArrayList<>();
   }

   public void setPosition(int newPos){
      this.position = newPos;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public int getId() {
      return id;
   }

   public double getBalance() {
      return balance;
   }

   public int getPosition() {
      return position;
   }

   public ArrayList<Land> getLands() {
      return landsOfPlayer;
   }

   public void deposit(double amount){
      this.balance += amount;
   }

   public void withdraw(double amount){
      this.balance -= amount;
   }
}