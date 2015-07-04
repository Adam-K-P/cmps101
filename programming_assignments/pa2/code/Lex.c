/* Adam Pinarbasi
   akpinarb
   pa2           */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "List.h"

#define MAX_LENGTH 160

typedef struct file_info {
   char **line_array;
   int length;
   size_t nr_lines;
} file_info;

file_info insert_file (FILE *infile, size_t nr_lines) {
   file_info this_file;
   char buffer[MAX_LENGTH];
   char *line_array[nr_lines];
   int length = 0;
   while (fgets(buffer, MAX_LENGTH, infile) != NULL) {
      char *line = malloc(strlen(buffer) + 1);
      line_array[length++] = line;
   }
   for (int i = 0; i < length; ++i) printf("%s\n", line_array[i]);
   this_file.line_array = line_array;
   this_file.length     = length;
   this_file.nr_lines   = nr_lines;
   return this_file;
}

void sort_array (List list, file_info this_file) {
   char **holder;
   for (int i = 0; i < this_file.length; ++i) {}
}

int main (int argc, char **argv) {
   List list = newList();
   if (argc != 3) {
      fprintf(stderr, "Usage: Lex [input file] [output file]\n");
      return EXIT_FAILURE;
   }
   FILE *infile = fopen(argv[1], "r");
   if (infile == NULL) {
      fprintf(stderr, "File: %s not found", argv[1]);
      return EXIT_FAILURE;
   }
   size_t nr_lines = 0;
   char buffer[MAX_LENGTH];
   while (fgets(buffer, MAX_LENGTH, infile) != NULL) ++nr_lines;
   if(fclose(infile)) {
      fprintf(stderr, "main(): failed to close %s\n", argv[1]);
      return EXIT_FAILURE;
   }
   infile = fopen(argv[1], "r");
   sort_array(list, insert_file(infile, nr_lines));
   return EXIT_SUCCESS;
}
