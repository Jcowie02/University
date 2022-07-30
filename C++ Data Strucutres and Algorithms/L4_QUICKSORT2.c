//L4_QUICKSORT.C
//Jackson, Cowie; David, Laughton
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int verbose = 0;
long unsigned int   swaps = 0;

#define MAXINDEX    19          // how much actual data

void printarrowatposition (int posn)
// Prints a V - a down arrow - where the pivot value will be
{
    int i;
    for (i=0; i<posn; i++)      printf ("     ");
    printf ("   V\n");
}//printarrowatposition()

void swap (int *a, int g, int h)
// Swap values at locations g and h in array a.
// If global verbose is set, reports that swap takes place.
// Global counter swaps is incremented by 1.  User needs to remember to
// reset swaps before starting code that swap count is required.
{
    int  t = a[g];      // temporary to hold one value being swapped
    if (verbose)
        printf ("Swap elements at %d (%d) and %d (%d)\n",g,a[g],h,a[h]);
    a[g] = a[h];
    a[h] = t;
    swaps++;
} //swap()

void printarray (char *msg, int *a, int min, int max, int numperline)
// Print contents of array a from position min to position max. If msg is not
// NULL, prints msg on stdout before printing array. Parameter numperline -
// if not 0 - indicates how many numbers to print per line.
{
    // Check first for dumbness
    if ((a != NULL) && (numperline > 0))    {
        int     i, num2print;
        if (msg != NULL)    printf ("%s\n", msg);
        // Compute total number of data items to print
        num2print = 1 + max-min;
        for (i=0; i<num2print; i++) {
            // See if we have to finish current line and start next line.
            if (i && ((i % numperline) == 0))   putchar ('\n');
            // Print number - remember counter goes from 0 but items to
            // print start at min.
            printf (" %4d", a[i+min]);
        }
        // Close off last line
        putchar ('\n');
    } else  {
        fprintf (stderr, "Error in printarray() parameters\n");
    }
}//printarray()

int read_int_file (char *fname, int **parray)
// Function reads file containing positive integer values and stores data in
// order in an array.  The data is terminated by a negative value.
// Function uses malloc() to get enough memory for the array, and returns
// the size of the array in integers (not bytes). The pointer to the array
// is returned in the place pointed to by parray.
// If parray is NULL, function reads file and determines how many integers are
// in the file, but does not create an array and store the values.
{
    FILE    *fp;
    int     *pa;            // pointer to array
    int     count;
    int     rslt;
    int     data;

    fp = fopen (fname, "r");
    if (fp == NULL) {
        fprintf (stderr, "ERROR: Cannot open %s\n", fname);
        return -1;
    }

    // Step 1. Read through array just counting how many numbers there are
    count = 0;
    while (fscanf (fp, "%d", &data) == 1)   {
        if (data == -1) break;
        count++;
    }
    // report if asked
    if (verbose)    {
        printf ("File %-15s contains %d data items\n", fname, count);
    }

    // Step 2. If caller provided a parray pointer then we reread the file
    // storing data as we go.
    if (parray != NULL) {
        int     k;
        rewind (fp);        // back to beginning of file
        // gat an array big enough to store all the data
        pa = (int *)malloc(count * sizeof(int));
        if (pa == NULL) {
            fprintf (stderr, "ERROR: Cannot allocate space for array\n");
            fclose (fp);
            return -1;
        }
        // Got an array - fill it
        for (k=0; k<count; k++) {
            if (fscanf (fp, "%d", pa+k) != 1)   {
                fprintf (stderr, "ERROR: Problem reading item %d from file\n",
                         k);
                fclose (fp);
                return -1;
            }
        }//endfor
    }//endif parray non-NULL

    // All data read. Close file and provide pointer
    fclose (fp);
    *parray = pa;
    return count;
}//read_int_file()


int partition (int  *a,  int left, int right)
// Using locations a[left..right], partition fragment by picking a[left] as
// separator value, then working through bottom and top halves of the array,
// arranging - by swapping values if necessary - that all values smaller than
// the separator value are in the bottom part and all values larger are in
// the top part.
{
    int ll, rr, pivotval;
    ll = left+1;
    rr = right;
    // choose pivot to be leftmost location
    pivotval = a[left];
    if (verbose)
        printf ("Partition around value in position %d - %d\n", left, a[left]);
    while (ll < rr) {
        // if one on left less than pivot, leave it alone
        if(a[ll] <= pivotval)   { ll++; continue;}
        // if one on right greater than pivot, leave it
        if(a[rr] > pivotval)    { rr--; continue;}
        // both left and right on wrong side - swap them
        swap(a, ll, rr);
    }//endwhile
    // we stop when rr and ll collide. Place pivot value such that everything
    // to left is less and everything to right is same or greater.
    if (a[ll] < pivotval)   {
        swap(a, ll, left);
    } else  {
        swap(a, --ll, left);
    }
    return ll;
}//partition()

