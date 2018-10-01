import java.io.*;
import java.util.Arrays;

public class Puzzle {
	// initialize row/column size as constants because they are dependent on the input file
	private static final int ROW_SIZE = 4;
	private static final int COLUMN_SIZE = 3;
	private static final String INPUT_FILE = "input/input.txt";
	private static final String POSITIONS = "abcdefghijklmnopqrstuvwxyz";
	
	private int[] puzzle;
	private static int[] initialState;
	private static int[] goalState;
	
	public Puzzle() {
		//read directly from input file and initialize puzzle array
		puzzle = new int[ROW_SIZE * COLUMN_SIZE];
		
		if (initialState == null) {
			readInputFile();
		}
		
		if (goalState == null) {
			initializeGoalState();
		}
	}
	
	public Puzzle(int[] puzzle) {
		this.puzzle = puzzle;
	}
	
	private void readInputFile() {
		//Source: https://stackoverflow.com/questions/731365/reading-and-displaying-data-from-a-txt-file
		
		
		//TEST PURPOSES ONLY
		initialState = new int[] {1, 2, 3, 4, 5, 6, 7, 0, 9, 10, 11, 8};
		puzzle = Arrays.copyOf(initialState, initialState.length);
		//END TEST
				
//		initialState = new int[ROW_SIZE * COLUMN_SIZE];
//		try {
//			BufferedReader in = new BufferedReader(new FileReader(INPUT_FILE));
//			String line;
//			while ((line = in.readLine()) != null)
//			{
//			    String[] tiles = line.split(" ");
//			    
//			    for (int i = 0; i < tiles.length; i++) {
//			    	puzzle[i] = Integer.parseInt(tiles[i]);
//			    }
//			}
//			in.close();
//		} catch (IOException e) {
//			//either file not found or error while reading
//			e.printStackTrace();
//		}
	}
	
	private void initializeGoalState() {
		goalState = new int[ROW_SIZE * COLUMN_SIZE];
		for (int i = 0; i < puzzle.length - 1; i++) {
			goalState[i] = i + 1;
		}
		goalState[puzzle.length - 1] = 0;
	}
	
	public String toString() {
		//prints contents of puzzle separated by comma
		//Source: https://stackoverflow.com/questions/409784/whats-the-simplest-way-to-print-a-java-array
		return Arrays.toString(puzzle);
	}
	
	public char getPosition(int puzzleIndex) {
		return POSITIONS.charAt(puzzleIndex);
	}
	
	public int getEmptyTileIndex() {
		for (int i = 0; i < puzzle.length; i++) {
			if (puzzle[i] == 0) {
				return i;
			}
		}
		
		return 0;
	}
	
	public char getEmptyTilePosition() {
		return POSITIONS.charAt(getEmptyTileIndex());
	}
	
	public static boolean isSolved(int[] puzzle) {
		return Arrays.equals(puzzle, goalState);
	}
	
	private Puzzle swapTiles(int indexA, int indexB) {
		//must return a new object each time
		//multiple branches are based on a single state
		//if you don't return a new object, then branches will be based on other moves
		int[] copy = Arrays.copyOf(puzzle, puzzle.length);		
		Puzzle newPuzzle = new Puzzle(copy);
		
		int temp = copy[indexA];
		copy[indexA] = copy[indexB];
		copy[indexB] = temp;
		
		return newPuzzle;
	}
	
	//BEGIN set of operators
	public Puzzle moveUp() {		
		int emptyTileIndex = getEmptyTileIndex();
		//can't move up if in top row
		if (emptyTileIndex <= ROW_SIZE - 1) {
			return this;
		}
		
		return swapTiles(emptyTileIndex, emptyTileIndex - ROW_SIZE);
	}
	
	public Puzzle moveUpRight() {
		int emptyTileIndex = getEmptyTileIndex();
		//can't move up if in top row or in rightmost column
		if (emptyTileIndex <= ROW_SIZE - 1 || (emptyTileIndex + 1) % ROW_SIZE == 0) {
			return this;
		}
		
		return swapTiles(emptyTileIndex, emptyTileIndex - ROW_SIZE + 1);
	}
	
	public Puzzle moveRight() {
		int emptyTileIndex = getEmptyTileIndex();
		//can't move right if in rightmost column
		if ((emptyTileIndex + 1) % ROW_SIZE == 0) {
			return this;
		}
		
		return swapTiles(emptyTileIndex, emptyTileIndex + 1);
	}
	
	public Puzzle moveDownRight() {
		int emptyTileIndex = getEmptyTileIndex();
		int lastIndex = puzzle.length - 1;
		int firstLastRowIndex = puzzle.length - ROW_SIZE;
		
		//can't move right if in rightmost column or in bottom row
		if ((emptyTileIndex + 1) % ROW_SIZE == 0 || (emptyTileIndex >= firstLastRowIndex && emptyTileIndex <= lastIndex)) {
			return this;
		}
		
		return swapTiles(emptyTileIndex, emptyTileIndex + ROW_SIZE + 1);
	}
	
	public Puzzle moveDown() {
		int emptyTileIndex = getEmptyTileIndex();
		int lastIndex = puzzle.length - 1;
		int firstLastRowIndex = puzzle.length - ROW_SIZE;
		
		//can't move down if in bottom row
		if (emptyTileIndex >= firstLastRowIndex && emptyTileIndex <= lastIndex) {
			return this;
		}
		
		return swapTiles(emptyTileIndex, emptyTileIndex + ROW_SIZE);
	}
	
	public Puzzle moveDownLeft() {
		int emptyTileIndex = getEmptyTileIndex();
		int lastIndex = puzzle.length - 1;
		int firstLastRowIndex = puzzle.length - ROW_SIZE;
		
		//can't move left if in leftmost column or in bottom row
		if (emptyTileIndex % ROW_SIZE == 0 || (emptyTileIndex >= firstLastRowIndex && emptyTileIndex <= lastIndex)) {
			return this;
		}
		
		return swapTiles(emptyTileIndex, emptyTileIndex + ROW_SIZE - 1);
	}
	
	public Puzzle moveLeft() {
		int emptyTileIndex = getEmptyTileIndex();
		//can't move left if in leftmost column
		if (emptyTileIndex % ROW_SIZE == 0) {
			return this;
		}
		
		return swapTiles(emptyTileIndex, emptyTileIndex - 1);
	}
	
	public Puzzle moveUpLeft() {
		int emptyTileIndex = getEmptyTileIndex();
		//can't move left if in leftmost column or in top row
		if (emptyTileIndex % ROW_SIZE == 0 || emptyTileIndex <= ROW_SIZE - 1) {
			return this;
		}
		
		return swapTiles(emptyTileIndex, emptyTileIndex - ROW_SIZE - 1);
	}
	
	public int[] getPuzzle() {
		return puzzle;
	}
	//END set of operators
}
