#include <stdio.h>
#include <math.h>


int main()

{
	float pLength, pWidth, pDepth, pRadius; 
	scanf("%f, %f, %f, %f", &pLength, &pWidth, &pDepth, &pRadius);

	//Surface Area
	
	//Corners
	
	float eq1 = 8*((3.14*(pow(pRadius, 2)))/4);  // top and bottom faces of the corners
	float eq2 = 4*pDepth*((3.14*(pRadius*2))/4); // sides of the corners
	
	//Top and Bottom Faces
	
	float eq3 = 2*((pLength - 2*(pRadius))*(pWidth-2*(pRadius))); // reduced size square in the middle
	float eq4 = 4*pRadius*(pWidth- (pRadius*2)); // rectangles that didn't get included in the in the cut off square (horizontal)
	float eq5 = 4*pRadius*(pLength - (pRadius*2)); // rectangles that didn't get included in the in the cut off square (vertical)
	
	//Side Rectangles
	
	float eq6 = 2*pDepth*(pLength - (pRadius*2)); // vertical 
	float eq7 = 2*pDepth*(pWidth - (pRadius*2)); // horizontal
	
	float surfaceArea = eq1 + eq2 + eq3 + eq4 + eq5 + eq6 + eq7;
	
	printf("iPhone surface area:\n%.0f mm2\n", surfaceArea);
	
	//Volume
	
	//Middle Rectangle
	
	float eq8 = pDepth*((pLength - 2*(pRadius))*(pWidth-2*(pRadius)));
	
	//Side Rectangles (not included in the middle rectangle)
	
	float eq9 = 2*pDepth*pRadius*(pWidth- (pRadius*2));
	float eq10 = 2*pDepth*pRadius*(pLength - (pRadius*2));
	
	//Corner Cylinders
	
	float eq11 = 3.14*(pow(pRadius, 2))*pDepth;

	float volume = eq8 + eq9 + eq10 + eq11;
	
	printf("iPhone volume:\n%.0f mm3\n", volume);
	
	//Material Cost
	
	float materialCost = volume*0.0183;
	
	printf("iPhone material cost:\n$%.0f\n", materialCost);
	
	//Battery Cost
	
	float batteryCost = 0.45*volume*0.0095;
	
	printf("battery cost:\n$%.0f\n", batteryCost);
}
