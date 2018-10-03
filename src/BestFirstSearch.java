import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

public class BestFirstSearch {
	private String outputPath = "output/puzzleBFS-_.txt";	
	private Puzzle puzzle;	
	private PriorityQueue<HeuristicNode> openList;
	private ArrayList<Node> closedList;
	private String currentHeuristic;
	
	public BestFirstSearch(Puzzle puzzle, String heuristic) {
		this.puzzle = puzzle;
		this.currentHeuristic = heuristic;
		openList = new PriorityQueue<HeuristicNode>(new BFSComparator());
		outputPath = outputPath.replaceAll("_", heuristic);
		closedList = new ArrayList<Node>();
	}
	
	public void getSolutionPath() {
		//search and print solution path
		//Source: https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it-in-java
		try {
			//will clear file every time
			PrintWriter pw = new PrintWriter(outputPath);
			
			HeuristicNode initialState = new HeuristicNode(puzzle, currentHeuristic);
			openList.offer(initialState);
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
	
	public boolean search(HeuristicNode nodeToVisit) {
		if (!openList.isEmpty()) {			
			nodeToVisit = openList.poll();				
			visitNode(nodeToVisit);
			
			if (Puzzle.isSolved(nodeToVisit.getPuzzle())) {
				return true;
			}
			
			ArrayList<HeuristicNode> childNodes = generateChildNodes(nodeToVisit);
			
			for (HeuristicNode child : childNodes) {
				addChildNode(nodeToVisit, child);
			}						
			
			return search(nodeToVisit);			
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
	
	private ArrayList<HeuristicNode> generateChildNodes(HeuristicNode currentNode) {
		//generate all possible moves from this state and add them as children
		ArrayList<HeuristicNode> childNodes = new ArrayList<HeuristicNode>();
		
		Puzzle currentState = currentNode.getStateRepresentation();
		if (currentState.canMoveUp()) {
			childNodes.add(new HeuristicNode(currentState.moveUp(), currentHeuristic));
		}
		if (currentState.canMoveUpRight()) {
			childNodes.add(new HeuristicNode(currentState.moveUpRight(), currentHeuristic));
		}
		if (currentState.canMoveRight()) {
			childNodes.add(new HeuristicNode(currentState.moveRight(), currentHeuristic));
		}
		if (currentState.canMoveDownRight()) {
			childNodes.add(new HeuristicNode(currentState.moveDownRight(), currentHeuristic));
		}
		if (currentState.canMoveDown()) {
			childNodes.add(new HeuristicNode(currentState.moveDown(), currentHeuristic));
		}
		if (currentState.canMoveDownLeft()) {
			childNodes.add(new HeuristicNode(currentState.moveDownLeft(), currentHeuristic));
		}
		if (currentState.canMoveLeft()) {
			childNodes.add(new HeuristicNode(currentState.moveLeft(), currentHeuristic));
		}
		if (currentState.canMoveUpLeft()) {
			childNodes.add(new HeuristicNode(currentState.moveUpLeft(), currentHeuristic));
		}
		
		return childNodes;
	}
	
	private void addChildNode(HeuristicNode currentNode, HeuristicNode nodeToAdd) {
		if (!isDuplicateState(nodeToAdd)) {
			currentNode.addChild(nodeToAdd);
			openList.offer(nodeToAdd);
		}
	}
}
