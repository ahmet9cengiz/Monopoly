import java.io.Serializable;
import java.util.ArrayList;

public class GameBoard implements Serializable {

   private ArrayList<ILand> landMap;

   GameBoard() {
      landMap = new ArrayList<>();

   }

   public void addLand(ILand newLand){
      landMap.add(newLand);
      newLand.setPosition(landMap.indexOf(newLand));
   }

   public ArrayList<ILand> getLandMap() {
      return landMap;
   }

   public ILand getLandPlayerIsOn(Player player){
      return this.landMap.get(player.getPosition());
   }

   public int findNextLandPos(Player player, int dice){
      int position = getLandPlayerIsOn(player).getPosition();
      for(int i=0; i<dice; i++){
         if(position == this.landMap.size()-1){
            position = -1;
         }
         position++;
      }
      return position;
   }

   public PlayerMovedResponse movePlayer(Player player, int dice){
      int position = findNextLandPos(player, dice);
      player.setPosition(position);
      System.out.println(player.getName()+" has moved "+dice+" cells ahead to "+landMap.get(position).getName());
      return landMap.get(position).newArrival(player);
   }

   public void passRequestFromGameToLand(NewArrivalRequest request, Player player){
      if(!getLandPlayerIsOn(player).isEmpty()) {
         IInteractiveLand interactiveLand = (IInteractiveLand) getLandPlayerIsOn(player);
         interactiveLand.getNewArrivalRequest(request.getInput(), player);
      }
   }

}