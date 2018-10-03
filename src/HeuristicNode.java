import java.util.ArrayList;

//used for BFS and A*
public class HeuristicNode extends Node {

	private static final String TILES_OUT_OF_PLACE = "h1";
	private static final String CHEBYSHEV_DISTANCE = "h2";
	
	private int pathCost;			//g(n)		
	private int heuristicValue;		//h(n)
	private String currentHeuristic;
	
	public HeuristicNode(Node parentNode, ArrayList<Node> childNodes, Puzzle stateRepresentation, String heuristic) {
		super(parentNode, childNodes, stateRepresentation);
		this.currentHeuristic = heuristic;
		increasePathCost();		
		setHeuristicValue();
	}

	public HeuristicNode(Node parentNode, Puzzle stateRepresentation, String heuristic) {
		super(parentNode, stateRepresentation);
		this.currentHeuristic = heuristic;
		increasePathCost();		
		setHeuristicValue();
	}

	public HeuristicNode(Puzzle stateRepresentation, String heuristic) {
		super(stateRepresentation);
		this.currentHeuristic = heuristic;
		increasePathCost();		
		setHeuristicValue();
	}

	public HeuristicNode(Node n, String heuristic) {
		super(n);
		this.currentHeuristic = heuristic;
		increasePathCost();		
		setHeuristicValue();
	}

	public HeuristicNode(String heuristic) {
		super();
		this.currentHeuristic = heuristic;
		increasePathCost();		
		setHeuristicValue();
	}

	public int getPathCost() {
		return pathCost;
	}
	
	public void increasePathCost() {
		//assume that all moves costs 1
		//if no parent, pathCost = 0
		if (parentNode != null) {
			HeuristicNode temp = (HeuristicNode)parentNode;
			pathCost = temp.getPathCost() + 1;
		}
	}
	
	public int getHeuristicValue() {
		return heuristicValue;
	}

	
	private void setHeuristicValue() {
		if (currentHeuristic == TILES_OUT_OF_PLACE) {
			heuristicValue = getTilesOutOfPlace();
		}
		else if (currentHeuristic == CHEBYSHEV_DISTANCE) {
			heuristicValue = getChebyshevDistance();
		}
		else {
			
		}
	}
	
	public void setCurrentHeuristic(String heuristic) {
		//switching heuristics mid-search will probably have adverse effects
		//only use this when you're done with the previous search
		this.currentHeuristic = heuristic;
	}	
	
	private int getTilesOutOfPlace() {
		int[] goalState = Puzzle.getGoalState();
		int[] currentState = getPuzzle();
		int tilesOutOfPlace = 0;
		
		for (int i = 0; i < currentState.length; i++) {
			if (currentState[i] != goalState[i]) {
				tilesOutOfPlace++;
			}
		}
		
		return tilesOutOfPlace;
	}
	
	private int getChebyshevDistance() {
		//variation on Manhattan distance that accounts for diagonals
		//Source: https://en.wikipedia.org/wiki/Chebyshev_distance
		return 0;
	}
	
	@Override
	public Node addChild(Node n) {
		if (!childNodes.contains(n)) {
			childNodes.add(n);
			n.parentNode = this;
			((HeuristicNode)n).increasePathCost();
			return n;
		}
		
		return null;
	}
}
