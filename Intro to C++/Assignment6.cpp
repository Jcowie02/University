#include <stdio.h>
#include <stdlib.h>
// constant values of compressive/tensile strength
#define compressiveStrength 30
#define tensileStrength 5

void calculations(float elasticMod, float strain); // main function

int main() {
    float elasticMod, strain;
    scanf("%f, %f", &elasticMod, &strain); // modulus and strain user input
    calculations(elasticMod, strain); // run the function
    return 0;

}

void calculations(float elasticMod, float strain) {
    float stress = elasticMod*strain;
    printf("Stress value: %0.2f MPa\n", stress); // initial output value from simple calculation

    // the below conditional statements determine if the bridge is in tension or compression, and subsequently if
    // the absolute value of the stress is greater than the tensile/compressive strength. The appropriate statements
    // are outputted for each scenario.
    if (stress > 0){
        printf("Bridge deck in tension\n");
        if (abs(stress) > tensileStrength)
            printf("Danger: tensile strength exceeded\n");
        else printf("Bridge deck safe\n");
    }
    else if (stress < 0){
        printf("Bridge deck in compression\n");
        if (abs(stress) > compressiveStrength)
            printf("Danger: compressive strength exceeded\n");
        else printf("Bridge deck safe\n");
    }
    else printf("No stress in bridge\n");
}