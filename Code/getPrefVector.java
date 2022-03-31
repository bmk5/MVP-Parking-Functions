import java.util.*;
import java.lang.*;
import java.util.Scanner;
import java.io.*;

/*
* The program below is used to obtain all parking functions that map
* to a certain outcome map, which is given as the input
*/

public class getPrefVector{

  /*
  * This method checks whether the given input is indeed a possible outcome map
  * An outcome map will always be a permutation. Thus given an input string, This
  * method returns true if the string is a permutation and false otherwise.
  */
  public static boolean isPermutation(String perm){
      Set<Integer> set = new HashSet<>();
      for (int i = 0; i < perm.length(); i++){
          set.add(Character.getNumericValue(perm.charAt(i)));
      }
      //if set size does not equal the preference vector size, then we know there were repeated characters
      return set.size() == perm.length();
  }

  public static void main(String[] args) throws Exception {
    if (args.length == 0){
        System.out.println("Input not provided, check README file for directions");
        System.exit(1);
    }

    //the outcome map
    String perm = args[0];


    if (!isPermutation(perm)){
      System.out.println("Input is not a permutation, thus cannot be an outcome map");
      System.exit(1);
    }

    // the corresponding file that contains parking functions of length perm.length()
    String filename = "../MVPPF/pf" + perm.length() + ".txt";

    Scanner scanner =  new Scanner( new BufferedReader( new FileReader(filename)));
    Park_mvp parkMvp = new Park_mvp();

    //find the parking functions that map to the above outcome map
    while (scanner.hasNext()) {
       String data = scanner.next();
       String result = parkMvp.park_mvp(data);
        if (perm.equals(result)) System.out.println(data);
    }

  }


}
