public interface IGameRepository {
   boolean save(Game game, String inputFile);
   Game load(String inputFile);
}
