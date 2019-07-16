public class NewArrivalRequest {
   private int playerId;
   private int landId;
   private String input;

   NewArrivalRequest(int playerId, int landId, String input){
      this.playerId = playerId;
      this.landId = landId;
      this.input = input;
   }

   public int getPlayerId() {
      return playerId;
   }

   public int getLandId() {
      return landId;
   }

   public String getInput() {
      return input;
   }
}
