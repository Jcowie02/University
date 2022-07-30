#include <iostream>
#include <string>
#include <vector>
#include <stdio.h>
#define ARRAYSIZE 20

using namespace std;

class Stack{
    // values below are used throughout the entire class
    int top;
    int idsArray[ARRAYSIZE];
    char operationsArray[ARRAYSIZE]; //not necessarily needed, but included in starter code
public:

    Stack(){
        top = 0; // initializing top value as 0
    };

    bool isEmpty(){ // error handling (ensures no popping if the stack is empty)
        if (top == 0){
            return true;
        }
        else return false;
    }
    int push(int element1, char element2){
        if (top  == ARRAYSIZE){ //overflow error handling
            printf("FAIL");
            return 0;
        }
        else { //pushing elements into the stack of ids and operations
            idsArray[top] = element1;
            operationsArray[top] = element2;
            top++;
        } //adding one value to the top (only top values considered in stacks)
    }

    int pop(){
        if (isEmpty()){ //underflow error handling
            printf("FAIL");
            return 0;
        }
        else {
            top--;
            return idsArray[top-1];
        }//returns the value right before the negative to check if they are the same
        //this logic follow LIFO (last in first out) as the most recently fired should also be the most recently hired
    }

    int check(){
        return idsArray[top-1]; //returns the current top value in the ids array (one that was just inputted if
        // negative)
    }

};

int main(int argc, const char * argv[]) {

    int numberOfHROperations;  // user input for the number of HR operations
    printf("Enter the number of HR operations:\n");
    cin >> numberOfHROperations;

    Stack one; // defining Stack 'variable type' one

    //initializing vectors
    std::vector<int> ids;
    std::vector<char> operations;

    printf("Input the operations and IDs:\n");
    for (int i = 0; i < numberOfHROperations; i++) { //for loop runs for however many HR operations there are
        char operation;
        int id;
        scanf(" %c%d", &operation, &id);

        operations.push_back(operation); // used in starter code - pushes elements into a vector
        ids.push_back(id);
        one.push(ids[i], operations[i]); //using vector values to input into the Stack class and update arrays

        if (operation == '-') {
            if (one.check() == one.pop()) // if the HR operation is negative, then the employee id should match the
                // employee id right before it. This code ensures that the most recent id matches the one before
                // it to ensure the most recently hired is being fired first
                one.pop(); // if this is the case, the other value is popped, so that this code can work for other
                // fired employees
            else {
                printf("FAIL"); // if this conditions does not hold, FAIL is outputted and the program ends
                return 0;
            }
        }
    }
    printf("PASS"); //if the user input is satisfied throughout the entire loop (for all fires), then the manager's
    // requirements were met and PASS is outputted
    return 0;
}

// Notes:

//Example logic:
//For example, I’d have and [10,20,30,30], then 30 and 30 would both be popped, then I'd have [10,20,20], and 20 and 20
// would then both be popped, leaving me with just [10] in the stack

// Alternative logic: only add values to the stack if they are positive, and then if they are negative, pop the last
// positive value and compare it

// I understand that my code did not pass the Gradescope tests, but I think it is because I am not utilizing the vector
// and string functions properly.
// My code works with arbitrary values in accordance with the example given in the assignment document