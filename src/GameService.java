import java.util.ArrayList;

public class GameService {
   private IGameRepository gameRepository;
   private Game game;
   private LandFactory landFactory;

   public GameService(IGameRepository gameRepository){
      this.gameRepository = gameRepository;
      landFactory = new LandFactory();
   }

   private void createGameBoard(){
      game.getGameBoard().addLand(landFactory.createLand(ILand.LandType.LAND,"Istanbul", 400, 70));
      game.getGameBoard().addLand(landFactory.createLand(ILand.LandType.LAND,"Bursa", 300, 55));
      game.getGameBoard().addLand(landFactory.createLand(ILand.LandType.LAND,"Ankara", 320, 65));
      game.getGameBoard().addLand(landFactory.createLand(ILand.LandType.EMPTY_LAND,"",0,0));
      game.getGameBoard().addLand(landFactory.createLand(ILand.LandType.LAND,"Izmir", 270, 45));
      game.getGameBoard().addLand(landFactory.createLand(ILand.LandType.LAND,"Antalya", 260, 43));
      game.getGameBoard().addLand(landFactory.createLand(ILand.LandType.LAND,"Tekirdag", 200, 35));
      game.getGameBoard().addLand(landFactory.createLand(ILand.LandType.EMPTY_LAND,"",0,0));
      game.getGameBoard().addLand(landFactory.createLand(ILand.LandType.LAND,"Kocaeli", 200, 30));
      game.getGameBoard().addLand(landFactory.createLand(ILand.LandType.LAND,"Mersin", 230, 35));
      game.getGameBoard().addLand(landFactory.createLand(ILand.LandType.LAND,"Adana", 250, 40));
      game.getGameBoard().addLand(landFactory.createLand(ILand.LandType.EMPTY_LAND,"",0,0));
      game.getGameBoard().addLand(landFactory.createLand(ILand.LandType.LAND,"Erzurum", 200, 25));
   }

   public void buildNewGame(NewGameRequest newGameRequest){
      game = new Game(newGameRequest.createPlayers());
      game.setPlayerIDs();
      createGameBoard();
   }

   public boolean save(String inputFile){
      return gameRepository.save(this.game, inputFile);
   }

   public void load(String inputFile){
      this.game = gameRepository.load(inputFile);
   }

   public PlayerMovedResponse moveNextPlayer(){
      return game.moveNextPlayer();
   }

   public void passRequestFromMainToGame(NewArrivalRequest request) {
      game.passRequestFromServiceToBoard(request);
   }

   public boolean isGameContinuing(){
      return game.isContinuing();
   }

   public void setGameContinuing(boolean bool){
      game.setContinuing(bool);
   }
}
