/* Adam Pinarbasi
   akpinarb
   pa4           */
#include <assert.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include "Graph.h"
#include "List.h"

struct GraphObj {
   List *adj;
   int *color;
   int *parent;
   int *distance;
   int order;
   int size;
   int source;
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
   thisGraph->source    = NIL;
   for (int i = 0; i < n; ++i) thisGraph->adj[i] = newList();
   return thisGraph;
}

//freeGraph
//Graph destructor
void freeGraph (Graph *G) {
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
int getSource (Graph G) { return G->source; }

//getParent
//Returns parent of vertex u
int getParent (Graph G, int u) { return G->parent[u]; }

//getDist
//Returns distance from most recent source to vertex u
int getDist (Graph G, int u ) { return G->distance[u]; }

//getPath
//Appends to List vertices of shortest path in G from source to u
void getPath (List L, Graph G, int u) {
   if (G->source == u) append(L, u);
   else if (G->parent[u] == NIL) 
      { printf("No path to %d\n", u); }
   else {
      getPath(L, G, G->parent[u]);
      append(L, u);
   }
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
   addArc(G, u, v);
   addArc(G, v, u);
}

//addArc
//Inserts a new directed edge from u to v
//i.e. to the adjacency list of u and not v
void addArc (Graph G, int u, int v) {
   if (u > G->order || v > G->order) error("addArc", "Invalid vertex value");
   List uList = G->adj[u];
   if (length(uList) == 0) { append(uList, v); return; }
   else {
      for (moveFront(uList); index(uList) >= 0; moveNext(uList)) 
         if (v < get(uList)) { insertBefore(uList, v); return; }
   }
   append(uList, v);
}

//BFS
//Implements the Breadth First Search algorithm
/* For the color values:
   0 = white (undiscovered)
   1 = grey  (discovered, but neighbors may be undiscovered)
   2 = black (totally discovered)                           */
void BFS (Graph G, int u) {
   G->source = u;
   for (int i = 0; i < G->order; ++i) {
      G->color[i]    = 0;
      G->distance[i] = INF;
      G->parent[i]   = NIL;
   }
   G->color[u]    = 1;
   G->distance[u] = 0;
   List queue     = newList();
   append(queue, u);
   for (moveFront(queue); index(queue) >= 0;) {
      int x = get(queue);
      delete(queue);
      List yList = G->adj[x];
      for (moveFront(yList); index(yList) >= 0; moveNext(yList)) {
         int y = get(yList);
         if (G->color[y] == 0) {
            G->color[y]    = 1;
            G->distance[y] = G->distance[x] + 1;
            G->parent[y]   = x;
            append(queue, y);
         }
      }
      G->color[x] = 2;
   }
}

//Other Operations

//printGraph
//Prints the contents of the graph to the file, out
void printGraph (FILE *out, Graph G) {
   (void)out;
   (void)G;
   int n = G->order;
   for (int i = 0; i < n; ++i) {
      printf("%d: ", i);
      List thisList = G->adj[i];
      if (length(thisList) == 0) { printf("\n"); continue; }
      for (moveFront(thisList); index(thisList) >= 0; moveNext(thisList)) 
         printf("%d ", get(thisList));
      printf("\n");
   }

}




   










