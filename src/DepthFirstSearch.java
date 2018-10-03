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
	
	public void getSolutionPath() {
//		//search and print solution path
//		//Source: https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it-in-java
		try {
			//will clear file every time
			PrintWriter pw = new PrintWriter(OUTPUT_PATH);
			
			Node initialState = new Node(puzzle);
			openList.push(initialState);
			boolean solutionFound = search(initialState);
			
			if (solutionFound) {
				//only print solution path if it is found
				pw.println("Solution found!");
				Node solutionPathNode = closedList.get(closedList.size() - 1);
				Stack<String> buffer = new Stack<String>();
				
				while (solutionPathNode != null) {
					Puzzle state = solutionPathNode.getStateRepresentation();
					//prefix must be 0 if initial state, otherwise use letter index of empty tile
					char prefix = solutionPathNode.getParentNode() != null ? state.getEmptyTilePosition() : '0';
					buffer.push(prefix + " " + state);				
					solutionPathNode = solutionPathNode.getParentNode();
				}
				
				while (!buffer.isEmpty()) {
					pw.println(buffer.pop());
				}
			}
			else {
				//otherwise print entire search path
				pw.println("Solution not found :(");
				for (int i = 0; i < closedList.size(); i++) {
					//prefix must be 0 if initial state, otherwise use letter index of empty tile				
					Puzzle state = closedList.get(i).getStateRepresentation();
					char prefix = i == 0 ? '0' : state.getEmptyTilePosition();
					pw.println(prefix + " " + state);
				}
			}		
			
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	
	private boolean search(Node nodeToVisit) {
		try {
			if (!openList.isEmpty()) {
				nodeToVisit = openList.pop();
				visitNode(nodeToVisit);
				
				if (Puzzle.isSolved(nodeToVisit.getPuzzle())) {
					return true;
				}
				
				generateChildNodes(nodeToVisit);
				
				//need to reverse list to push to stack in correct order
				//i.e. to traverse tree left to right
				ArrayList<Node> childNodes = nodeToVisit.getChildNodes();
				Collections.reverse(childNodes);
				
				for (Node child : childNodes) {
					openList.push(child);				
				}
				return search(nodeToVisit);
			}
		} catch (StackOverflowError e) {
			//Seems like bruteforcing didn't work...
			System.out.println("Stack overflow.");
			System.out.println("openList.size(): " + openList.size());
			System.out.println("closedList.size(): " + closedList.size());
		}
		
		return false;
	}
	
	private void visitNode(Node n) {
		n.visit();
		closedList.add(n);
	}
	
	private boolean isDuplicateState(Node n) {
		return openList.contains(n) || closedList.contains(n);
	}
	
	private void generateChildNodes(Node currentNode) {
		//generate all possible moves from this state and add them as children
		
		Puzzle currentState = currentNode.getStateRepresentation();
		if (currentState.canMoveUp()) {
			addChildNode(currentNode, new Node(currentState.moveUp()));
		}
		if (currentState.canMoveUpRight()) {
			addChildNode(currentNode, new Node(currentState.moveUpRight()));
		}
		if (currentState.canMoveRight()) {
			addChildNode(currentNode, new Node(currentState.moveRight()));
		}
		if (currentState.canMoveDownRight()) {
			addChildNode(currentNode, new Node(currentState.moveDownRight()));
		}
		if (currentState.canMoveDown()) {
			addChildNode(currentNode, new Node(currentState.moveDown()));
		}
		if (currentState.canMoveDownLeft()) {
			addChildNode(currentNode, new Node(currentState.moveDownLeft()));
		}
		if (currentState.canMoveLeft()) {
			addChildNode(currentNode, new Node(currentState.moveLeft()));
		}
		if (currentState.canMoveUpLeft()) {
			addChildNode(currentNode, new Node(currentState.moveUpLeft()));
		}
	}
	
	private void addChildNode(Node currentNode, Node nodeToAdd) {
		if (!isDuplicateState(nodeToAdd)) {
			currentNode.addChild(nodeToAdd);
		}
	}
}
