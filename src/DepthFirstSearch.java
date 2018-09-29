import java.util.Stack;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DepthFirstSearch {
	private final String OUTPUT_PATH = "output/puzzleDFS.txt";
	
	private Puzzle puzzle;	
	private Stack<Node> openList;
	private ArrayList<Node> closedList;
	
	public DepthFirstSearch(Puzzle puzzle) {
		this.puzzle = puzzle;
		this.openList = new Stack<Node>();
		this.closedList = new ArrayList<Node>();
	}
	
//	public void getSolutionPath() {
//		//search and print solution path
//		//Source: https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it-in-java
//		try {
//			//will clear file every time
//			PrintWriter pw = new PrintWriter(OUTPUT_PATH);
//			
//			//first, print initial state
//			pw.println("0 " + puzzle);
//			
//			//add initial state to open list
//			openList.add(new Node(puzzle.getPuzzle(), puzzle.getEmptyTilePosition()));
//			
//			
//			pw.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public void getSolutionPath() {
		Node initialState = new Node(puzzle.getPuzzle(), puzzle.getEmptyTilePosition());

		openList.add(initialState);
		search(initialState);
	}
	
	public boolean search(Node n) {
		while (!openList.isEmpty()) {
			if (puzzle.isSolved()) {
				return true;
			}
			
			n.visit();
			closedList.add(n);
			
			
			
			for (Node child : n.getChildNodes()) {
				
			}
		}		
		return false;
	}
	
	private void getPossibleMoves() {
		
	}
}
