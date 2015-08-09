/* Adam Pinarbasi
   akpinarb
   pa5            */
#include <limits.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Graph.h"
#include "List.h"

#define MAX_SIZE 160

//Just a simple struct to return two vertices from a function
typedef struct vertices {
   int vertex1;
   int vertex2;
} vertices;

//error 
//Simple error function
static void error (char *function, char *message) {
   fprintf(stderr, "Error in function %s: %s\n", function, message);
   exit(EXIT_FAILURE);
}

static vertices *getVertices (char *buffer) {
   vertices *theseVertices = malloc(sizeof(vertices));
   char *tok = strtok(buffer, " ");
   if (tok == NULL) error("getVertcies", "Illegal file input");
   long tvertex1 = strtol(tok, NULL, 10);
   tok = strtok(NULL, " ");
   if (tok == NULL) error("getVertices", "Illegal file input");
   long tvertex2 = strtol(tok, NULL, 10);
   if (tvertex1 == LONG_MIN || tvertex1 == LONG_MAX ||
       tvertex2 == LONG_MIN || tvertex2 == LONG_MAX   )
      error("getVertices", "Illegal file input");
   theseVertices->vertex1 = (int)tvertex1;
   theseVertices->vertex2 = (int)tvertex2;
   return theseVertices;
}

//inList
//Helper function: checks if an entry is already in a List
static bool inList (List vertexList, int vertex) {
   if (length(vertexList) == 0) return false;
   for (moveFront(vertexList); index(vertexList) >= 0; moveNext(vertexList)) 
      if (get(vertexList) == vertex) return true;
   return false;
}

//getStrong
//Gets and prints strongly connected components
static void getStrong (Graph G, FILE *outfile, List vertexList) {
   List roots = newList();
   DFS(G, vertexList);
   Graph trans = transpose(G);
   DFS(trans, vertexList);
   int scc = 0;
   for (moveFront(vertexList); index(vertexList) >= 0; moveNext(vertexList)) {
      if (getParent(trans, get(vertexList)) == NIL) { 
         append(roots, get(vertexList)); 
         ++scc;
      }
   }
   fprintf(outfile, "\nG contains %d strongly connected components:\n", scc);
   int i = 1;
   for (moveBack(roots), moveBack(vertexList); index(roots) >= 0; 
        movePrev(roots)) {
      List component = newList();
      for (; index(vertexList) >= 0; movePrev(vertexList)) {
         prepend(component, get(vertexList));
         if (get(vertexList) == get(roots)) {
            movePrev(vertexList);
            break;
         }
      }
      fprintf(outfile, "Component %d: ", i++);
      printList(outfile, component);
      fprintf(outfile, "\n");
      freeList(&component);
   }
   freeList(&roots);
   freeGraph(&trans);
}

//makeGraph
//Constructs the graph from input
static Graph makeGraph (FILE *infile, List vertexList) {
   char buffer[MAX_SIZE];
   if (!fgets(buffer, MAX_SIZE, infile))
      error("makeGraph", "Illegal file format");
   long tsize = strtol(buffer, NULL, 10);
   if (tsize == LONG_MIN || tsize == LONG_MAX)
      error("makeGraph", "Illegal file input");
   int size = (int)tsize;
   Graph G = newGraph(size);
   while (fgets(buffer, MAX_SIZE, infile)) {
      vertices *theseVertices = getVertices(buffer);
      int vertex1 = theseVertices->vertex1;
      int vertex2 = theseVertices->vertex2;
      free(theseVertices);
      if (((vertex1 == 0) ^ (vertex2 == 0)) ||
           (vertex1 > getOrder(G)) || vertex2 > getOrder(G))
         error("makeGraph", "Illegal vertex values");
      if (vertex1 == 0) break; //both must be 0
      addArc(G, vertex1, vertex2);
      if (!inList(vertexList, vertex1)) append(vertexList, vertex1);
      if (!inList(vertexList, vertex2)) append(vertexList, vertex2);
   }
   return G;
}

int main (int argc, char **argv) {
   if (argc != 3) error("main", "Usage: FindComponents [infile] [outfile]");
   FILE *infile  = fopen(argv[1], "r");
   FILE *outfile = fopen(argv[2], "w");
   List vertexList = newList();
   Graph G = makeGraph(infile, vertexList);
   printGraph(outfile, G);
   getStrong(G, outfile, vertexList);
   freeList(&vertexList);
   freeGraph(&G);
   fprintf(outfile, "\n");
   fclose(infile);
   fclose(outfile);
   return EXIT_SUCCESS;
}
