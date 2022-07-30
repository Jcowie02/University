#include <stdio.h>
#include <math.h>

#define PI 3.14
#define g 9.81

int main(int argc, char **argv)
{
	float initialHeight, tankRad, pipeRad;
	int finalT;
	scanf("%f, %f, %f, %d", &initialHeight, &tankRad, &pipeRad, &finalT);

	float heightHolder = initialHeight/2; // will be used later to check if tank is half-drained
	int mayorStatement;

	float halfwayHeight;
	float velocity;
	float initialVol = PI*(tankRad*tankRad*initialHeight); // volume of a right cylinder
	float avgVel = 0; // counter
    float lostVol;

    //NOTE: I decided to keep the initialHeight variable the same and keep updating it for simplicity
    //(instead of creating a new variable to update in the 'for' loop)

	for (int t = 0; t <= finalT; t++) {

		velocity = 0.5*(sqrt(2*g*initialHeight));  // given in the question
		avgVel += velocity;  // add to the average velocity counter

		lostVol = velocity*(PI*pipeRad*pipeRad); // given
		initialVol -= lostVol; // update volume
		initialHeight = initialVol/(PI*tankRad*tankRad); // updating the height by dividing the new volume by the
		// tank radius and PI (i.e. rearranging initialVol equation)

        if (initialHeight <= heightHolder) // variable will be set to 1 once the height is less than half
            // the initial height (see above for height holder)
            mayorStatement = 1;
        else
            mayorStatement = 0;

        if (t == 1800) // creates variable holder at 30 minutes of draining
            halfwayHeight = initialHeight;
		else
			continue;
	}

	avgVel /= finalT; // need to divide by total time to find the average velocity

	// print values
	printf("Average velocity value: %0.2f m/s\n", avgVel);
	printf("Height value after 30 min: %0.2f m\n", halfwayHeight);
	printf("Height value after %d seconds: %0.2f m\n", finalT, initialHeight);

    if (mayorStatement == 1) // once the loop has completed, this condition will tell the user if the tank is half drained or not
	    printf("Tank half drained\n");
    else
	    printf("Tank still not half drained\n");
}