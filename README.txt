How to run Mini-Project 1
1. Import project into Eclipse
2. Select which puzzle you would like to solve in input/input.txt
3. Open Main.java in src/(default package)
4. Run code
5. Check outputs in output folder

Notes:
- Heuristics:
	- h1 = number of tiles out of place
	- h2 = Chebyshev distance
	- hx = sum of permutation inversions
- You can change "h1" in the BFS and A* search initializations to "h2" and "hx" to change heuristics
- You can comment/uncomment lines in input.txt, but the program will always solve the last uncommented puzzle
- To change the dimensions of the puzzle, go in the Puzzle class, change ROW_SIZE and COLUMN_size and append "-3x3" or "-4x4" to INPUT_FILE. These are the only acceptable dimensions, apart from the default 3x4
- Check the Java console for additional information