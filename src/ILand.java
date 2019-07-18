import java.io.Serializable;

public interface ILand extends Serializable {

   int getPosition();
   boolean isEmpty();
   PlayerMovedResponse newArrival(Player player);
   String getName();
   void setPosition(int position);
   LandType getEnumType();

   enum LandType {
      LAND, EMPTY_LAND
   }
}