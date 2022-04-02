import java.util.*;
import java.io.*;


/*
* This program takes in a file containing outcome maps as input.
* It counts how many outcome maps avoid the pattern '321' and '3412'.
* Finally, the count is the output to stdout.
*/

public class PatternCheck{

  /*
  * This method looks for the longest decreasing subsequence in the given array.
  * It returns an int which represents the length of the longest decreasing subsequence.
  */
  public static int LDS(int[] nums, int i, int prev)
     {
         // Base case: nothing is remaining
         if (i == nums.length) {
             return 0;
         }

         // case 1: exclude the current element and process the
         // remaining elements
         int excl = LDS(nums, i + 1, prev);

         // case 2: include the current element if it is smaller
         // than the previous element in LDS
         int incl = 0;
         if (nums[i] < prev) {
             incl = 1 + LDS(nums, i + 1, nums[i]);
         }

         // return the maximum of the above two choices
         return Integer.max(incl, excl);
     }

   /*
   * This method takes in an outcome map(in the form of an array) as input.
   * It checks for the existence of the '321' pattern in the array.
   * Returns true if the pattern exists, false otherwise.
   */
   public static boolean subDecreasing(int []arr){
      return LDS(arr,0,Integer.MAX_VALUE) >= 3;
   }

   /*
   * This method takes in an outcome map(in the form of an array) as input.
   * It checks for the existence of the '3412' pattern in the array.
   * Returns true if the pattern exists, false otherwise.
   */
  public static boolean subOther(int [] arr){
    // if length is less than 4, then the array does not contain the pattern '3412'
    if(arr.length < 4) return false;

    int count = 0;

    for (int i = 0; i < arr.length; i++){
       count = 1;
       int j = i;

       // due to the nature of the pattern, we create a list that stores the
       // first two integers of the pattern for comparison purposes.
       List<Integer> list = new ArrayList<>(2);
       list.add(arr[j]);

       while (count != 4){
         // in our first iteration, we look for a larger number,
         // since in '3412', 4 is larger than 3
         if (count == 1){
           j = findLarger(j,arr);

           // no larger integer exists, so pattern cannot start here
           if (j == -1) break;
           count++;
         }

         // in our second iteration, we look for a smaller number,
         // since in '3412', 1 is less than 3
         else if (count == 2){
           // think of this as adding the '4' in '3412'
           list.add(arr[j]);

           j = findLesser(list.get(0),j,arr);

           //if no smaller integer exists, pattern breaks
           if (j == -1) break;
           count++;
         }

         // in our third iteration, we look for a 'middle' number,
         // since in '3412', 2 is less than 3 and 4, but larger than 1.
         else if (count == 3){
          j = findMiddle(j,arr,list.get(0),list.get(1));

          //if no "middle" integer exists, pattern breaks
          if (j == -1) break;
          count++;
         }

       }
        //check if pattern exists at the end of all three iterations,
        if (count == 4) return true;
    }

    return false;
  }


  /*
  * This method takes in an array and an index within the array as input.
  * It then checks for the first integer, larger than the integer present at
  * the given index and returns the index of that larger integer.
  * If no such integer exists, it returns -1
  */
  public static int findLarger(int pos, int [] arr){
    int num = arr[pos];
    for (int i = pos+1; i < arr.length;i++){
      if (arr[i] > num ) return i;
    }

    return -1;
  }

  /*
  * This method takes in an array, an index within the array and two integers
  * as input. It then checks for the first integer, smaller than the two integers
  * but larger than the integer present at the given index. The method returns the index
  * said integer. If no such integer exists, it returns -1
  */
  public static int findMiddle(int pos, int [] arr, int num1,int num2){
    for (int i = pos+1; i < arr.length; i++){
        if (arr[i] > arr[pos] && arr[i] < num1 && arr[i] < num2) return i;
   }
    return -1;
  }

  /*
  * This method takes in an array, an index within the array and an int as input.
  * It then checks for the first integer, smaller than both the integer present at
  * the given index and the given number. The method then returns the index of
  * that smaller integer. If no such integer exists, it returns -1
  */
  public static int findLesser(int num, int pos, int [] arr){
    for (int i = pos+1; i < arr.length; i++){
        if (arr[i] < arr[pos] && arr[i] < num) return i;
   }
    return -1;
  }

  /*
  * This method takes in an outcome map as input.
  * It returns true if the map avoids both '321' and '3412'
  * otherwise it returns false.
  */
  public static boolean checkPattern(String map){
    //representation of outcome map as an array
    int [] outcome = new int[map.length()];

    for (int i = 0; i < map.length() ;i++ ) {
       outcome[i] = Integer.parseInt(String.valueOf(map.charAt(i)));
    }

     return !subDecreasing(outcome) && !subOther(outcome);
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

    // the corresponding file that contains outcome maps of length num
    String filename = "../MVPOutMaps/map" + num + ".csv";
    File myobj = new File(filename);
    Scanner scanner = new Scanner(myobj);
    int count = 0;

    //output file
    File output = new File("../PrefIndependence maps/" + "sizesEqual" + num + ".txt");
    FileWriter writer = new FileWriter(output);

    while (scanner.hasNextLine()) {

        String data = scanner.nextLine();
        String [] arr = data.split(",");
        String map = arr[0];


        if (checkPattern(map)) {
          writer.write(map);
          writer.write("\n");
          writer.flush();
          count++;
        }

   }
    writer.close();
    System.out.println("TOTAL:" + count);
  }

}
