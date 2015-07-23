/* Adam Pinarbasi
   akpinarb
   pa4           */
#include <stdio.h>
#include <stdlib.h>
#include "Graph.h"

int main (void) {
   Graph G = newGraph(10);
   addEdge(G, 1, 2);
   addEdge(G, 1, 3);
   BFS(G, 1);
   FILE *temp;
   printGraph(temp, G);
   List thisList = newList();
   getPath(thisList, G, 2);
   for (moveFront(thisList); index(thisList) >= 0; moveNext(thisList)) 
      printf("%d\n", get(thisList));
   return EXIT_SUCCESS;
}

