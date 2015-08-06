/* Adam Pinarbasi
   akpinarb
   pa5           */
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
   int *discover;
   int *finish;
   int order;
   int size;
};

//error
//Simple error function
static void error (char *function, char *message) {
   fprintf(stderr, "Error in function: %s : %s\n", function, message);
   exit(EXIT_FAILURE);
}

//Constructors-Destructors

//newGraph
//Graph constructor
Graph newGraph (int n) {
   Graph thisGraph      = malloc(sizeof(struct GraphObj));
   thisGraph->adj       = calloc(n + 1, sizeof(struct List));
   thisGraph->color     = calloc(n + 1, sizeof(int)); 
   thisGraph->parent    = calloc(n + 1, sizeof(int));
   thisGraph->discover  = calloc(n + 1, sizeof(int));
   thisGraph->finish    = calloc(n + 1, sizeof(int));
   thisGraph->order     = n;
   thisGraph->size      = 0;
   for (int i = 0; i < n + 1; ++i) thisGraph->adj[i] = newList();
   return thisGraph;
}

//freeGraph
//Graph destructor
void freeGraph (Graph *G) {
   /*free((*G)->discover);
   free((*G)->finish);
   free((*G)->parent);
   free((*G)->color);

   (*G)->discover = NULL;
   (*G)->finish   = NULL;
   (*G)->parent   = NULL;
   (*G)->color    = NULL;

   for (int i = 0; i < (*G)->order + 1; ++i) freeList(&((*G)->adj[i]));
   free((*G)->adj);
   (*G)->adj = NULL;

   free(*G);
   *G = NULL;*/
   (void)G;
}

//Access Functions

//getOrder
//Returns the order of a Graph
inline int getOrder (Graph G) { return G->order; }

//getSize
//Returns the size of a Graph (number of edges)
inline int getSize (Graph G) { return G->size; }

//getDiscover
//Returns the discover time of a vertex
/* Pre: 1 <= u <= n = getOrder(G) */
int getDiscover (Graph G, int u) { 
   if (!(1 <= u && u <= getOrder(G))) error("getDiscover", 
        "Precondition: 1 <= u <= n = getOrder(G) violated");
   return G->discover[u]; 
}

//getFinish
//Returns the finish time of a vertex
/* Pre: 1 <= u <= n = getOrder(G) */
int getFinish (Graph G, int u) {
   if (!(1 <= u && u <= getOrder(G))) error("getFinish", 
        "Precondition: 1 <= u <= n = getOrder(G) violated");
   return G->finish[u];
}

//getParent
//Returns parent of vertex u
//pre: 1 <= u <= G->order
int getParent (Graph G, int u) { 
   if (u < 1 || u > G->order) error("getParent", "Invalid vertex number");
   return G->parent[u]; 
}

//Manipulation procedures

//reverseList
//Helper function for DFS. Self-explanatory.
static void reverseList (List S) {
   List Scopy = copyList(S);
   for (moveBack(Scopy); index(Scopy) >= 0; movePrev(Scopy)) {
      deleteFront(S);
      append(S, get(Scopy));
   }
}

//visit
//Helper function for DFS
static void visit (Graph G, int x, int *time, List S) {
   G->color[x] = 1;
   G->discover[x] = ++(*time);
   List thisList = G->adj[x];
   if (length(thisList) != 0) {
      for (moveFront(thisList); index(thisList) >= 0; moveNext(thisList)) {
         int y = get(thisList);
         if (G->color[y] == 0) {
            G->parent[y] = x;
            visit(G, y, time, S);
         }
      }
   }
   G->color[x]  = 2;
   G->finish[x] = ++(*time);

   //get(S) should never be less than 0, provoke error rather than ignore
   for (moveFront(S); ; moveNext(S)) { 
      if (get(S) == x) {
         delete(S);
         append(S, x);
         break;
      }
   }
}

//DFS
//Perforsm depth first search on a Graph G
/* white = 0 (undiscovered)
   grey  = 1 (discovered with undiscovered children)
   black = 2 (discovered and children discovered) */
void DFS (Graph G, List S) {
   if (length(S) != G->order) error("DFS", "input List incomplete");
   int time = 0;
   for (int i = 1; i < G->order + 1; ++i) {
      G->color[i]  = 0;
      G->parent[i] = NIL;
   }
   List Scopy = copyList(S);
   for (moveFront(Scopy); index(Scopy) >= 0; moveNext(Scopy)) {
      int x = get(Scopy);
      if (G->color[x] == 0) visit(G, x, &time, S);
   }
   reverseList(S);
}

//addEdge
//Inserts a new edge joining u to v
void addEdge (Graph G, int u, int v) {
   addArc(G, u, v);
   addArc(G, v, u);
   --G->size;
}

//addArc
//Inserts a new directed edge from u to v
//i.e. to the adjacency list of u and not v
//pre: 1 <= u && v <= G->order
void addArc (Graph G, int u, int v) {
   if (u < 1 || v < 1 || u > G->order || v > G->order) 
      error("addArc", "Invalid vertex number");
   List uList = G->adj[u];
   if (length(uList) == 0) { append(uList, v); return; }
   else {
      for (moveFront(uList); index(uList) >= 0; moveNext(uList)) {
         if (v < get(uList)) { insertBefore(uList, v); return; }
         if (v == get(uList)) 
            error("addArc", "Cannot have two identical arcs");
      }
   }
   append(uList, v);
   ++G->size;
}

//Other Operations

//transpose
//Returns the transpose of a Graph
Graph transpose (Graph G) {
   Graph trans = newGraph(G->order);
   for (int i = 1; i < G->order + 1; ++i) {
      List thisList = G->adj[i];
      if (length(thisList) == 0) continue;
      for (moveFront(thisList); index(thisList) >= 0; moveNext(thisList)) 
         addArc(trans, get(thisList), i);
   }
   return trans;
}

//copyGraph
//Returns a copy of a graph
Graph copyGraph (Graph G) {
   Graph copy = newGraph(G->order);
   for (int i = 1; i < G->order + 1; ++i) {
      List thisList = G->adj[i];
      if (length(thisList) == 0) continue;
      for (moveFront(thisList); index(thisList) >= 0; moveNext(thisList))
         addArc(copy, i, get(thisList));
   }
   return copy;
}

//printGraph
//Prints the contents of the graph to the file, out
void printGraph (FILE *out, Graph G) {
   int n = G->order;
   for (int i = 1; i < n + 1; ++i) {
      fprintf(out, "%d: ", i);
      List thisList = G->adj[i];
      if (length(thisList) == 0) { fprintf(out, "\n"); continue; }
      for (moveFront(thisList); index(thisList) >= 0; moveNext(thisList)) 
         fprintf(out, "%d ", get(thisList));
      fprintf(out, "\n");
   }
}
