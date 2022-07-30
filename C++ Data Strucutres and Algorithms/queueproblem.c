//queueproblem.c - Lab 02 - Jackson, Cowie

#include <stdio.h>
#include <stdlib.h>
#define FULL 400 // as indicated, each queue can hold up to 400 inputs

typedef enum {false=0, true}	bool, *pbool; // used for function types

// Node structure for the queues
struct item {
    struct item    *next;
    int            value;
};
typedef struct item		Item, *pItem;

// Descriptor for the queue; back and front point to nodes in the queue, while count keeps track of the number of nodes
struct queue	{
    Item     *back;
    Item     *front;
    int      count;
};
typedef struct queue queue, *pqueue;

// this function initializes each queue, setting up the 'default' conditions
void init(pqueue pque) {
    pque->count = 0;
    pque->front = NULL;
    pque->back = NULL;
}

int is_empty (pqueue pque) {
    return (pque->back == NULL); // if the back or front is pointing to NULL, then the queue must be empty
}

bool enqueue (pqueue pque, int n)
{
    if (pque == NULL)	return false; // checks for bad/invalid pointer

    if (pque->count < FULL) {
        Item	*pnew = (Item *)malloc(sizeof (struct item)); //node pointer to be created if the queue is not full

        if (pnew != NULL) { //if memory was successfully created...
            pnew->value = n; //give temporary pointer its value, and point it to NULL
            pnew->next = NULL;
            if (!is_empty(pque)) { //if the queue is not empty, point the back of the queue to this node and then
                // set the back of the queue equal to this node
                pque->back->next = pnew;
                pque->back = pnew;
            } else { //if the queue is empty, then the back and the front of the queue will point to this node
                pque->front = pque->back = pnew;
            }
            pque->count++; // add to the count of the nodes in a queue
        }
    }
    return true;
}

void print_queue (pItem tmp)
{
    // counter and k used to determine when to input a newline
    int counter = 0;
    int k = 1; //need to initialize as 1, so it doesn't go into the else statement right away

    if (tmp == NULL) return; //checks for invalid pointer
    else {
        while (tmp != NULL) { //runs for the entire queue
            if (k != 0){
                printf("%5d", tmp->value); // output value, starting from the front
                tmp = tmp->next; //move on to next node
                counter++;
                k = counter % 12; //update k and counter
            }
            else{ // 12 outputs has been reached, so new line is created
                putchar('\n');
                k++; //prevents from going into the else statement right away
            }
        }
        putchar('\n');
    }
}

int main (void)
{
    int val = 1;

    // the code below initializes and creates memory for each of the queues
    pqueue q0, q1, q2, q3;
    q0 = malloc(sizeof(queue));
    q1 = malloc(sizeof(queue));
    q2 = malloc(sizeof(queue));
    q3 = malloc(sizeof(queue));
    init(q0);
    init(q1);
    init(q2);
    init(q3);

    printf("Input integer numbers into 4 different queues. To end input sequence, enter a negative number.\n");

    //while loop runs until a negative number is inputted
    while (1){
        scanf("%d", &val);
        if (val < 0) break;

        // add inputted value to corresponding queue depending on the modulo
        if (val % 4 == 0) enqueue (q0, val);
        else if (val % 4 == 1) enqueue (q1, val);
        else if (val % 4 == 2) enqueue (q2, val);
        else enqueue (q3, val);

    }

    // each queue is then printed, starting from the front as those values would have been the first inputted (FIFO)
    printf("All queues will be displayed in sequential order, maximum 12 values per line\n");
    printf("Queue_0:\n");
    print_queue (q0->front);
    printf("Queue_1:\n");
    print_queue (q1->front);
    printf("Queue_2:\n");
    print_queue (q2->front);
    printf("Queue_3:\n");
    print_queue (q3->front);

    return 0;
}