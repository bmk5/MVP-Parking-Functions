import java.util.*;
import java.io.*;


/*
* This program takes in a file consisting of outcome maps and their corresponding
* sizes as inputs. It checks to see which outcome maps attain preference independence
* and counts them.
*/
public class PrefIndependenceCheck{
  /*
  * this function takes in the position of a car in the outcome map as well
  * as the outcome map itself as input. It counts the number of cars left of
  * the current car that are greater than the current car.
  * Returns the count.
  */
  public static int findBiggerCars(int pos, int[] outcome){
     int count = 0;
     int car = outcome[pos];

     for (int i = 0; i <= pos; i++){
        if (outcome[i] >= car) count++;
     }

     return count;
  }

  /*
  * This function takes a particular outcome map and its size as input
  * It returns true if the map achieves preference independence, False
  * otherwise
  */
  public static boolean equalProduct(String outcome, int order){
    int [] outMap = new int[outcome.length()];

    for (int i = 0; i < outcome.length() ;i++ ) {
       outMap[i] = Integer.parseInt( String.valueOf(outcome.charAt(i)) );
    }

    int product = 1;

    for (int i = 0;i < outcome.length() ;i++){
       int largerCars = findBiggerCars(i,outMap);
       product *= largerCars;
    }

    //check for preference independence`
    return product == order;

  }

  public static void main(String[] args) throws Exception {
    if (args.length == 0){
        System.out.println("Input not provided, check README file for directions");
        System.exit(1);
    }

    int num = Integer.parseInt(args[0]);

    if (num < 2 || num > 8){
      System.out.println("Input must be greater than 1 and less than 9,check README file for directions");
      System.exit(1);
    }

    //fetching the associated file
    String filename = "../OutcomeMaps/map" + num + ".csv";
    File myobj = new File(filename);
    Scanner scanner = new Scanner(myobj);

    int count = 0;

    //reading the file line by line
    // Note that the file is a csv file, each line consists of two comma separated values
    // The first value is the outcome map, and the second is the size of the outcome map
    while (scanner.hasNextLine()) {
       String data = scanner.nextLine();
       String [] arr = data.split(",");

       String outcome = arr[0];
       int size = Integer.parseInt(arr[1]);

       if (equalProduct(outcome,size)) count++;

    }
    scanner.close();
    System.out.println("TOTAL:" + count);
  }
}
