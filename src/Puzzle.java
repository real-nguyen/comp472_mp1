import java.io.*;
import java.util.Arrays;

public class Puzzle {
	// initialize row/column size as constants because they are dependent on the input file
	private static final int ROW_SIZE = 4;
	private static final int COLUMN_SIZE = 3;
	private static final String INPUT_FILE = "input/input.txt";
	private static final String POSITIONS = "abcdefghijklmnopqrstuvwxyz";
	
	private int[] puzzle;
	private static int[] goalState;
	
	public Puzzle() {
		//read directly from input file and initialize puzzle array
		puzzle = new int[ROW_SIZE * COLUMN_SIZE];
		readInputFile();
		if (goalState == null) {
			goalState = new int[ROW_SIZE * COLUMN_SIZE];
			for (int i = 0; i < puzzle.length - 1; i++) {
				goalState[i] = i + 1;
			}
			goalState[puzzle.length - 1] = 0;
		}
	}
	
	private void readInputFile() {
		//Source: https://stackoverflow.com/questions/731365/reading-and-displaying-data-from-a-txt-file
		try {
			BufferedReader in = new BufferedReader(new FileReader(INPUT_FILE));
			String line;
			while ((line = in.readLine()) != null)
			{
			    String[] tiles = line.split(" ");
			    
			    for (int i = 0; i < tiles.length; i++) {
			    	puzzle[i] = Integer.parseInt(tiles[i]);
			    }
			}
			in.close();
		} catch (IOException e) {
			//either file not found or error while reading
			e.printStackTrace();
		}
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
	
	public boolean isSolved() {
		return Arrays.equals(puzzle, goalState);
	}
	
	private int[] swapTiles(int indexA, int indexB) {
		int temp = puzzle[indexA];
		puzzle[indexA] = puzzle[indexB];
		puzzle[indexB] = temp;
		
		return puzzle;
	}
	
	//BEGIN set of operators
	public int[] moveUp() {		
		int emptyTileIndex = getEmptyTileIndex();
		//can't move up if in top row
		if (emptyTileIndex <= ROW_SIZE - 1) {
			return puzzle;
		}
		
		return swapTiles(emptyTileIndex, emptyTileIndex - ROW_SIZE);
	}
	
	public int[] moveUpRight() {
		int emptyTileIndex = getEmptyTileIndex();
		//can't move up if in top row or in rightmost column
		if (emptyTileIndex <= ROW_SIZE - 1 || (emptyTileIndex + 1) % ROW_SIZE == 0) {
			return puzzle;
		}
		
		return swapTiles(emptyTileIndex, emptyTileIndex - ROW_SIZE + 1);
	}
	
	public int[] moveRight() {
		int emptyTileIndex = getEmptyTileIndex();
		//can't move right if in rightmost column
		if ((emptyTileIndex + 1) % ROW_SIZE == 0) {
			return puzzle;
		}
		
		return swapTiles(emptyTileIndex, emptyTileIndex + 1);
	}
	
	public int[] moveDownRight() {
		int emptyTileIndex = getEmptyTileIndex();
		int lastIndex = puzzle.length - 1;
		int firstLastRowIndex = puzzle.length - ROW_SIZE;
		
		//can't move right if in rightmost column or in bottom row
		if ((emptyTileIndex + 1) % ROW_SIZE == 0 || (emptyTileIndex >= firstLastRowIndex && emptyTileIndex <= lastIndex)) {
			return puzzle;
		}
		
		return swapTiles(emptyTileIndex, emptyTileIndex + ROW_SIZE + 1);
	}
	
	public int[] moveDown() {
		int emptyTileIndex = getEmptyTileIndex();
		int lastIndex = puzzle.length - 1;
		int firstLastRowIndex = puzzle.length - ROW_SIZE;
		
		//can't move down if in bottom row
		if (emptyTileIndex >= firstLastRowIndex && emptyTileIndex <= lastIndex) {
			return puzzle;
		}
		
		return swapTiles(emptyTileIndex, emptyTileIndex + ROW_SIZE);
	}
	
	public int[] moveDownLeft() {
		int emptyTileIndex = getEmptyTileIndex();
		int lastIndex = puzzle.length - 1;
		int firstLastRowIndex = puzzle.length - ROW_SIZE;
		
		//can't move left if in leftmost column or in bottom row
		if (emptyTileIndex % ROW_SIZE == 0 || (emptyTileIndex >= firstLastRowIndex && emptyTileIndex <= lastIndex)) {
			return puzzle;
		}
		
		return swapTiles(emptyTileIndex, emptyTileIndex + ROW_SIZE - 1);
	}
	
	public int[] moveLeft() {
		int emptyTileIndex = getEmptyTileIndex();
		//can't move left if in leftmost column
		if (emptyTileIndex % ROW_SIZE == 0) {
			return puzzle;
		}
		
		return swapTiles(emptyTileIndex, emptyTileIndex - 1);
	}
	
	public int[] moveUpLeft() {
		int emptyTileIndex = getEmptyTileIndex();
		//can't move left if in leftmost column or in top row
		if (emptyTileIndex % ROW_SIZE == 0 || emptyTileIndex <= ROW_SIZE - 1) {
			return puzzle;
		}
		
		return swapTiles(emptyTileIndex, emptyTileIndex - ROW_SIZE - 1);
	}
	
	public int[] getPuzzle() {
		return puzzle;
	}
	//END set of operators
}
