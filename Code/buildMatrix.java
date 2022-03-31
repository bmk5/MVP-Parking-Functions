/*
* This class constructs a matrix from a given parking function
* Input is provided from the terminal and the matrix is displayed
* as the output
*/

public class buildMatrix{

  /*
  * this function constructs the matrix and outputs it to the console
  */
  public static void build(String pf){
    int size = pf.length();
    int [][] matrix = new int [size][size];

    // the row represents the car
    // the column is the preferred parking spot of the car which we set to 1.
    for (int i = 0 ;i< pf.length(); i++ ) {
      String s = String.valueOf(pf.charAt(i));
      int pos = Integer.valueOf(s);

      matrix[i][pos-1] = 1;
    }

    for (int row = 0; row < matrix.length; row++) {
       for (int col = 0; col < matrix[row].length; col++) {
           System.out.printf("%4d", matrix[row][col]);
       }
       System.out.println();
   }
  }


  public static void main(String[] args) {
    if (args.length == 0){
        System.out.println("Input not provided, check README file for directions");
        System.exit(1);
    }
    
    build(args[0]);
  }

}
