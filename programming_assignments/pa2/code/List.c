/* Adam Pinarbasi
   akpinarb
   pa2           */

#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include "List.h"

typedef struct node {
   struct node *next;
   struct node *prev;
   int data;
}node;

typedef struct List {
   node *front;
   node *back;
   node *cursor;
   int index;
   int length;
}List;

static void error(char *function, char *message) {
   printf("Error in - %s: %s\n", function, message);
   exit(EXIT_FAILURE);
}

// newList
/* List constructor */
List newList(void) {
   List *thisList = malloc(sizeof(List))
   clear(thisList);
   return thisList;
}

// freeList
/* List destructor */
void freeList(List *pL){
   for (node *curr = pL->front; curr != NULL;) {
       node *temp = curr;
       curr = curr->next;
       free(temp); 
       *temp = NULL;
   }
   free(pL);
   *pL = NULL;
}

// length
/* Get length */
int length(List L){ return L.length; }

// index
/* Get index */
int index(List L) { return L.index; }

// front
/* Get front element */
int front(List L) { 
   if (length == 0) error("front", "length is 0");
   return L.front->data; 
}

// back
/* Get back element */
int back(List L) { return L.back->data; }

// get
/* Get element at cursor */
int get(List L) { return L.cursor->data; }

// equals
/* Find if List A equals List B */
int equals(List A, List B) {  
   if ( ((A == NULL) ^ (B == NULL)) || 
        ((A.length == 0) ^ (B.length == 0)) ) return false;
   for (node *Anode, node*Bnode; 
        Anode != NULL && Bnode != NULL;
        Anode = Anode.next, Bnode = Bnode.next ) 
      { if (Anode != Bnode) return false; }
   return true;
}

// clear
/* Clear out a List to defaults */
void clear(List L) { 
   thisList.front  = NULL;
   thisList.back   = NULL;
   thisList.cursor = NULL;
   thisList.index  = -1;
   thisList.length = 0;
}

// moveFront
/* Move cursor to front node */
void moveFront(List L) { cursor = front; }

// moveBack
/* Move cursor to back node */
void moveBack(List L) { cursor = back; }

// movePrev
/* Move cursor to previous node */
void movePrev(List L) { 

}

// moveNext
/* Move cursor to next node */
void moveNext(List L) {
   (void)L;
}

// prepend
/* Prepend node to list */
void prepend(List L, int data) {
   (void)L;
   (void)data;
}

// append
/* Append node to list */
void append(List L, int data) {
   (void)L;
   (void)data;
}

// insertBefore
/* Insert node before cursor */
void insertBefore(List L, int data) {
   (void)L;
   (void)data;
}

// insertAfter
/* Insert node after cursor */
void insertAfter(List L, int data) {
   (void)L;
   (void)data;
}

// deleteFront
/* Delete front element */
void deleteFront(List L) {
   (void)L;
}

// deleteBack
/* Delete back element */
void deleteBack(List L) {
   (void)L;
}

// delete
/* Delete element under cursor */
void delete(List L) {
   (void)L;
}

// printList
/* Print the contents of the list */
void printList(File *out, List L) {
   (void)out;
   (void)L;
}

// copyList
/* Return a new copy of the list */
List copyList(List L) {
   (void)L;
   return NULL;
}

// concatList
/* Concatenate List B onto List A */
List concatList(List A, List B) {
   (void)A;
   (void)B;
   return NULL;
}
