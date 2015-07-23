/* Adam Pinarbasi
   akpinarb
   pa4           */
#include <assert.h>
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

static void error (char *function, char *message) {
   printf("Error in function: %s : %s\n", function, message);
   exit(EXIT_FAILURE);
}
   

//Constructors-Destructors

//Graph
//Graph constructor
Graph newGraph (int n) {
   Graph thisGraph      = malloc(sizeof(struct GraphObj));
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

//Manipulation Procedures

//makeNull
//Deletes all edges and restores G to its original state
void makeNull (Graph G) {
   int n = G->order;
   for (int i = 0; i < n; ++i) {
      clear(G->adj[i]);
      G->color[i] = 0;
      G->parent[i] = 0;
      G->distance[i] = 0;
   }
   G->size = 0;
}

//addEdge
//Inserts a new edge joining u to v
void addEdge (Graph G, int u, int v) {
   if (u > G->order || v > G->order) error("addEdge", "Invalid vertex value");

   List uList = G->adj[u];
   bool uIns = false;
   for (moveFront(uList); index(uList) >= 0; moveNext(uList)) {
      assert(v != get(uList));
      if (v < get(uList)) { insertBefore(uList, v); uIns = true; }
   }
   if (!uIns) append(uList, v);

   List vList = G->adj[v];
   bool vIns = false;
   for (moveFront(vList); index(vList) >= 0; moveNext(vList)) {
      assert(u != get(vList));
      if (u < get(vList)) { insertBefore(vList, u); vIns = true; }
   }
   if (!vIns) append(vList, u);
}

//addArc
//Inserts a new directed edge from u to v
//i.e. to the adjacency list of u and not v
void addArc (Graph G, int u, int v) {
   if (u > G->order || v > G->order) error("addEdge", "Invalid vertex value");
   List uList = G->adj[u];
   for (moveFront(uList); index(uList) >= 0;  moveNext(uList)) {
      assert(v != get(uList));
      if (v < get(uList)) { insertBefore(uList, v); return; }
   }
   append(uList, v);
}

//BFS
//Implements the Breadth First Search algorithm
void BFS (Graph G, int u) {
   (void)G;
   (void)u;
}

//Other Operations

//printGraph
//Prints the contents of the graph to the file, out
void printGraph (FILE *out, Graph G) {
   (void)out;
   (void)G;
}




   










