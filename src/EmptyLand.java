public class EmptyLand implements ILand {
   private int position;
   private boolean empty;
   private String name;

   EmptyLand() {
      this.empty = true;
      this.name = "";
      this.position = -1;
   }

   public LandType getEnumType(){
      return LandType.EMPTY_LAND;
   }

   public String getName(){
      return this.name;
   }

   public int getPosition() {
      return position;
   }

   public void setPosition(int position) {
      this.position = position;
   }

   public boolean isEmpty() {
      return empty;
   }

   public PlayerMovedResponse newArrival(Player player){
      return new PlayerMovedResponse(player.getId(),this.position,false,"This land is closed for interaction");
   }

}
