import java.io.*;
import java.util.*;

/*
* This program takes in parking functions as inputs.
* It parks the parking functions according to the MVP parking
* rule and stores the resulting outcome maps in a map.
* The map counts the occurences of these outcome maps.
* Finally, the outcome maps and their sizes are printed to stdout.
*/

public class Park_mvp{

  /*
  * This method takes an array as input.
  * It initializes the array with all -1's.
  */
  public static void init_array(int [] array){
     for (int i = 0; i < array.length ;i++ ) {
       array[i] = -1;
     }

  }

  /*
  * This method takes an array and an index within the given array as input.
  * Starting from the given index, to the end of the array, the method looks
  * for the first "empty" spot in the array, marked by -1. It returns the index
  * of the first such spot. If no such index exists, it returns -1.
  */
  public static int findSpot(int [] array,int start){

    for (int i = start; i < array.length ;i++ ) {
       if (array[i] == -1) return i;
    }
     return -1;

  }

  /*
  * This method takes in a preference vector in form of a String as input.
  * It returns the resultant parking order of the parking function,
  * that is, the outcome map, according to the MVP parking rule
  */
  public static String park_mvp(String data){
    // representation of the preference vector as an array
    int [] p_vector = new int[data.length()];
    for (int i = 0; i < data.length() ;i++ ) {
       p_vector[i] = Integer.parseInt( String.valueOf(data.charAt(i)) );
    }

    // initializing our outcome map
    int [] result = new int[p_vector.length];
    init_array(result);


    for (int i = 0;i < p_vector.length ;i++ ) {
      int spot = p_vector[i] - 1;

      //if the given spot is unoccupied, the car parks there
      if ( result[spot] == -1){
        result[spot] = i+1;
      }
      else{ //otherwise we look for the next empty spot
        int car = result[spot];
        result[spot] = i + 1;
        int empty = findSpot(result,spot+1);

        // if no spot exists, then the given string is not a parking function
        if (empty < 0)  return null;

        result[empty] = car;
      }

    }

    //converting outcome map to string representation
    String newStr = "";
    for (int i = 0; i < result.length ;i++ ) {
      newStr+=result[i];
    }

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
         String outcome = park_mvp(data);

         map.put(outcome, map.getOrDefault(outcome,0) + 1);
     }

     //creating the output file
    File output = new File("../MVPOutMaps/" + "map" + num + ".csv");
    PrintWriter writer = new PrintWriter(output);

    //write to file
    for (String outcome : map.keySet()){
      StringBuilder sb = new StringBuilder();
      int size = map.get(outcome);
      sb.append(outcome);
      sb.append(",");
      sb.append(size);
      sb.append("\n");
      writer.write(sb.toString());
    }

    writer.close();
 }

}
