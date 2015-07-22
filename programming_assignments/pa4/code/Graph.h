#ifndef __GRAPH
#define __GRAPH

#include <stdio.h>
#include "List.h"

#define INF -1
#define NIL -2

typedef struct GraphObj *Graph;

//Constructors-Destructors
Graph newGraph (int);
void freeGraph (Graph *);

//Access functions
int getOrder (Graph);
int getSize (Graph);
int getSource (Graph);
int getParent (Graph, int);
int getDist (Graph, int);
void getPath (List, Graph, int);

//Manipulation procedures
void makeNull (Graph);
void addEdge (Graph, int, int);
void addArc (Graph, int, int);
void BFS (Graph, int);

//Other operations
void printGraph (FILE *, Graph);

#endif
