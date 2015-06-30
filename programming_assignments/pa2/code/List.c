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
} node;

typedef struct List {
   node *front;
   node *back;
   node *cursor;
   int index;
   int length;
} List;

static void error(char *function, char *message) {
   printf("Error in function %s: %s\n", function, message);
   exit(EXIT_FAILURE);
}

// newList
/* List constructor */
List newList(void) {
   List *thisList = malloc(sizeof(List));
   clear(*thisList);
   return *thisList;
}

node *newNode(void) {
   node *newNode = malloc(sizeof(node));
   newNode->next = NULL;
   newNode->prev = NULL;
   newNode->data = 0;
   return newNode;
}

// freeList
/* List destructor */
void freeList(List *pL){
   if (pL == NULL) error("freeList", "list is NULL");
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
int length(List L){ 
   if (L == NULL) error ("length", "list is NULL");
   return L.length; 
}

// index
/* Get index */
int index(List L) {
   if (L == NULL) error("index", "list is NULL");
   return L.index;
}

// front
/* Get front element */
int front(List L) { 
   if (L == NULL) error("front", "list is NULL");
   if (length == 0) error("front", "length is 0");
   return L.front->data; 
}

// back
/* Get back element */
int back(List L) { 
   if (L == NULL) error("back", "list is NULL");
   if (L.back == NULL) error ("back", "back is NULL");
   return L.back->data; 
}

// get
/* Get element at cursor */
int get(List L) { 
   if (L == NULL) error("get", "list is NULL");
   if (L.cursor == NULL) error("get", "cursor is NULL");
   return L.cursor->data; 
}

// equals
/* Find if List A equals List B */
int equals(List A, List B) {  
   if ((A == NULL ^ B == NULL) || A.length != B.length)    
      return false;
   if (A == NULL || A.length == 0) return true; 
      /* means both are NULL or empty */
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
void moveFront(List L) { 
   if (L == NULL) error("moveFront", "list is NULL");
   L.index = 0;
   L.cursor = L.front;
}

// moveBack
/* Move cursor to back node */
void moveBack(List L) {
  if (L == NULL) error("moveBack", "list is NULL"); 
  L.index = L.length - 1;
  L.cursor = L.back;
}

// movePrev
/* Move cursor to previous node */
void movePrev(List L) { 
   if (L == NULL) error("movePrev", "list is NULL");
   if (L.cursor == NULL) return;
   L.cursor = L.cursor->prev;
   --L.index;
}

// moveNext
/* Move cursor to next node */
void moveNext(List L) {
   if (L == NULL) error("moveNext", "list is NULL");
   if (L.cursor == NULL) return;
   L.cursor = L.cursor->next;
   ++L.index;
}

// prepend
/* Prepend node to list */
void prepend(List L, int data) { //FIXME fails if L.front is NULL
   if (L == NULL) error("prepend", "list is NULL");
   node *prep    = newNode(); 
   prep->data    = data;
   prep->next    = L.front;
   L.front->prev = prep;
   L.front       = prep;
   ++L.length;
}

// append
/* Append node to list */
void append(List L, int data) { //FIXME fails if L.front is NULL
   if (L == NULL) error("appaend", "list is NULL");
   node *app    = newNode();
   app->data    = data;
   app->prev    = L.back;
   L.back->next = app;
   L.back       = app;
   ++L.length;
}

// insertBefore
/* Insert node before cursor */
void insertBefore(List L, int data) {
   if (L == NULL) error("insertBefore", "list is NULL");
   if (L.cursor == NULL) error("insertBefore", 
                               "cursor is NULL");
   node *insb = newNode();
   insb->data = data;
   insb->prev = L.cursor->prev;
   insb->next = L.cursor;
   L.cursor->prev != NULL ? L.cursor->prev->next = insb :
                            L.front = insb;
   L.cursor->prev  = insb;
   ++L.index;
   ++L.length;
}

// insertAfter
/* Insert node after cursor */
void insertAfter(List L, int data) {
   if (L == NULL) error("insertBefore", "list is NULL");
   if (L.cursor == NULL) error("insertBefore", 
                               "cursor is NULL");
   node *insa = newNode();
   insa->data = data;
   insa->prev = L.cursor;
   insa->next = L.cursor->next;
   L.cursor->next != NULL ? L.cursor->next->prev = insa :
                            L.back = insa;
   L.cursor->next  = insa;
   ++L.length;
}

// deleteFront
/* Delete front element */
void deleteFront(List L) {
   if (L == NULL) error("deleteFront", "list is NULL");
   if (L.front == NULL) error("deleteFront", 
                              "front is NULL");
   node *temp = L.front;
   L.front = L.front->next;
   free(temp);
   *temp = NULL;
   --L.index;
}

// deleteBack
/* Delete back element */
void deleteBack(List L) {
   if (L == NULL) error("deleteBack", "list is NULL");
   if (L.back == NULL) error("deleteBack", 
                             "back is NULL");
   node *temp = L.back;
   L.back = L.back->prev;
   free(temp);
   *temp = NULL;
   if (L.cursor == L.back) L.index = -1;
}

// delete
/* Delete element under cursor */
void delete(List L) {
   if (L == NULL) error("delete", "list is NULL");
   if (L.cursor == NULL) error("delete", 
                               "cursor is NULL");
   free(L.cursor);
   *(L.cursor) = NULL;
   L.index = -1;
   --L.length;
}

// printList
/* Print the contents of the list */
void printList(File *out, List L) {
   if (L == NULL) error("printList", "list is NULL");
   for (node *curr = L.front; curr != NULL; curr = curr->next) 
      fprintf((curr == L.back ? "%d" : "%d "), curr->data);
}

// copyList
/* Return a new copy of the list */
List copyList(List L) {
   if (L == NULL) return NULL;
   List newL = newList();
   for (node *curr = L.front; curr != NULL; curr = curr->next)
      newL.append(curr->data);
   return newL;
}

// concatList
/* Concatenate List B onto List A */
List concatList(List A, List B) {
   A == NULL ? return B : (B == NULL ? return A : );
   List cat = copyList(A);
   for (node *curr = B.front; curr != NULL; curr = curr->next)
      cat.append(curr->data);
   return cat;
}
