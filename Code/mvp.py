# this program takes in a preference vector and spits out
# its corresponding outcome map as per the MVP rule.

import sys
#finds the first empty spot within the array
def findSpot(arr,start):
    for i in range(start, len(arr)):
        if arr[i] == 0 :
            return i
    return -1


# this method parks the given preference vector according
# to the MVP rule
def park_mvp(string):
    length = len(string)

    #preference vector
    p_vec= list(string)

    # initialzing our outcome map
    outMap = [0] * (length)

    for index,c in enumerate(p_vec):
        if not c.isdigit():
            break
        spot = int(c) -1

        # if spot is empty, we park the car there
        if outMap[spot] == 0:
            outMap[spot] = index+1
        else: # otherwise look for the next available spot
            old = outMap[spot]
            outMap[spot] = index + 1
            empty = findSpot(outMap,spot+1)
            outMap[empty] = old

    return ''.join(map(str,outMap))

def main():
    args = sys.argv[1:]
    print(park_mvp(args[0]))

if __name__ == '__main__':
    main()
