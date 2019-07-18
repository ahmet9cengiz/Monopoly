import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
   private ArrayList<Player> players;
   private GameBoard gameBoard;
   private Dice dice;
   private boolean continuing;
   private int currentPlayerId;

   Game() {
      players = new ArrayList<>();
      gameBoard = new GameBoard();
      dice = new Dice();
      continuing = true;
      currentPlayerId = -1;
   }

   Game(ArrayList<Player> players) {
      this.players = players;
      gameBoard = new GameBoard();
      dice = new Dice();
      continuing = true;
      currentPlayerId = -1;
   }

   public void getNextPlayer() {
      if(currentPlayerId == players.size()-1)
         currentPlayerId = 0;
      else
         currentPlayerId++;
   }

   public void setPlayerIDs(){
      for(int i=0; i< players.size(); i++){
         players.get(i).setId(i);
      }

   }


   public PlayerMovedResponse moveNextPlayer(){
      getNextPlayer();
      printPlayerBalances();
      return gameBoard.movePlayer(this.players.get(this.currentPlayerId), dice.roll());

   }

   public void passRequestFromServiceToBoard(NewArrivalRequest request){
      this.gameBoard.passRequestFromGameToLand(request, this.players.get(request.getPlayerId()));
   }

   public boolean isContinuing() {
      return continuing;
   }

   public void setContinuing(boolean continuing) {
      this.continuing = continuing;
   }

   public ArrayList<Player> getPlayers() {
      return players;
   }

   public GameBoard getGameBoard() {
      return gameBoard;
   }

   public void printPlayerBalances(){
      for(int i=0; i<players.size(); i++){
         System.out.printf("%100s%n",players.get(i).getName()+": "+players.get(i).getBalance());
      }
   }
}