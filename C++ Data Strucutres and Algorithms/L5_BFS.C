// Cowie, Jackson; Laughton, David
// L5_BFS.C [171031]
// Breadth-first search of graph

/* --README--------------------------------------------------------------------
Source code used for teaching purposes in course:
ELEC278, Queen's University, Kingston, Fall semester 2021.
This code is provided without warranty of any kind. It is the responsibility
of the user to determine the correctness and the usefulness of this code for
any purpose.

Author:  David F. Athersych, P.Eng.
All rights reserved. This code is intended for students registered in the
course and the semester listed above.

See LICENCE.MD for restrictions on the use of this code.
---------------------------------------------------------------------------- */


#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Global variable controls amount of console output. Set to non-zero if you
// want lots of information while program runs.
// (This variable is updated by an assignment in main().)
int     verbose = 0;

typedef int Que_data;       // create queue filled with ints
#include "quearray.h"

#define MAXVERTEX 7             // Set maximum number of vertices
#include "GraphBasic.h"


void breadthFirstSearch (int start)
// Do breadth-first search of global graph
{
    int     i;
    int     unvisitedVertex;
    Queue   *ToVisit = create_queue (MAXVERTEX+1);

    //mark first node as visited
    Vertices[start]->visited = GREY;

    //display vertex
    displayVertex (start);

    //insert vertex index in queue
    insert (ToVisit, start);

    while(!isQueueEmpty(ToVisit)) {
        //get vertex from front of unvisited queue
        int tempVertex;
        removefront (ToVisit, &tempVertex);

        //no adjacent vertex found
        while((unvisitedVertex = getAdjUnvisitedVertex(tempVertex)) != -1) {
            Vertices[unvisitedVertex]->visited = GREY;
            displayVertex(unvisitedVertex);
            insert(ToVisit, unvisitedVertex);
        }
    }

    putchar ('\n');
    printf ("List of vertices that were not visited\n");
    for (i=0; i<vertexCount; i++)
        if (Vertices[i]->visited == GREEN)  displayVertex (i);
    putchar ('\n');

    //queue is empty, search is complete, reset all visited flag
    resetVisitFlags ();
    destroy_queue (&ToVisit);
}//BreadthFirstSearch()


