import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
* The program below is used to obtain all parking functions that map
* to a certain outcome map, which is given as the input
*/

public class GetPreferenceVectors{

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
  
    //find the parking functions that map to the above outcome map
    while (scanner.hasNext()) {
       String data = scanner.next();
       String result = Park_mvp.park_mvp(data);
        if (perm.equals(result)) System.out.println(data);
    }
    scanner.close();
  }


}
