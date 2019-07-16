import java.util.ArrayList;

public class Game {
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

   public void getNextPlayer(int playerId) {
      if(currentPlayerId == players.size()-1)
         currentPlayerId = 0;
      else
         currentPlayerId++;
   }

   public void addPlayer(Player player){
      players.add(player);
      player.setId(players.indexOf(player));
   }


   public PlayerMovedResponse moveNextPlayer(){
      getNextPlayer(currentPlayerId);
      printPlayerBalances();
      return gameBoard.movePlayer(this.players.get(this.currentPlayerId), dice.roll());

   }

   public void passRequestFromMainToBoard(NewArrivalRequest request){
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