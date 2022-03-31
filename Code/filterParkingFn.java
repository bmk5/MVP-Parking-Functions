import java.util.*;
import java.io.*;


/*
* Given a file consisting of combinations of length n, this class
* filters out the parking functions among those combinations and writes
* it to a file.
*/
public class filterParkingFn{

  /*This method checks returns true if the given preference vector
  * is a parking function, false otherwise
  */
  public static boolean isParkingFunction(String str){
    str = str.trim();
    char [] ch = str.toCharArray();
    Arrays.sort(ch);

    for (int i = 0; i < ch.length; i++){
       if (Character.getNumericValue(ch[i]) > i+1) {
         return false;
       }
    }
    return true;
  }

  /*
  * This method checks for a number present in
  * the given file name so as to produce the corresponding
  * output file
  */
  public static int findNum(String filename){
   char[] chars = filename.toCharArray();
   for(char c : chars){
      if(Character.isDigit(c)){
          return Integer.valueOf(Character.getNumericValue(c));
      }
   }

    System.out.println("Invalid file/directory name format");
    System.exit(-1);
    return -1;
 }

  public static void main(String[] args)  throws Exception{
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
    String filename = "../Combinations/combos" + num + ".txt";
    File myobj = new File(filename);
    Scanner scanner = new Scanner(myobj);

    //creating the output file
    File output = new File("pf" + num + ".txt");
    FileWriter writer = new FileWriter(output);


    while (scanner.hasNext()) {
        String data = scanner.next();

        // if parking function, then write to file
         if (isParkingFunction(data)){
           writer.write(data);
           writer.write("\n");
           writer.flush();
         }
   }

   writer.close();
  }

}