int main (void)
{
    int     v;

    verbose = 0;            // Set to be talkative/not talkative during build

    resetGraph();

    // read number at top of text file (N)
    // put the first N number of characters into a string and put it into add vertex labels
    // read in the rest of the file and iteratively add the edges for each pair
    int N, c, j, c1, c2;
    char string[100], stringv1[100], stringv2[100];
    FILE *file;
    file = fopen("graphdata.txt", "r");
    fscanf(file, "%d", &N);
    char array[N+1];
    char arrayV1[N+1];
    char arrayV2[N+1];
    printf("n is %d\n", N);

    for(j = 0; j<(N*2); j++) {
        c = getc(file);
        if(j == N*2-1){
            array[j/2-1] = (char) c;
        }
        else if(j%2 == 1){
            array[j/2] = (char) c;
            //printf("%c", (char) c);
        }
    }
    strncpy(string, array, N);
    addVertexLabels(string);
    strncpy(string, array, N);
    // printf("string is %s\n", string);

    c = getc(file);// to get to the front of the list
    for(j = 0; j<(22)*4; j++) {
        // getting every character in the text file
        //printf("%c", (char) c); // print statement for debugging
        if(j%4 == 0){
            c1 = getc(file);
            arrayV1[j/4] = (char) c;
            //printf("%c ", (char) c);
        }
        else if (j%4 == 2){
            c2 = getc(file);
            arrayV2[(j+2)/4] = (char) c;
            //printf("%c \n", (char) c);
        }
        else if (j%4 == 3){
            addEdgeByLabel(c1, c2);
        }
    }


    //strncpy(stringv1, arrayV1, 35 - (N + 1)-1);
    //printf("vertex 1 is %s\n", stringv1);

    //strncpy(stringv2, arrayV2, 35 - (N + 1)-1);
    //printf("vertex 2 is %s\n", stringv2);



//    addVertexLabels ("SABCDEF");
//
//    addEdgeByLabel ('S', 'A');
//    addEdgeByLabel ('S', 'B');
//    addEdgeByLabel ('S', 'C');
//    addEdgeByLabel ('A', 'D');
//    addEdgeByLabel ('B', 'D');
//    addEdgeByLabel ('C', 'D');
//    addEdgeByLabel ('D', 'E');
//    addEdgeByLabel ('E', 'F');

    verbose = 1;            // now be talkative

    if (verbose)    {
        // Display resulting adjacency matrix
        printf ("Adjacency Matrix:\n");
        printAdjMatrix();
        putchar ('\n');
    }

    printf ("Breadth-first searches, starting at each vertex\n");
    for (v=0; v<MAXVERTEX; v++) {
        printf("Breadth First Search starting at vertex %c (%d):\n",
               Vertices[v]->label, v);
        breadthFirstSearch(v);
    }

    // printf ("Second demonstration - this time with islands\n");
    // verbose = 1;
    // resetGraph ();
    // addVertexLabels ("PQRSTUV");
    // addEdgeByLabel ('P', 'Q');
    // addEdgeByLabel ('P', 'R');
    // addEdgeByLabel ('R', 'Q');
    // addEdgeByLabel ('S', 'T');
    // addEdgeByLabel ('S', 'U');
    // addEdgeByLabel ('S', 'V');
    // addEdgeByLabel ('T', 'U');
    // addEdgeByLabel ('T', 'V');
    // addEdgeByLabel ('V', 'W');
    // verbose = 1;         // now be talkative

    // if (verbose) {
    //  // Display resulting adjacency matrix
    //  printf ("Adjacency Matrix:\n");
    //  printAdjMatrix();
    //  putchar ('\n');
    //  }

    // printf ("Breadth-first searches, starting at each vertex\n");
    // for (v=0; v<MAXVERTEX; v++)  {
    //  printf("Breadth First Search starting at vertex %c (%d):\n",
    //      Vertices[v]->label, v);
    //  breadthFirstSearch(v);
    //  }

    // printf ("\n=====================================================\n");
    // printf ("Third demonstration - this time with directed edges.\n");
    // resetGraph();
    // addVertexLabels ("ABCDEFG");

    // addDirectedEdge (0, 1);      // A - B
    // addDirectedEdge (0, 2);      // A - C
    // addDirectedEdge (0, 6);      // A - G
    // addDirectedEdge (1, 3);      // B - D
    // addDirectedEdge (2, 6);      // C - G
    // addDirectedEdge (3, 4);      // D - E
    // addDirectedEdge (3, 5);      // D - F
    // addDirectedEdge (5, 6);      // F - G
    // addDirectedEdge (5, 4);      // F - E

    // verbose = 1;         // now be talkative

    // if (verbose) {
    //  // Display resulting adjacency matrix
    //  printf ("Adjacency Matrix:\n");
    //  printAdjMatrix();
    //  putchar ('\n');
    //  }

    // printf ("Breadth-first searches, starting at each vertex\n");
    // for (v=0; v<MAXVERTEX; v++)  {
    //  printf("Breadth First Search starting at vertex %d:\n", v);
    //  breadthFirstSearch(v);
    //  }

    // printf ("\n=====================================================\n");
    // printf ("Fourth demonstration - with weighted directed edges.\n");
    // resetGraph();
    // addVertexLabels ("ABCDEFG");

    // addWeightedDirectedEdge (0, 1, 10);      // A - B
    // addWeightedDirectedEdge (0, 2, 20);      // A - C
    // addWeightedDirectedEdge (0, 6, 30);      // A - G
    // addWeightedDirectedEdge (1, 3, 40);      // B - D
    // addWeightedDirectedEdge (2, 6, 50);      // C - G
    // addWeightedDirectedEdge (3, 4, 60);      // D - E
    // addWeightedDirectedEdge (3, 5, 70);      // D - F
    // addWeightedDirectedEdge (5, 6, 80);      // F - G
    // addWeightedDirectedEdge (5, 4, 90);      // F - E

    // verbose = 1;         // now be talkative

    // if (verbose) {
    //  // Display resulting adjacency matrix
    //  printf ("Adjacency Matrix:\n");
    //  printAdjMatrix();
    //  putchar ('\n');
    //  }

    // printf ("Breadth-first searches, starting at each vertex\n");
    // for (v=0; v<MAXVERTEX; v++)  {
    //  printf("Breadth First Search starting at vertex %d:\n", v);
    //  breadthFirstSearch(v);
    //  }
    return 0;
}//main()