import java.util.*;
import java.lang.*;
import java.io.File;

/*
* This program takes in parking functions as inputs.
* It parks the parking functions according to the classical parking
* rule and stores the resulting outcome maps in a map.
* The map counts the occurences of these outcome maps.
* Finally, the outcome maps and their sizes are printed to stdout.
*/

public class Park_Classic{

  /*
  * This method takes in a preference vector in form of a String as input.
  * It returns the resultant parking order of the parking function,
  * that is, the outcome map, according to the classical parking rule
  */
  public static String park_classical(String data){
     Park_mvp parking = new Park_mvp();

     // representation of the preference vector as an array
     int [] p_vector = new int[data.length()];
     for (int i = 0; i < data.length() ;i++ ) {
        p_vector[i] = Integer.parseInt( String.valueOf(data.charAt(i)));
     }

     // initializing our outcome map
     int [] result = new int[p_vector.length];
     parking.init_array(result);


     for (int i = 0; i < p_vector.length ;i++ ) {
        int spot = p_vector[i] - 1;

        //if the given spot is unoccupied, the car parks there
        if ( result[spot] == -1){
          result[spot] = i+1;
        }
        else{//otherwise we look for the next empty spot
          int empty = parking.findSpot(result,spot);

          // if no spot exists, then the given string is not a parking function
          if (empty < 0)  return null;
          result[empty] = i+1;
        }
     }

     //converting outcome map to string representation
     String newStr = "";
     for (int i = 0; i < result.length ;i++ ) newStr+=result[i];


     return newStr;
  }



  public static void main(String[] args) throws Exception {
    if (args.length == 0){
        System.out.println("Input not provided, check README file for directions");
        System.exit(1);
    }

    int num = Integer.parseInt(args[0]);

    // input must be valid
    if (num < 2 || num > 8){
      System.out.println("Input must be greater than 1 and less than 9,check README file for directions");
      System.exit(1);
    }


    //fetching the associated file
    String filename = "../MVPPF/pf" + num + ".txt";
    File myobj = new File(filename);
    Scanner scanner = new Scanner(myobj);

    // map for storing outcome maps and their sizes
    TreeMap<String,Integer> map = new TreeMap<>();


      while (scanner.hasNext()) {
         String data = scanner.next();
         String outcome = park_classical(data);

         map.put(outcome, map.getOrDefault(outcome,0) + 1);
     }

     //print to stdout
     for (String outcome : map.keySet()){
       System.out.println(outcome + "," + map.get(outcome));
     }
  }

}
