import java.util.*;


public class ParkingFunction {

  //this is the array representation of our parkinf function
  protected int [] prefVector;

  protected int inversions;
  protected int totalBumps;
  protected int sumDisplacementMVP;// total displacement under the MVP rule
  protected int sumDisplacementClassic;// total displacement under Classic rule


  protected String inverse; // inverse of the parking function only when it's a permutation
  protected String mvpOutcome;//outcome under the MVP rule
  protected String classicOutcome;// outcome under the Classic rule

  protected boolean isPerm;
  protected boolean involution;

  protected List<Integer> unluckyCarsMVP;//contains the cars that were bumped under the MVP rule
  protected List<Integer> luckyCarsMVP;// contains the cars that did not get bumped under MVP rule

  protected List<Integer> unluckyCarsClassic;//contains the cars that were bumped under the classic rule
  protected List<Integer> luckyCarsClassic;// contains the cars that did not get bumped under the classic rule


  public ParkingFunction(String pf){
     assert (!pf.equals("") && pf != null) : "String is either empty or null";// make sure string isn't empty or null
     pf = pf.trim();
     unluckyCarsMVP = new ArrayList<Integer>();
     luckyCarsMVP = new ArrayList<Integer>();
     unluckyCarsClassic = new ArrayList<Integer>();
     luckyCarsClassic = new ArrayList<Integer>();

     totalBumps = 0;

     initPrefVector(pf);

     mvpOutcome = park_mvp();
     classicOutcome = park_classic();
     inversions = countInversions();
     sumDisplacementMVP = findDisplacementMVP();
     sumDisplacementClassic = findDisplacementClassic();
     isPerm = isPermutation();
     inverse = findInverse();
     involution = isInvolution();
  }

  /*
  * this method initializes the array representation of the parking function
  * as a preference vector
  */

  public void initPrefVector(String pf){
    prefVector = new int[pf.length()];
    for (int i = 0; i < prefVector.length ;i++ ) {
       prefVector[i] = Integer.parseInt( String.valueOf(pf.charAt(i)) );
    }

  }

 /*
 * this is a helper functions that sets all the spots in an array
 * to -1 to represent empty spaces
 */
  public void initializeEmpty(int [] arr){
   //initialize all spots to -1 to represent empty spaces
    for (int i = 0; i < arr.length ;i++ ) {
         arr[i] = -1;
    }
  }

  /*
  * this method returns true if the parking function is an involution, false otherwise
  */
  public boolean isInvolution(){
    // involutions are in the context of permutations so if the parking function
    // is not a permutation then it is not an involution.
     if(!isPerm) return false;

     String pvector = stringOutcome(prefVector);
     String outMap = mvpOutcome.toString(); //could have used classical outcome, doesn't matter because it's a permutation

     return pvector.equals(outMap);
  }

  /*
  * This method parks the given parking function using the MVP rule and returns the
  * resulting outcome map
  */
  public String park_mvp(){
    // this will be the representation of our outcome map
    int [] result = new int[prefVector.length];

    //initialize to all empty spaces
    initializeEmpty(result);

    for (int i = 0;i < prefVector.length ;i++ ) {
      //since pf is 1-indexed, we set the preferences to be 0-indexed
      int spot = prefVector[i] - 1;

      //if we meet an empty spot, we park our car in the spot
      if ( result[spot] == -1){
        result[spot] = i+1;
      }
      else{//otherwise, we bump the current car,traverse the array to find an empty spot to park it
        int car = result[spot];
        result[spot] = i + 1;
        int empty = findSpot(result,spot+1);
        result[empty] = car;
        totalBumps++;
      }

    }

    return stringOutcome(result);
  }


 /*
 * this method is invoked when a given car finds its spot taken.
 * the method takes in the array, and an integer representing an index into an array.
 * it returns the index of the first empty spot, represented by -1.
 */
  public int findSpot(int [] array,int start){
    for (int i = start; i < array.length ;i++ ) {
       if (array[i] == -1) return i;
    }
     return -1;

  }

  /*
  * this method takes an array and returns a formatted string representation of the array
  */
  public String stringOutcome(int[] result){
   StringBuilder sb = new StringBuilder();
   for (Integer num : result) sb.append(num);
   return sb.toString();
  }

  /*
  * This method parks the given parking function using the classical rule and returns the
  * resulting outcome map
  */
  public String park_classic(){
    //  this will be the representation of our outcome map

     int [] result = new int[prefVector.length];
     initializeEmpty(result);


     for (int i = 0; i < prefVector.length ;i++ ) {
        int spot = prefVector[i] - 1;
        if ( result[spot] == -1){
          result[spot] = i+1;
        }
        else{
          int empty = findSpot(result,spot);
          result[empty] = i+1;
        }
     }

     return stringOutcome(result);
  }

  /*
  * This method returns true if the given parking function is a permutation
  */
  public boolean isPermutation(){
      Set<Integer> set = new HashSet<>();
      for (Integer num : prefVector) set.add(num);
      //if set size does not equal the preference vector size, then we know there were repeated characters
      return set.size() == prefVector.length;
  }

