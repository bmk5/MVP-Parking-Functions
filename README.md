# MVP-Parking-Functions
CONTENTS OF THIS FILE
---------------------

 * Introduction
 * Directories
    * ClassicOutMaps 
    * Combinations
    * InvolutionsData
    * MVPPF
    * MVPOutMaps
    * PrefIndependence maps
    * Results_Excel
    * Code
 * Program Files and use







PROGRAM FILES AND USE
---------------------
As stated before, in this section we explore the programs in the *Code* directory and how to use them. Most of these programs have been written in Java and a few in python. You do not need to have any prior experience in programming as we explain in detail how the code is used. However, if you do want to understand more about the code, you are free to do that, as the code is (I hope) well documented. Before we start, you'll need to open your terminal(Linux) or Command Prompt(Windows). From there you will need to navigate to the MVP-Parking-Functions directory, from where you'll go into the Code directory where all these programs are located. To learn how to navigate within the terminal, take a look at this short 4-minute tutorial: <a taget="_blank" title="hello" href="https://www.youtube.com/watch?v=xp6oHaStwww">Terminal Navigation in Linux</a>. For Windows users, I'd suggest watching the Linux tutorial, since there are a lot of similarities there, then this: <a taget="_blank" title="hello" href="https://www.youtube.com/watch?v=7ABkcHLdG_A">Terminal Navigation in Windows</a>.

#### *buildMatrix.java* ####
This program is used to build a matrix from a preference vector provided as input from the terminal. That is to say, **YOU** provide the input. Using this program as an example, we show how to do that. Before running any program, we always need to *compile* it. You do not need to understand what this means, just that you have to do it before running a program. We use the above program as an example. To do so, type the following in your terminal or command prompt:
                  <p align="center">
                       ![](/Snippets/compile1.png)
                  </p>
With this, we have compiled the program. **ENSURE** that a *buildMatrix.class* file has newly popped up among the files in your directory. The *.class* file is very important to the program, without it, you cannot run your program. We can now run our program. Suppose I want the matrix associated with the preference vector *1122*, then, we type the following in the terminal:
                   <p align="center">
                       ![](/Snippets/build.png)
                  </p>
Notice that there are spaces between the three pieces of text. That is important otherwise an error occurs. Hitting enter should output the following:
                   <p align="center">
                       ![](/Snippets/output1.png)
                  </p>
You can keep running the program as many times as you want. You do not need to compile the program each time you want to run it unless you have changed the code.
                   <p align="center">
                       ![](/Snippets/examples1.png)
                  </p>

#### *drawpath.py* ####
This program builds a lattice path from a preference vector provided as input from the terminal. The program is written in python. Python is a bit different from java in that all the steps shown before can be done in one step as follows. Suppose we want the lattice path that corresponds to *1133*, then we type:
                  <p align="center">
                       ![](/Snippets/draw.png)
                  </p>
Since we expect a graphic, a pop-up window will appear with the resultant lattice path as shown:
                   <p align="center">
                       ![](/Snippets/example2.png)
                  </p>
To run the program again with a different input, you will need to close the pop-up window, then type the command again.

#### *PrefIndependenceCheck.java* ####
This program essentially checks to see which outcome maps in *S_n* attain preference independence. It outputs the total number that attain preference independence. It takes a single input *n* corresponding to *S_n*. For example:
                     <p align="center">
                       ![](/Snippets/equal.png)
                    </p>
                     
The input *5* implies that we want all the outcome maps in *S_5* that attain preference independence. The input to the program can only be an integer ranging from 2-8 inclusive. Any other input results in error as shown below:
                     <p align="center">
                       ![](/Snippets/error1.png)
                     </p>

#### *filterParkingFn.java* ####
This program 'filters' out all the MVP parking functions from all possible combinations from the set \{1,2,...,n\}. The output is a file, whose name is *pf_n.txt*. The file contains all the 'filtered' out MVP parking functions and is written to the *MVPPF* directory. For example, running:
                     <p align="center">
                       ![](/Snippets/filter.png)
                    </p>
creates a new file: *pf5.txt* in the *MVPPF* folder containing all the MVP parking functions of length *5*. If one already exists, the program overwrites it, so no need to worry about redundant files.

#### *getPrefVector.java* ####
This program takes in an outcome map as input, and outputs all the preference vectors that map to the given outcome map. For example, suppose I want to know all the preference vectors that map to *431625*:
                     <p align="center">
                       ![](/Snippets/prefVectors.png)
                    </p>
As in the program prior, the input to the program can only be an integer ranging from 2-8 inclusive. Any other input results in error.

#### *CheckSelfInverse.java* ####
This program counts how many of the outcome maps in *S_n* are their own inverses, and returns the total count. The input to this program is a single integer corresponding to *n*. For example:
                    <p align="center">
                       ![](/Snippets/inverse.png)
                    </p>
This says that we count all the outcome maps in *S_6* that are their own self inverses.Again, the input to the program can only be an integer ranging from 2-8 inclusive. Any other input results in error.


#### *Park_mvp.java* ####
This program parks all the parking functions of length *n* according to the MVP parking rule. It keeps track of the outcome maps and their sizes, and writes that to a corresponding csv file in the *MVPOutMaps* folder. For example, running:
                  <p align="center">
                     ![](/Snippets/mvp.png)
                  </p>
implies that we should park all the parking functions of length 4 under the MVP rule. The resultant file is stored in the *MVPOutMaps* folder as *map4.csv*. Again, the input to the program can only be an integer ranging from 2-8 inclusive. Any other input results in error.

#### *Park_classic.java* ####
This program parks all the parking functions of length *n* according to the Classical parking rule. It keeps track of the outcome maps and their sizes, and writes that to a corresponding csv file in the *ClassicOutMaps* folder. For example, running:
                  <p align="center">
                     ![](/Snippets/classic.png)
                  </p>
implies that we should park all the parking functions of length 7 under the classical rule. The resultant file is stored in the *ClassicOutMaps* folder as *map7.csv*. Again, the input to the program can only be an integer ranging from 2-8 inclusive. Any other input results in error.

#### *PatternCheck.java* ####
This program checks and counts which outcome maps in *S_n* avoid the patterns *321* and *3412*. The input is a single integer corresponding to *n*.It writes those that avoid the patterns into the *PrefIndependence maps* directory. For instance: 
                   <p align="center">
                     ![](/Snippets/pattern.png)
                   </p>
Implies that we check which outcome maps in *S_5* avoid *321* and *3412*. We see that the total is *34*. The resultant file is stored in the *PrefIndependence maps* folder as *sizesEqual5.txt*.  

#### *mvp.py* ####
This program works just like **Park_mvp.java**. The only difference is that this program takes a preference vector as an input and spits out the resultant outcome map as output. For instance:
                   <p align="center">
                     ![](/Snippets/mvp_py.png)
                   </p>
