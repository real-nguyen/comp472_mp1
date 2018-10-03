import java.util.Comparator;

public class Heuristic_TilesOutOfPlace implements Comparator<Node> {
	@Override
	public int compare(Node n1, Node n2) {
		//heuristic where tiles out of place w.r.t. goal state are counted
		//lower h(n) = better
		int[] goalState = Puzzle.getGoalState();
		int tilesOutOfPlace_n1 = 0;
		int tilesOutOfPlace_n2 = 0;
		
		for (int i = 0; i < goalState.length; i++) {
			if (n1.getPuzzle()[i] != goalState[i]) {
				tilesOutOfPlace_n1++;
			}
			if (n2.getPuzzle()[i] != goalState[i]) {
				tilesOutOfPlace_n2++;
			}
		}
		
		if (tilesOutOfPlace_n1 < tilesOutOfPlace_n2) {
			return -1;
		}
		else if (tilesOutOfPlace_n1 > tilesOutOfPlace_n2) {
			return 1;
		}		
		
		return 0;
	}

}
