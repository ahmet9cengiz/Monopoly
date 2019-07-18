import java.util.ArrayList;
import java.util.List;

public class NewGameRequest {
   private ArrayList<String> playerNames;

   NewGameRequest(ArrayList<String> playerNames){
      this.playerNames = playerNames;
   }

   public ArrayList<Player> createPlayers(){
      ArrayList<Player> players = new ArrayList<>();
      for(int i=0; i<playerNames.size(); i++){
         players.add(new Player(playerNames.get(i)));
      }
      return players;
   }
}
