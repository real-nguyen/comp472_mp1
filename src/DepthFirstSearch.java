import java.util.Stack;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

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
		Node initialState = new Node(puzzle);
		generateSearchTree(initialState);
		
		openList.push(initialState);
		search(initialState);
	}
	
	public boolean search(Node nodeToVisit) {
		if (!openList.isEmpty()) {
			visitNode(nodeToVisit);
			
			if (Puzzle.isSolved(nodeToVisit.getPuzzle())) {
				return true;
			}
			
			//need to reverse list to push to stack in correct order
			//i.e. to traverse tree left to right
			ArrayList<Node> childNodes = nodeToVisit.getChildNodes();
			Collections.reverse(childNodes);
			
			for (Node child : childNodes) {
				openList.push(child);
			}
			return search(openList.pop());
		}
		return false;
	}
	
	private void visitNode(Node n) {
		n.visit();
		closedList.add(n);		
	}
	
	private void addNodeToOpenList(Node n) {
		if (!n.isVisited()) {
			openList.push(n);
		}
	}
	
	private void generateSearchTree(Node n) {
		//generate all possible moves from this state and add them as children
		//only add nodes if they are not in the search tree or have not been generated

		if (openList.contains(n) || closedList.contains(n)) {
			return;
		}
	}
}
