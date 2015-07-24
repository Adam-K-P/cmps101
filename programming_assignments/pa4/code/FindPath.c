/* Adam Pinarbasi
   akpinarb
   pa4           */
#include <limits.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Graph.h"
#include "List.h"

#define MAX_SIZE 160

//error
//Simple error function
static void error (char *function, char *message) {
   fprintf(stderr, "Error in function: %s : %s\n", function, message);
   exit(EXIT_FAILURE);
}

//makeGraph
//Constructs the graph from input
static Graph makeGraph(FILE *infile) {
   char buffer[MAX_SIZE];
   if (!fgets(buffer, MAX_SIZE, infile)) 
      error("makeGraph", "Illegal file format");
   long tsize = strtol(buffer, NULL, 10);
   if (tsize == LONG_MIN || tsize == LONG_MAX)
      error ("makeGraph", "Illegal file input");
   int size = (int)tsize;
   Graph G = newGraph(size);
   while (fgets(buffer, MAX_SIZE, infile))  {
      char *tok = strtok(buffer, " ");
      if (tok == NULL) error("makeGraph", "Illegal file input");
      long tvertex1 = strtol(tok, NULL, 10);
      tok = strtok(NULL, " ");
      if (tok == NULL) error("makeGraph", "Illegal file input");
      long tvertex2 = strtol(tok, NULL, 10);
      if (tvertex1 == LONG_MIN || tvertex1 == LONG_MAX ||
          tvertex2 == LONG_MIN || tvertex2 == LONG_MAX   )
         error("makeGraph", "Illegal file input");

      int vertex1 = (int)tvertex1;
      int vertex2 = (int)tvertex2; 
      if (((vertex1 == 0) ^ (vertex2 == 0)) || 
           (vertex1 > getOrder(G)) || vertex2 > getOrder(G)) 
         error("makeGraph", "Illegal vertex values");
      if (vertex1 == 0) break; //both must be 0
      addEdge(G, vertex1, vertex2);
   }
   return G;
}

//doPaths
//Performs BFS and getPath operations
static void doPaths (Graph G, FILE *infile, FILE *outfile) {
   char buffer[MAX_SIZE];
   while (fgets(buffer, MAX_SIZE, infile)) {
      char *tok = strtok(buffer, " ");
      if (tok == NULL) error("doPaths", "Illegal file input");
      long tvertex1 = strtol(tok, NULL, 10);
      tok = strtok(NULL, " ");
      if (tok == NULL) error("doPaths", "Illegal file input");
      long tvertex2 = strtol(tok, NULL, 10);
      if (tvertex1 == LONG_MIN || tvertex1 == LONG_MAX ||
          tvertex2 == LONG_MIN || tvertex2 == LONG_MAX   )
         error("doPaths", "Illegal file input");

      int vertex1 = (int)tvertex1;
      int vertex2 = (int)tvertex2;
      if (((vertex1 == 0) ^ (vertex2 == 0)) || 
           (vertex1 > getOrder(G)) || vertex2 > getOrder(G)) 
         error("doPaths", "Illegal vertex values");
      if (vertex1 == 0) break; 
      BFS(G, vertex1);
      if (getDist(G, vertex2) == INF) {
         fprintf(outfile, "The distance from %d to %d is infinity\n",
                           vertex1, vertex2);
         fprintf(outfile, "No %d-%d path exists\n\n", vertex1, vertex2);
         continue;
      }
      fprintf(outfile, "The distance from %d to %d is %d\n", 
                        vertex1, vertex2, getDist(G, vertex2));
      List L = newList();
      getPath(L, G, vertex2);
      fprintf(outfile, "A shortest %d-%d path is: ", vertex1, vertex2);
      printList(outfile, L);
      fprintf(outfile, "\n\n");
      freeList(&L);
   }
}

int main (int argc, char **argv) {
   if (argc != 3) error("main", "Usage: FindPath [infile] [outfile]");
   FILE *infile  = fopen(argv[1], "r");
   FILE *outfile = fopen(argv[2], "w");
   Graph G = makeGraph(infile);
   printGraph(outfile, G);
   fprintf(outfile, "\n");
   doPaths(G, infile, outfile);
   fclose(infile);
   fclose(outfile);
   freeGraph(&G);
   return EXIT_SUCCESS;
}
