#include <stdio.h>
#include <math.h>

#define N 12 //Number of coordinate values

int main()
{
    float coordinates[N][2]; //Initialize array
    float avgX = 0.0;
    float avgY = 0.0;
    float newArray[N][N];

    for (int i = 0; i < N; i++) {
        scanf("%f, %f", &coordinates[i][0], &coordinates[i][1]);

        // conditions to get the right output syntax (i.e. commas, etc.)
        if (i == (N / 2))
            printf("\n(%0.1f,%0.1f),", coordinates[i][0], coordinates[i][1]);
        else if (i == N - 1)
            printf("(%0.1f,%0.1f)\n", coordinates[i][0], coordinates[i][1]);
        else
            printf("(%0.1f,%0.1f),", coordinates[i][0], coordinates[i][1]);

        // adds each index of the input to calculate averages in the X and Y
        avgX += coordinates[i][0];
        avgY += coordinates[i][1];
    }

    avgX /= N;
    avgY /= N;
    printf("\nAverage center: (%0.1f,%0.1f)\n\n", avgX, avgY);

    // above code takes care of the first two output requirements

    for (int x = 0; x < N; x++) { // master for loop that goes through the rows in the 'newArray'
        printf("Distance from point %d to\n", x+1);

        for (int y = 0; y < N; y++) { //initiates the double for loop to go assign average distances to the newArray
            // from the distances between points in the 'coordinates' array

            for (int j = 0; j < N; j++) {
                newArray[y][j] = sqrt((pow(coordinates[y][0] - coordinates[j][0], 2))
                                      + (pow(coordinates[y][1] - coordinates[j][1], 2)));
                //the above equation calculates the hypotenuse as if the points were on a cartesian plane
            }
            if (y == N-1) // condition to select the correct output syntax for the corresponding distances
                // (end of each for loop iteration 'y')
                printf("%d.- %0.1f\n",y+1, newArray[x][y]);
            else
                printf("%d.- %0.1f, ",y+1, newArray[x][y]);
        }
    }

    printf("\n"); // formatting

    for (int v = 0; v < N; v++) { // this for loop goes though the values of the newArray and adds all the values
        // of each row into the variable 'sum', printing out the average, then resetting 'sum' to zero
        float sum = 0;
        for (int q = 0; q < N; q++){
            sum += newArray[v][q];
        }
        printf("Average distance from point %d: %0.1f km\n", v+1, sum/(N-1));
    }
}
