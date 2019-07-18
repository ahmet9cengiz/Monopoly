import java.io.*;

public class SerializableGameRepository implements IGameRepository{

   @Override
   public boolean save(Game game, String inputFile) {
      try {
         ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(inputFile)));
         oos.writeObject(game);
         oos.close();
         return true;
      }
      catch (Exception e) {
         System.out.println(e.getMessage());
         return false;
      }
   }

   @Override
   public Game load(String inputFile) {
      try {
         ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(inputFile)));
         Game game = (Game) ois.readObject();
         ois.close();
         return game;
      }
      catch (Exception e) {
         System.out.println(e.getMessage());
         return null;
      }
   }
}
