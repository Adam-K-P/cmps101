/* Adam Pinarbasi
   akpinarb
   pa4           */
#include <stdio.h>
#include <stdlib.h>
#include "Graph.h"

int main (void) {
   Graph G = newGraph(10);
   printf("order:%d\nsize:%d\n", getOrder(G), getSize(G));
   return EXIT_SUCCESS;
}

