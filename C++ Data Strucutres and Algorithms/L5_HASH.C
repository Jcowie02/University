// Cowie, Jackson; Laughton, David
// L5_HASH.C
// Program for use with ELEC278 Lab 5.
//
// History:
// 161106	HF	First release
// 171102	DA	Minor tweaks
// 201101	DA	Format changes


#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include "DLL.h"

//optimized length of hash table for both text files.
#define	TABSIZ1	39			// define size of table for wordlist.txt
#define TABSIZ2 37			// define table size for wordlist2.txt
// Definition of data structure for chained hashtable
typedef struct HashTable {
    pLinkedList	*a;			// point to array of pointers to linked lists
    int			size;		// size of the array (not the table)
    int			population;	// actual number of strings in table
    int			collisions;	// count collisions detected on insert
} HashTable, *pHashTable;

float ACL, nonzeroChains; // global average chain length variable.

pHashTable initHashTable (int s)
{
    pHashTable	pht = (pHashTable) malloc(sizeof(HashTable));
    if (pht != NULL)	{
        // Created space for hashtable descriptor
        int		i;
        pht->size = s;		// size is the size of the linked-list array
        pht->collisions = 0;
        pht->population = 0;
        // Now set up the harray of linked list pointers for the chains
        pht->a = malloc(sizeof(LinkedList) * s);
        if (pht->a != NULL)	{
            // Initialize memory for all the linked lists
            for(i=0; i<s; i++)	pht->a[i] = init();
        } else	{
            // Didn't get needed memory - give up what we got
            free (pht);
            pht = NULL;
        }
    }
    return pht;
}//initHashTable()


unsigned long int hashcode (char *x)
// This function computes hash code for given string.  Note that hash code
// is unbounded - it needs to be reduced modulo size of hash table (array)
// used to store data. (You will see another function hash() below that
// completes the index calculation.)
{
    unsigned long int	sum = 0;
    int		i = 0;
    int l = strlen(x);
    while(x[i]!='\0'){
        sum += (x[i] * (long)pow(31,l-1-i));
        i++;
    }
    return sum;
}//hashcode()


int hash (char* x, int size)
// Index calculation. Function hashcode() computes general hash for string;
// this function turns hash into valid index, given size of array.
{
    return hashcode(x) % size;
}//hash()


void insert(HashTable *ht, char *x)
// Insert new string into hashtable
{
    int key = hash(x, ht->size);
    printf ("    Word: %s hash index: %d\n", x, key);
    // Is the slot empty?
    if (!isEmptyList(ht->a[key])){
        ht->collisions++;
    }
    else if(isEmptyList(ht->a[key])){
        nonzeroChains++;//if the chain is starting from zero there becomes a new non zero chain
    }
    addFront(ht->a[key],x);
    ht->population++;//when a string is inserted to the hash table increase the population of the hash table
}//insert()


int findInTable(HashTable *ht, char* x)
{
    int key = hash(x, ht->size);
    return (find(ht->a[key],x));
}


void printTable(HashTable *ht)
{
    int i;
    float LF, enteries;
    printf("----------\n");
    for (i = 0; i < ht->size; i++)	{
        printf("%d --> ", i);
        printList(ht->a[i]);
    }
    enteries = ht->population;
    ACL = ht->population/nonzeroChains;//average chain length
    LF = enteries/TABSIZ2*100;// load factor calculatoion by dividing by the table sizeS
    printf("# of Collisions=%d # entries=%d Load factor = %.2f %% Average chain length %.2f\n", ht->collisions, ht->population, LF, ACL);
    //printf("Load Factor %%=%.2f\n----------", loadFactor(ht));
}




int main()
{
    int i=0;
    HashTable* h = initHashTable(TABSIZ2+1);

// Two versions of code to build data. The first generates random strings, the
// second (used in the lab) reads words from a file.
#if 0
    // Generate a set of random strings (given the way this works, everyone
	// gets the same set of random strings!) insert each into the hash table
	// and then print the table.
	for(i=0;i<TABSIZ;i++){
		int x = rand()%5+1;
		char *p = malloc(sizeof(char)*x);
		int j=0;
		for(j=0;j<x;j++)
			p[j] = rand()%26 + 97;
		p[j]='\0';
		insert(h,p);
	}
#else

    FILE	*fp;
    char	*p;
    char	buff [132];
    fp = fopen ("WORDLIST2.TXT", "r");
    while (!feof (fp))	{
        fgets (buff, 128, fp);
        p = buff;
        while (*p)	 {
            if (*p <= ' ')	{
                *p = '\0';
                break;
            }
            p++;
        }
        printf ("Word inserted: %s\n", buff);
        insert (h, buff);
        printTable (h);
        printf ("Press return key to add another number to table:");
        fflush (stdout);
        getchar();
    }
#endif
    printTable(h);

    return 0;
}