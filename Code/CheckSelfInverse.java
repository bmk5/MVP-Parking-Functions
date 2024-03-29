import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
* This program takes in a file that consists of outcome maps.
* It then checks to see the number of outcome maps which are self-inverses
* It outputs the number of such outcome maps
*/


public class CheckSelfInverse{

  /*
  * This method takes in an outcome map as input, and returns the inverse
  * of the given outcome map.
  */
  public static String findInverseOfMap(String data){
    //transform the string into an array
    int [] arr = new int[data.length()];
     for (int i = 0; i < data.length() ;i++ ) {
        arr[i] = Integer.parseInt( String.valueOf(data.charAt(i)) ) - 1;
     }

     //find the inverse
     int[] res = new int [arr.length];
     for (int i =0; i < res.length; i++) res[arr[i]] = i;


     StringBuilder sb = new StringBuilder();
     for (Integer i : res) sb.append(i+1);

     return sb.toString();
  }
  
  /*
   * This method takes in a number as a parameter.
   * It sources the associated file with that number and reads each line of the file
   * to ascertain the outcome maps that are self inverses.
   */
  public static void readOutcomeMapsFromFile(int num) throws IOException {

    //fetching the associated file
    String filename = "../MVPOutMaps/map" + num + ".csv";
    File myobj = new File(filename);
    Scanner scanner = new Scanner(myobj);

    int count = 0;

    File output = new File("../InvolutionsData/" + "selfInverse" + num + ".txt");
    FileWriter writer = new FileWriter(output);

    //reading the file line by line
    // Note that the file is a csv file, each line consists of two comma separated values
    // The first value is the outcome map, and the second is the size of the outcome map
      while (scanner.hasNextLine()){
         String data = scanner.nextLine();
         String [] arr = data.split(",");

         //we only need the outcome map
         String outcome = arr[0];
         String inverse = findInverseOfMap(outcome);

         if (inverse.equals(outcome)) {
           writer.write(outcome);
           writer.write("\n");
           writer.flush();
           count++;
         }
         scanner.close();
         writer.close();
     }

     System.out.println("TOTAL:" + count);
    
  }
  public static void main(String[] args) throws Exception{
    //check to see that there exists input
    if (args.length == 0){
        System.out.println("Input not provided, check README file for directions");
        System.exit(1);
    }

    int num = Integer.parseInt(args[0]);

    //input must be valid
    if (num < 2 || num > 8) {
      System.out.println("Input must be greater than 1 and less than 9,check README file for directions");
      System.exit(1);
    }
    
    readOutcomeMapsFromFile(num);
    
  }
}
