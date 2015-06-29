/* Adam Pinarbasi
   akpinarb
   pa2           */

#include <stdio.h>
#include <stdlib.h>
#include "List.h"

typedef struct node {
   struct node *next;
   struct node *prev;
}node;

typedef struct List {
   node *front;
   node *back;
   int index;
   int length;
}List;

// newList
/* List constructor */
List newList(void) {
   return NULL;
}

// freeList
/* List destructor */
void freeList(List *pL){
   (void)pL;
}

// length
/* Get length */
int length(List L){
   (void)L;
   return 0;
}

// index
/* Get index */
int index(List L) {
   (void)L;
   return 0;
}

// front
/* Get front element */
int front(List L) {
   (void)L;
   return 0;
}

// back
/* Get back element */
int back(List L) {
   (void)L;
   return 0;
}

// get
/* Get element at cursor */
int back(List L) {
   (void)L;
   return 0;
}

// equals
/* Find if List A equals List B */
int equals(List A, List B) {
   (void)A;
   (void)B;
   return 0;
}

// clear
/* Clear out a List to defaults */
void clear(List L) {
   (void) L;
}

// moveFront
/* Move cursor to front node */
void moveFront(List L) {
   (void)L;
}

// moveBack
/* Move cursor to back node */
void moveBack(List L) {
   (void)L;
}

// movePrev
/* Move cursor to previous node */
void movePrev(List L) {
   (void)L;
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
