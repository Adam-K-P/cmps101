/* Adam Pinarbasi
   akpinarb
   pa5           */
#ifndef __GRAPH
#define __GRAPH

#include <stdio.h>
#include "List.h"

#define NIL -2

typedef struct GraphObj *Graph;

//Constructors-Destructors
Graph newGraph (int);
void freeGraph (Graph *);

//Access functions
int getOrder (Graph);
int getSize (Graph);
int getParent (Graph, int);
int getDiscover (Graph, int);
int getFinish (Graph, int);

//Manipulation procedures
void addArc (Graph, int, int);
void addEdge (Graph, int, int);
void DFS (Graph, List);

//Other operations
Graph transpose (Graph);
Graph copyGraph (Graph);
void printGraph (FILE *, Graph);

#endif
