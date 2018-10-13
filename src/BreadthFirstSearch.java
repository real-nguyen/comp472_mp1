import java.util.Stack;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {
	private final String OUTPUT_PATH = "output/puzzleBFS.txt";
	
	private Puzzle puzzle;	
	private Queue<Node> openList;
	private ArrayList<Node> closedList;
	private double elapsedTime;
	
	public BreadthFirstSearch(Puzzle puzzle) {
		this.puzzle = puzzle;
		this.openList = new LinkedList<Node>();
		this.closedList = new ArrayList<Node>();
	}
	
	public void getSolutionPath() {
		//search and print solution path
		//Source: https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it-in-java
		try {
			//will clear file every time
			PrintWriter pw = new PrintWriter(OUTPUT_PATH);
			
			Node initialState = new Node(puzzle);
			openList.add(initialState);
			boolean solutionFound = search(initialState);
			
			if (solutionFound) {
				//only print solution path if it is found
				pw.println("Solution found!");
				pw.println("Elapsed time: " + elapsedTime + " seconds");
				pw.println("Search path size: " + closedList.size());
				Node solutionPathNode = closedList.get(closedList.size() - 1);
				Stack<String> buffer = new Stack<String>();
				
				while (solutionPathNode != null) {
					Puzzle state = solutionPathNode.getStateRepresentation();
					//prefix must be 0 if initial state, otherwise use letter index of empty tile
					char prefix = solutionPathNode.getParentNode() != null ? state.getEmptyTilePosition() : '0';
					buffer.push(prefix + " " + state);				
					solutionPathNode = solutionPathNode.getParentNode();
				}
				
				pw.println("Solution path length: " + buffer.size());
				
				while (!buffer.isEmpty()) {
					pw.println(buffer.pop());
				}
			}
			else {
				//otherwise print entire search path
				pw.println("Solution not found :(");
				pw.println("Elapsed time: " + elapsedTime + " seconds");
				pw.println("Search path size: " + closedList.size());
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
		//measure elapsed time
		//not terribly accurate but serves our purposes
		//Source: https://www.baeldung.com/java-measure-elapsed-time
		long start = System.currentTimeMillis();
		long end = 0;
		
		while (!openList.isEmpty()) {
			nodeToVisit = openList.poll();
			visitNode(nodeToVisit);
			
			if (Puzzle.isSolved(nodeToVisit.getPuzzle())) {
				end = System.currentTimeMillis();
				elapsedTime = (end - start) / 1000.00;
				System.out.println("Solution found in " + getClass());
				System.out.println("openList.size(): " + openList.size());
				System.out.println("closedList.size(): " + closedList.size());
				System.out.println("Elapsed time: " + elapsedTime + " seconds");
				System.out.println("----------");
				return true;
			}
			
			ArrayList<Node> childNodes = generateChildNodes(nodeToVisit);
			
			//need to reverse list to push to stack in correct order
			//i.e. to traverse tree left to right
			Collections.reverse(childNodes);
			
			for (Node child : childNodes) {
				addChildNode(nodeToVisit, child);
			}
		}
		
		end = System.currentTimeMillis();
		elapsedTime = (end - start) / 1000.00;
		System.out.println("No solution found in " + getClass());
		System.out.println("openList.size(): " + openList.size());
		System.out.println("closedList.size(): " + closedList.size());
		System.out.println("Elapsed time: " + elapsedTime + " seconds");
		System.out.println("----------");
		
		return false;
	}
	
	private void visitNode(Node n) {
		n.visit();
		closedList.add(n);
	}
	
	private boolean isDuplicateState(Node n) {
		return openList.contains(n) || closedList.contains(n);
	}
	
	private ArrayList<Node> generateChildNodes(Node currentNode) {
		//generate all possible moves from this state and add them as children
		ArrayList<Node> childNodes = new ArrayList<Node>();
		
		Puzzle currentState = currentNode.getStateRepresentation();
		if (currentState.canMoveUp()) {
			childNodes.add(new Node(currentState.moveUp()));
		}
		if (currentState.canMoveUpRight()) {
			childNodes.add(new Node(currentState.moveUpRight()));
		}
		if (currentState.canMoveRight()) {
			childNodes.add(new Node(currentState.moveRight()));
		}
		if (currentState.canMoveDownRight()) {
			childNodes.add(new Node(currentState.moveDownRight()));
		}
		if (currentState.canMoveDown()) {
			childNodes.add(new Node(currentState.moveDown()));
		}
		if (currentState.canMoveDownLeft()) {
			childNodes.add(new Node(currentState.moveDownLeft()));
		}
		if (currentState.canMoveLeft()) {
			childNodes.add(new Node(currentState.moveLeft()));
		}
		if (currentState.canMoveUpLeft()) {
			childNodes.add(new Node(currentState.moveUpLeft()));
		}
		
		return childNodes;
	}
	
	private void addChildNode(Node currentNode, Node nodeToAdd) {
		if (!isDuplicateState(nodeToAdd)) {
			currentNode.addChild(nodeToAdd);
			openList.add(nodeToAdd);
		}
	}
}
