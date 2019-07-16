public class LandFactory {
   public ILand createLand(ILand.LandType landType, String name, double price, double rent){
      if(landType == ILand.LandType.EMPTY_LAND){
         return new EmptyLand();
      }
      else
         return new Land(name, price, rent);
   }
}
