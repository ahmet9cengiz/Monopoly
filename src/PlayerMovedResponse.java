public class PlayerMovedResponse{
   private boolean needResponseForAction; //newArrival
   private int playerId;
   private int landId;
   private String printMessage;

   PlayerMovedResponse(int playerId, int landId, boolean needResponseForAction, String printMessage){
      this.playerId = playerId;
      this.landId = landId;
      this.needResponseForAction = needResponseForAction;
      this.printMessage = printMessage;
   }

   public boolean needResponseForAction() {
      return needResponseForAction;
   }

   public int getPlayerId() {
      return playerId;
   }

   public int getLandId() {
      return landId;
   }

   public String getPrintMessage() {
      return printMessage;
   }
}
