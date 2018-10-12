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
- Check the Java console for additional information