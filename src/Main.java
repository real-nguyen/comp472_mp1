public class Main {

	public static void main(String[] args) {
		Puzzle p = new Puzzle();	
		
		DepthFirstSearch dfs = new DepthFirstSearch(p);
		dfs.getSolutionPath();
		
		BreadthFirstSearch bfs = new BreadthFirstSearch(p);
		bfs.getSolutionPath();
		
		BestFirstSearch befs = new BestFirstSearch(p, "h2");
		befs.getSolutionPath();
		
		AlgorithmAStar aas = new AlgorithmAStar(p, "h2");
		aas.getSolutionPath();
	}
}
