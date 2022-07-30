#include <string>
#include <stdio.h>

int main(){
    float rockWeight, firstDepthValue, depthIncrement, firstKValue, kIncrement; // these values can have decimals
    int numofDepthValues, numofKValues; // I assumed that the number of values would have to be whole

    // inputting values using stdio library
    printf("Input rock unit weight (kN/m3):\n");
    scanf("%f", &rockWeight);
    printf("Input number of depth values:\n");
    scanf("%d", &numofDepthValues);
    printf("Input first depth value (m):\n");
    scanf("%f", &firstDepthValue);
    printf("Input depth increment (m):\n");
    scanf("%f", &depthIncrement);
    printf("Input number of k values:\n");
    scanf("%d", &numofKValues);
    printf("Input first k value (m):\n");
    scanf("%f", &firstKValue);
    printf("Input k increment (m):\n");
    scanf("%f", &kIncrement);

    float depthValues[numofDepthValues], verticalStresses[numofDepthValues], kValues[numofKValues],
            horizontalStresses[numofDepthValues][numofKValues];
    // define the arrays that need to be made. horizontalStresses is 2D as it depends on the number of depth values
    // and the number of k values

    printf("Vertical stress values (kPa):\n");

    // This for loop assigns depth values using the first depth values and the increment between them,
    // and the verticalStresses uses the corresponding depth value and constant rock weight
    for (int i = 0; i < numofDepthValues; i++){
        depthValues[i] = firstDepthValue + i*(depthIncrement);
        verticalStresses[i] = depthValues[i] * rockWeight;
        printf("%.0f\n", verticalStresses[i]);
    }
    // This for loop assigns k values in the same method as above. I used a separate for loop because the kValues
    // array depends on the number of k values, not the number of depth values
    for (int j = 0; j < numofKValues; j++){
        kValues[j] = firstKValue + j*kIncrement;
    }
    // Separate for loop and nested for loop for assigning horizontal stresses. This array depends on the
    // verticalStresses array and the kValues, so it was also done separately.
    for (int k = 0; k < numofDepthValues; k++){
        for (int q = 0; q < numofKValues; q++){
            horizontalStresses[k][q] = verticalStresses[k] * kValues[q]; // simple formula given in assignment
            // multiplies vertical stress value to corresponding k value
        }
    }

    std :: string filename = "outputfileA.txt"; // need to include std here as I did not include "using namespace std"
    FILE* fileOutput = fopen(filename.c_str(), "w"); // writing file
    for (int x = 0; x < numofDepthValues; x++){
        if (x > 0)
            fprintf(fileOutput, "\n"); // proper formatting in text file
        fprintf(fileOutput, "%.0f,", depthValues[x]);
        for (int y = 0; y < numofKValues; y++){ // another nested for loop to output each horizontal stress in each row
            if (y == numofKValues - 1) // formatting for commas
                fprintf(fileOutput, "%.0f", horizontalStresses[x][y]);
            else
                fprintf(fileOutput, "%.0f,", horizontalStresses[x][y]);
        }
    }
}