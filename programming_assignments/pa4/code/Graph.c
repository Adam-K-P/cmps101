#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include "Graph.h"
#include "List.h"

//Most recent vertex used in BFS
int source = NIL; 

struct GraphObj {
   List *adj;
   int *color;
   int *parent;
   int *distance;
   int order;
   int size;
};

//Constructors-Destructors

//Graph
//Graph constructor
Graph newGraph (int n) {
   Graph thisGraph = malloc(sizeof(struct GraphObj));
   thisGraph->adj       = calloc(n, sizeof(struct List));
   thisGraph->color     = calloc(n, sizeof(int)); 
   thisGraph->parent    = calloc(n, sizeof(int));
   thisGraph->distance  = calloc(n, sizeof(int)); 
   thisGraph->order     = n;
   thisGraph->size      = 0;
   return thisGraph;
}

//Access Functions

//getOrder
//Returns the order of a Graph
int getOrder (Graph G) { return G->order; }

//getSize
//Returns the size of a Graph (number of edges)
int getSize (Graph G) { return G->size; }

//getSource
//Returns source vertex most recently used in BFS 
//Returns NIL if BFS has not been called
int getSource (Graph G) { return source; }

//getParent
//Returns parent of vertex u
int getParent (Graph G, int u) { return G->parent[u]; }

//getDist
//Returns distance from most recent source to vertex u
int getDist (Graph G, int u ) { return G->distance[u]; }

//getPath
//Appends to List vertices of shortest path in G from source to u
void getPath (List L, Graph G, int u) {
   (void)L;
   (void)G;
   (void)u;
}


   










