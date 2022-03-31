import java.util.*;


public class OutcomeMap{

   //list of parking functions that map to the outcome under the MVP parking rule
   public PriorityQueue<ParkingFunction> prefVectorsMVP;

   //list of parking functions that map to the outcome under the Classical parking rule
   public PriorityQueue<ParkingFunction> prefVectorsClassic;

   //String representation of our outcome map
   public String outcome;

   public OutcomeMap(String str){
      outcome = str.trim();
      assert isPerm(outcome) : "The given string cannot be an outcome map";

      prefVectorsMVP = new PriorityQueue<>(new PFComparator());
      prefVectorsClassic = new PriorityQueue<>(new PFComparator());
   }



   public boolean isPerm(String str){
     Set<Character> set = new HashSet<>();
     for (int i = 0; i < str.length(); i++) set.add(str.charAt(i));
     //if set size does not equal the preference vector size, then we know there were repeated characters
     return set.size() == str.length();
   }
  /*
  * This method takes in a string that is a parking function and adds it to the
  * list of parking functions that map to this outcome map under the MVP rule
  */
   public void addMVP(ParkingFunction pf){
      prefVectorsMVP.add(pf);
   }

   /*
   * This method takes in a string that is a parking function and adds it to the
   * list of parking functions that map to this outcome map under the Classical rule
   */
   public void addClassic(ParkingFunction pf){
      prefVectorsClassic.add(pf);
   }

   /*
   * This method takes in a string and returns true if the given
   *  string represents a permutation
   */
   // public boolean isPerm(String outcome){
   //   ParkingFunction pf = new ParkingFunction(outcome);
   //   return pf.isPerm;
   // }


   /*
   * Returns the list of parking functions that map to this outcome map
   * under the MVP parking rule
   */
   public PriorityQueue<ParkingFunction> getPrefVectorsMVP(){
     return prefVectorsMVP;
   }

   /*
   * Returns the list of parking functions that map to this outcome map
   * under the classical parking rule
   */
   public PriorityQueue<ParkingFunction> getPrefVectorsClassic(){
     return prefVectorsClassic;
   }

   /*
   * This method defines equality between outcome maps
   */
   @Override
   public boolean equals(Object obj){
      if (obj == null) return false;
      if (this == obj) return true;

      if (getClass() != obj.getClass()) return false;

      OutcomeMap other = (OutcomeMap) obj;
      return  toString().equals(other.toString());
   }

   /*
   * Returns a hash code for this Parking function.
   * The hashcode is given by the hashCode of the string representation of the outcome map.
   */
   @Override
   public int hashCode(){
     return outcome.hashCode();
   }

   /*
   * Returns the string representation of our outcome map
   */
   public String toString(){
     return outcome;
   }
}
