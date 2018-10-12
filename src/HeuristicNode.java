import java.util.ArrayList;

//used for BFS and A*
public class HeuristicNode extends Node {
	
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
	
	//path cost function
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

	
	//heuristic function
	private void setHeuristicValue() {
		if (currentHeuristic == "h1") {
			heuristicValue = getTilesOutOfPlace();
		}
		else if (currentHeuristic == "h2") {
			heuristicValue = getChebyshevDistance();
		}
		else {
			heuristicValue = getSumOfPermutationInversions();
		}
	}
	
	private int getTilesOutOfPlace() {
		int[] goalState = Puzzle.getGoalState();
		int[] currentState = getPuzzle();
		int tilesOutOfPlace = 0;
		
		for (int i = 0; i < currentState.length; i++) {
			//ignore if empty tile
			if (currentState[i] == 0) {
				continue;
			}
			
			if (currentState[i] != goalState[i]) {
				tilesOutOfPlace++;
			}
		}
		
		return tilesOutOfPlace;
	}
	
	private int getChebyshevDistance() {
		//variation on Manhattan distance that accounts for diagonals
		//Source: https://en.wikipedia.org/wiki/Chebyshev_distance
		int[] goalState = Puzzle.getGoalState();
		int[] currentState = getPuzzle();
		int chebyshevDistance = 0;
		
		for (int i = 0; i < currentState.length; i++) {
			//ignore if empty tile
			if (currentState[i] == 0) {
				continue;
			}
			
			if (currentState[i] != goalState[i]) {				
				int[] point1 = convertIndexToCoordinates(i);
				int[] point2 = convertIndexToCoordinates(getGoalIndex(currentState[i], goalState));
				int dx = Math.abs(point2[0] - point1[0]);
				int dy = Math.abs(point2[1] - point1[1]);
				
				chebyshevDistance += Math.max(dx, dy);
			}
		}
		
		return chebyshevDistance;
	}
	
	private int[] convertIndexToCoordinates(int index) {
		//converts puzzle index to 2d coordinates (x, y)
		//note that the coordinates are 1-based
		//i.e. first position in 12-puzzle is (1, 1) and the last is (4, 3)
		int[] coordinates = new int[2];
		
		//x coordinates
		coordinates[0] = (index % Puzzle.getRowSize()) + 1;
		//y coordinates
		coordinates[1] = (index / Puzzle.getRowSize()) + 1;
		
		return coordinates;
	}
	
	private int getGoalIndex(int currentTile, int[] goalState) {
		int index = 0;
		
		while (currentTile != goalState[index]) {
			index++;
		}
		
		return index;
	}
	
	private int getSumOfPermutationInversions() {
		int sum = 0;
		int[] currentState = getPuzzle();
		
		for (int i = 0; i < currentState.length; i++) {
			//ignore if empty tile or last tile
			if (currentState[i] == 0 || i == currentState.length - 1) {
				continue;
			}
			
			for (int j = i; j < currentState.length; j++) {
				if (currentState[j] != 0 && currentState[i] > currentState[j]) {
					sum++;
				}
			}
		}
		
		return sum;
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
