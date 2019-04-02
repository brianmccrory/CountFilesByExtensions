
A simple file to count files recursively in each directory, counting by file extensions. 

First edit the 'input.txt' file and add one or more directories to search for files.

$ vi input.txt

Then compile:

$ javac CountFiles.java

Then run:

$ java CountFiles       # uses file 'input.txt' by default

	OR
	
$ java CountFiles <input file>    # use a different file containing dirs to search

