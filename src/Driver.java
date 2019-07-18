import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
   public static Scanner in = new Scanner(System.in);

   public static void main(String[] args) {
      GameService gameService = new GameService(new SerializableGameRepository());

      System.out.println("Would you like to play a new game(0), or load an existing game(1)");

      int input = in.nextInt();
      if (input == 0) {
         NewGameRequest newGameRequest = new NewGameRequest(getPlayerNamesInput());
         gameService.buildNewGame(newGameRequest);
      } else if (input == 1) {
         System.out.println("Please enter the name of the input file: ");
         String inputFile = in.next();
         gameService.load(inputFile);
      }

      promptEnterKey();

      while(gameService.isGameContinuing()) {
         PlayerMovedResponse response = gameService.moveNextPlayer();
         if (response.needResponseForAction()) {
            gameService.passRequestFromMainToGame(getRequestForAction(response));
         }
         else{
            printMessages(response);
         }
         System.out.println("Press press 1 to save the game, press 0 to quit, and press anything else to continue");
         try {
            String decision = in.nextLine();
            if(decision.equals("0")){
               gameService.setGameContinuing(false);
            }
            else if(decision.equals("1")){
               System.out.println("Please enter a file name which you want the game to be saved to: ");
               String inputFile = in.nextLine();
               gameService.save(inputFile);
            }
            else{
            }
         }
         catch(Exception e) {
            e.printStackTrace();
         }
      }

   }

   public static String askToBuyLand(){
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

   public static void promptEnterKey() {
      System.out.println("Press \"ENTER\" to continue...");
      Scanner console = new Scanner(System.in);
      console.nextLine();
   }

   public static ArrayList<String> getPlayerNamesInput(){
      ArrayList<String> players = new ArrayList<>();
      players.add("Ahmet");
      players.add("Mehmet");
      players.add("Bahadir");
      players.add("Onur");
      players.add("Mert");
      return players;
   }
}