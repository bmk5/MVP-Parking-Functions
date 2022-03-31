# this program takes in a parking function,(ideally one that is a Motzkin path)
# and outputs its corresponding Motzkin path.


import matplotlib.pyplot as plt
import sys

# we construct the path by abstractly counting the number of ones
# in each column of the corresponding matrix without constructing the matrix itself
def drawPath(string):
    arr = list(map(int,string))
    mat = [0] * len(string)

    # here we are calculating the number of ones in each column
    # of the corresponding matrix.
    for num in arr:
        mat[num-1] += 1

    x = [0] * (len(string) + 1)
    y = [0] * (len(string) + 1)


    curr_x,curr_y = 0,0
    index = 1
    step = 1

    # we now plot the paths
    for num in mat:
        # if column is all zeroes, we have a downward diagonal
        if num == 0:
           x[index] = curr_x + step
           y[index] = curr_y - step

           curr_y -= step

        # if column has exactly one 1, we have a horizontal line
        if num == 1:
            x[index] = curr_x + step
            y[index] = curr_y

       # if column has more than one 1, we have an upward diagonal
        if num > 1:
            x[index] = curr_x + step
            y[index] = curr_y + step
            curr_y += step

        index+=1
        curr_x += step


    # draw the path
    plt.plot(x,y)
    plt.xticks(range(0, len(string) + 1))
    plt.grid()
    plt.show(block=False)


def main():
    args = sys.argv[1:]
    drawPath(args[0])
    plt.show()


if __name__ == '__main__':
    main()