int insertionsort (int *a, int min, int max)
// Work through array of numbers, placing each one in the correct place. Note
// that insertion sort differs from bubblesort in that with insertion sort, one
// value is moved to its relative place. In bubble sort, the largest value is
// found as the checking keeps choosing the larger of two values to keep
// moving.
{
    int     i, j;
    int     rslt = -1;


    // initialize swaps so we know how many exchanges take place
    int swaps = 0;

    // Outer for loop works through values from min to max. Effectively,
    // we start by making the data in position relative 0 a sorted array containing
    // one element, then adding the rest of the array, one by one, to the
    // sorted part
    for (i = min; i <=max; i++) {
        // Now, i indicates which new element in the array is going to be
        // inserted into its correct position. At the end of each loop, one
        // more value is moved, and all of the bottom is ordered.
        for (j = i; j > min && (a[j - 1] > a[j]); j--) {
            swap(a, j, j - 1);
        }//endfor
    }//endfor
    rslt = swaps;

    return rslt;
}//insertionsort()

int modifiedQuicksort (int *a,  int left, int right)
{
    if (left < right) //checks if the data still needs to be sorted
    {
        if(right - left > 32){
            int  pivotndx = partition (a, left, right); //create partition of the data
            // function uses recursion like the normal quicksort function to traverse through the dataset, but there
            // is an extra condition built in that checks if the data set gets small enough to use insertion sort.
            modifiedQuicksort(a, left, pivotndx-1);
            modifiedQuicksort(a, pivotndx+1, right);
        }else{ //function will run insertion sort if the size of the data is less than the specified 32
            insertionsort(a,left,right);
        }
    }
    return swaps;
}//modifiedQuicksort()

int quicksort (int *a,  int left, int right)
{
    if (verbose)
        printf ("\nQuicksort: left = %d   right = %d\n", left, right);
    if (left < right)    {
        int  pivotndx = partition (a, left, right);
        if (verbose)    {
            printf ("    Completed partition, pivot at: %d\n", pivotndx);
            printarrowatposition (pivotndx);
            printarray (NULL, a, 0, MAXINDEX, 1000);
        }
        quicksort (a, left, pivotndx-1);
        quicksort (a, pivotndx+1, right);
        // uses recursion to iterate through the data set until everything has been sorted.
    }
    return swaps;
}//quicksort()


int main(){

    //initialized some variable that will be used later on in the main program
    int         rslt;
    clock_t     start, end, start2, end2;
    int         *pa; // pointer to array;
    int         size;
    swaps = 0;

    //test holds characters that are outputted as strings to a text file
    char *test[4][3];
    test[0][0] = "Cowie, Jackson";
    test[0][1] = "                 Size of Dataset";
    test[0][2] = "100000";
    test[1][0] = "";
    test[1][1] = "                   Elapsed time";
    test[1][2] = " Number of swaps";


    FILE *fp;
    char output[] = "LAB4_PART3.TXT";
    fp = fopen(output, "w");

    //print the predetermined strings to the text file using a for loop and the rest of the data will be appended
    //to the text file using fprintf.
    for(int i=0;i<2;i++) {
        if(i>0) fprintf(fp, "\n");
        for(int j=0;j<3;j++){
            if(test[i][j] != "") fprintf(fp, "%s  ", test[i][j]);
            else fprintf(fp, "%s       ", test[i][j]);
        }
    }


    // the code below reads the text file into a variable called size, times and runs quicksort, then prints
    // the results to the text file. Swaps is then reset and the same is done for the modified quicksort.
    size = read_int_file("RANDOM.TXT", &pa);
    start = clock();
    rslt = quicksort (pa, 0, size-1);           //OK
    end = clock();
    fprintf(fp, "\n");
    fprintf(fp, "Quick Sort                     %ld            %d", end-start, rslt);

    swaps = 0; //reset swaps
    size = read_int_file("RANDOM.TXT", &pa);
    start2 = clock();
    rslt = modifiedQuicksort(pa, 0, size-1);            //OK
    end2 = clock();
    fprintf(fp, "\n");
    fprintf(fp, "Modified Quick Sort            %ld            %d", end2-start2, rslt);

    fclose(fp); // close file
}