  /*
  * this method returns the inverse of the parking function
  */
  public String findInverse(){
    if (!isPerm) return "Not a permutation";
    return mvpOutcome.toString();
  }


  /*
  * This method returns the total number of inversions in the parking function.
  */
  public int countInversions(){
    /*
    * this method works as follows: it calculates the number of integers
    * less than the current integer from the index of the current integer,
    * for example: 31245, at index 0, we have 1 and 2 less than 3 so that's 2 inversions.
    * we do so at each index to find the total number of inversions. {can be optimized :) }
    */

    int inversions = 0;

    for (int i = 0; i < prefVector.length;i++){
      int number = prefVector[i];
       for (int j = i+1; j < prefVector.length;j++ ) {
            if (prefVector[j] < number) inversions++;
       }
    }

    return inversions;
  }


  /*
  * Returns the displacement of the parking function under the MVP rule
  */
  public int findDisplacementMVP(){
    return findSumDisplacement(mvpOutcome.toString(),0);
  }

  /*
  * Returns the displacement of the parking function under the Classic rule
  */
  public int findDisplacementClassic(){
    return findSumDisplacement(classicOutcome.toString(),1);
  }

  /*
  * this method returns the total displacement that occured within the parking function
  * under the given rule. The tag represents a parkig rule, 0 for MVP, 1 for classic
  */
  public int findSumDisplacement(String outcome, int tag){
     int displacement = 0;

     // we use this to store where each car parked in the outcome map;
     HashMap<Integer,Integer> map = new HashMap<>();

     // filling in our hashmap
     for (int i = 0; i < outcome.length(); i++){
         String s = String.valueOf(outcome.charAt(i));
         map.put(Integer.parseInt(s),i+1);
     }

     // we compare where each car parked with its preference in the prefVector
     // the difference between the two is the displacement for car i
     for (int i = 0; i < prefVector.length;i++){
       int car = i+1;
       int pos_parked = map.get(car);
       int pos_preference = prefVector[i];
       int diff = pos_parked - pos_preference;

       //if a car was bumped then it was unlucky, otherwise if it wasn't we consider it lucky
       if (diff == 0){
         if (tag == 0) luckyCarsMVP.add(car);
         else luckyCarsClassic.add(car);
       }
       else {
         if (tag == 0) unluckyCarsMVP.add(car);
         else unluckyCarsClassic.add(car);
       }

       displacement += diff;
     }

    return displacement;

  }


 /*
 * Returns whether or not the given function is an involution
 */
  public boolean getIsInvolution(){
    return involution;
  }

 /*
 * Returns the inverse of the given parking function.
 * The function returns -1 if the given parking function is not a permutation
 */
  public String getInverse(){
    return inverse;
  }

  /*
  * Returns the number of inversions of the given parking function.
  */
  public int getInversions(){
    return inversions;
  }

  /*
  * Returns the outcome map of the given parking function under the MVP rule
  */
  public String getMVP(){
    return mvpOutcome;
  }

  /*
  * Returns the outcome map of the given parking function under the classical rule
  */
  public String getClassic(){
    return classicOutcome;
  }

  /*
  * Returns the total number of bumps that occurred when parking the
  * given parking function under the MVP rule;
  */
  public int getTotalBumps(){
    return totalBumps;
  }

  /*
  * Returns the list of cars that got bumped within this parking function
  * under the MVP rule
  */
  public List<Integer> getUnluckyCarsMVP(){
    return unluckyCarsMVP;
  }

  /*
  * Returns the list of cars that DID NOT get bumped within this parking function
  * under the MVP rule
  */
  public List<Integer> getLuckyCarsMVP(){
    return luckyCarsMVP;
  }

  /*
  * Returns the list of cars that got bumped within this parking function
  * under the CLassic rule
  */
  public List<Integer> getUnluckyCarsClassic(){
    return unluckyCarsClassic;
  }

  /*
  * Returns the list of cars that DID NOT get bumped within this parking function
  * under the CLassic
  */
  public List<Integer> getLuckyCarsClassic(){
    return luckyCarsClassic;
  }

  /*
  * Returns the total displacement within this function under the MVP rule
  */
  public int getDispacementMVP(){
    return sumDisplacementMVP;
  }

  /*
  * Returns the total displacement within this function under the Classic rule
  */
  public int getDispacementClassic(){
    return sumDisplacementClassic;
  }

  /*
  * Returns the string representation of the parking function
  */
  public String toString(){
    return stringOutcome(prefVector);
  }

  /*
  * This method defines equality between parking functions
  * Two parking functions are equal iff they have the same preference vector
  */
  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (this == obj) return true;

    if (getClass() != obj.getClass()) return false;

    ParkingFunction pf = (ParkingFunction) obj;
    return Arrays.equals(this.prefVector,pf.prefVector);
  }

  /*
  * Returns a hash code for this Parking function.
  * The hashcode is given by the hashCode of the string representation of the parking function.
  */
  @Override
  public int hashCode(){
    String str = this.toString();
    return str.hashCode();
  }
}
