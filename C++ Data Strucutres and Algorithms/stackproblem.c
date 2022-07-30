//stackproblem.c - Lab02 - Jackson, Cowie

#include <stdio.h>

#define STACK_SIZE  80

// These defines are meant to be constant - do not change
#define EMPTY_INDEX -1
#define MAX_INDEX   (STACK_SIZE - 1)

// These defines are meant to assist a reader in understanding behavior.
#define RTN_ERROR   0
#define RTN_OK      1

int  top = EMPTY_INDEX;
char  stack [STACK_SIZE]; //stack is initialized as char because it will be holding characters

int isEmpty() { //function used to determine when the printing loop will end
    if (top == -1) return 1;
    else return 0;
}

int push (char n)
{
    if (top == MAX_INDEX)  return RTN_ERROR;
    top = top + 1; //index for top of stack increases by 1
    stack[top] = n; //set new stack top to the inputted one
    return  RTN_OK;
}//push()

char  pop (char *data) {
    if (top == EMPTY_INDEX)  return RTN_ERROR;
    *data = stack[top--]; //value of pointer takes on the value of the stack at the top (then top is decreased by one
    // using top--)
    return *data; //returned to be able to print the stack
}// pop()


int main (void)
{
    char input = '1'; //input has to be intialized to avoid errors
    char rslt;
    char data;

    printf("Enter a new character or end the sequence by inputting a space or a newline\n");

    while (1){ // while loop runs until the user inputs a newline. Values are being pushed onto the stack
        scanf("%c", &input);
        push (input);
        if (input == '\n') {
            break;
        }
    }

    while (isEmpty() != 1) { //isEmpty() is 1 when the stack is empty
        rslt = pop(&data);
        printf("%c ", rslt); } //prints the returned value of the pop() function which is the last item pushed to the
    //stack (LIFO)

    return 0;
}//main()