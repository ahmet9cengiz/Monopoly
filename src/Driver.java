import java.util.Scanner;

public class Driver {
   public static void main(String[] args) {
      Game game = new Game();
      LandFactory landFactory = new LandFactory();

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


      game.addPlayer(new Player("Ahmet"));
      game.addPlayer(new Player("Mehmet"));
      game.addPlayer(new Player("Bahadir"));
      game.addPlayer(new Player("Onur"));
      game.addPlayer(new Player("Mert"));

      while(game.isContinuing()) {
         PlayerMovedResponse response = game.moveNextPlayer();
         if (response.needResponseForAction()) {
            game.passRequestFromMainToBoard(getRequestForAction(response));
         }
         else{
            printMessages(response);
         }
         System.out.println("Press any key to move the next player");
         try {
            System.in.read();
         }
         catch(Exception e){
            e.printStackTrace();
         }
      }

   }

   public static String askToBuyLand(){
      Scanner in = new Scanner(System.in);
      String input = "";
      try{
         input = in.nextLine();
      }
      catch(Exception e){
         e.printStackTrace();
      }
      return input;
   }

   public static NewArrivalRequest getRequestForAction(PlayerMovedResponse response){
      System.out.println(response.getPrintMessage());
      String input;
      do {
         input = askToBuyLand();
      } while (!input.equals("y") && !input.equals("n"));
      return new NewArrivalRequest(response.getPlayerId(),response.getLandId(),input);
   }

   public static void printMessages(PlayerMovedResponse response){
      System.out.println(response.getPrintMessage());
   }
}