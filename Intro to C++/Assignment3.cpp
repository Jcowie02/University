#include <stdio.h>
#include <math.h>

int main()
{
	float time, initVel, initAng, initH;
	scanf("%f, %f, %f, %f", &time, &initVel, &initAng, &initH);
	
	initAng *= (3.14/180);  //converting degrees to radians
	
	//one main condition testing if time is greater or less than zero
	if (time >= 0) {
		printf("Projectile released\n");
		
		float position = (-0.5*9.81*time*time) + (initVel*sin(initAng)*time) + initH;  // height of projectile given
		printf("Position: %0.2f meters\n", position);
		
		float velocity = (-9.81*time) + (initVel*sin(initAng));  // vertical velocity of projectile given
		printf("Velocity value: %0.2f meters/second\n", velocity);

		if (velocity >= 0)  // testing projectile trajectory
			printf("Projectile ascending\n");
		else
			printf("Projectile descending\n");
			
		// vertical position can be greater, equal, or less than initial vertical position regardless of ascedning/descending trajectory
		// so it has a separate condition
		
		if (position >= initH)
			printf("Projectile above initial position\n");
		else 
			printf("Projectile below initial position\n");
		
	}
	else
		printf("Projectile not released\n");
	
}