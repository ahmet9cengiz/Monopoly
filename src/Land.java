public class Land implements IInteractiveLand {
   private String name;
   private double price;
   private double rent;
   private int position;
   private boolean onMarket;
   private boolean empty;
   private Player owner;

   Land(String name, double price, double rent) {
      this.name = name;
      this.price = price;
      this.rent = rent;
      this.onMarket = true;
      this.empty = false;
      this.owner = null;
      this.position = -1;
   }

   public void setOwner(Player owner) {
      this.owner = owner;
   }

   public Player getOwner() {
      return owner;
   }

   public void changeMarketStatus(boolean status) {
      onMarket = status;
   }

   public String getName() {
      return name;
   }

   public double getPrice() {
      return price;
   }

   public double getRent() {
      return rent;
   }

   public int getPosition() {
      return position;
   }

   public void setPosition(int position) {
      this.position = position;
   }

   public boolean isOnMarket() {
      return onMarket;
   }

   public boolean isEmpty() {
      return empty;
   }

   public LandType getEnumType(){
      return LandType.LAND;
   }

   public boolean isGonnaPayRent(Player player) {
      if (!player.equals(this.getOwner()))
         return true;
      else
         return false;
   }

   public void collectRent(Player player) {
      double rentAmount = this.getRent();
      player.withdraw(rentAmount);
      this.getOwner().deposit(rentAmount);
   }

   public boolean isAbleToBuyTheLand(Player player){
      if(player.getBalance() >= this.getPrice()){
         return true;
      }
      else
         return false;
   }

   public void sellLand(Player player) {
      player.withdraw(this.getPrice());
      player.getLands().add(this);
      this.changeMarketStatus(false);
      this.setOwner(player);
      System.out.println(player.getName() + " has paid " + this.price + " and bought " + this.name);
   }

   public PlayerMovedResponse newArrival(Player player) {
      String printMessage;
      if (this.isOnMarket()) {
         if(isAbleToBuyTheLand(player)) {
            printMessage = "Would you like to buy(y/n): \nName: " + this.name + ", Price: " + this.price + ", Rent: " + this.rent;
            return new PlayerMovedResponse(player.getId(), this.position, true, printMessage);
         }
         else{
            printMessage = "You don't have enough money to buy the land";
            return new PlayerMovedResponse(player.getId(), this.position,false, printMessage);
         }
      }
      else {
         if (isGonnaPayRent(player)) {
            collectRent(player);
            printMessage = player.getName() + " has paid " + this.rent + " to " + this.owner.getName();
            return new PlayerMovedResponse(player.getId(), this.position, false, printMessage);
         } else
            return new PlayerMovedResponse(player.getId(), this.position, false, "This land is already your property");
      }
   }

   public void getNewArrivalRequest(String input, Player player){
      if(input.equals("y")){
         sellLand(player);
      }
      else{
         //do nothing
      }
   }
}