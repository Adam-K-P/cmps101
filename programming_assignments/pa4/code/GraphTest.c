/* Adam Pinarbasi
   akpinarb
   pa4           */
#include <stdio.h>
#include <stdlib.h>
#include "Graph.h"

int main (void) {
   Graph G = newGraph(6);

   addEdge(G, 1, 2);
   addEdge(G, 1, 3);
   addEdge(G, 2, 4);
   addEdge(G, 2, 5);
   addEdge(G, 2, 6);
   addEdge(G, 3, 4);
   addEdge(G, 4, 5);
   addEdge(G, 5, 6);

   BFS(G, 3);
   List thisList = newList();
   getPath(thisList, G, 6);
   for (moveFront(thisList); index(thisList) >= 0; moveNext(thisList)) 
      printf("%d\n", get(thisList));
   return EXIT_SUCCESS;
}

