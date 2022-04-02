# MVP-Parking-Functions
CONTENTS OF THIS FILE
---------------------

 * Introduction
 * Directories
    * Combinations
    * InvolutionsData
    * MVPPF
    * OutcomeMaps
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
With this, we have compiled the program. Ensure that a *buildMatrix.class* file has newly popped up among the files in yuor directory. From there, we can now run our program. Suppose I want the matrix associated with the preference vector *1122*, then, we type the following in the terminal:
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

