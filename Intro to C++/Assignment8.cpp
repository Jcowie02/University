#include <stdio.h>
#include <iostream>
using namespace std;

struct Payload { // this structure is the structure for my 'dataset' which contains all the necessary fields for
    // defining a customer center
    int numofCustomers;
    float avgPrice;
    float avgWeight;
};

class Economics {

public:
    float transportationCost = 4.7; // fixed cost used in revenue calculation
    float targetRevenue = 0.0;
    
    int customersServed(Payload dataset[], int numofCenters) { // this method goes through each index in the dataset
        // array, adds the number of customers in each center to a counter, then returns the value
        int c = 0;
        for (int i = 0; i < numofCenters; i++){
            c += dataset[i].numofCustomers;
        }
        return c;
    }
    float totalWeight(Payload dataset[], int numofCenters) { //this method does the same thing as the one above, only
        // it is for the avgWeight field of each dataset index
        float w = 0;
        for (int j = 0; j < numofCenters; j++){
            w += dataset[j].avgWeight*dataset[j].numofCustomers;
        }
        return w;
    }
    float totalRevenue(Payload dataset[], int numofCenters) { // this method calculates the total revenue and then
        // returns it
        for (int k = 0; k < numofCenters; k++) {
            targetRevenue += (dataset[k].numofCustomers * dataset[k].avgPrice) -
                 (dataset[k].numofCustomers * dataset[k].avgWeight * transportationCost);
            // the formula above takes the number of customers times the average price in a given center, and then
            // subtracts the number of customers times the weight of each item times the fixed transportation cost
            // to the get the total revenue in each center. The counter r adds the revenue from all centers together.
        }
        return targetRevenue;

    }
};

int main() {

    int numofCenters; // user input for number of centers
    printf("Input number of centers:\n");
    cin >> numofCenters;

    printf("Input customer data for all centers:\n");
    Payload dataset[numofCenters]; // dataset is defined as type 'Payload' as each index will pertain to three fields,
    //the number of customers, average price, and average weight in each center. The size of the dataset will be
    // determined by the number of centers.
    for (int i = 0; i < numofCenters; i++) {
        cin >> dataset[i].numofCustomers; // assign the number of customers to a given array index
    }
    printf("Input item average price data for all centers:\n");
    for (int j = 0; j < numofCenters; j++) { // assign the average price to a given array index
        cin >> dataset[j].avgPrice;
    }
    printf("Input item average weight data for all centers:\n");
    for (int k = 0; k < numofCenters; k++) { // assign the average weight to a given array index
        cin >> dataset[k].avgWeight;
    }

    Economics one; // 'one' is defined as the variable pertaining to the class 'Economics'
    int custServed;
    float totWeight, totRevenue, avgRevenue;

    custServed = one.customersServed(dataset, numofCenters); // calls the customersServed method in the class with the
    // parameters dataset and numofCenters, then returns and outputs the value to the console. The same process is used
    // for the other outputs below.
    printf("Total customers: %d\n", custServed);

    totWeight = one.totalWeight(dataset, numofCenters);
    printf("Total weight: %.0f kg\n", totWeight);

    totRevenue = one.totalRevenue(dataset, numofCenters);
    printf("Total revenue: $%.0f\n", totRevenue);

    avgRevenue = totRevenue / float(numofCenters); //type casting numofCenters to prevent any rounding errors
    printf("Average revenue: $%.0f\n", avgRevenue);
    if (avgRevenue > 36000) // finally, we need to test if the company is profitable or not by comparing the average
        // revenue to the profitability value
        printf("Company is profitable\n");
    else
        printf("Company is not profitable\n");

}