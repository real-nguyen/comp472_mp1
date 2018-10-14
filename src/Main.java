public class Main {

	public static void main(String[] args) {
		Puzzle p = new Puzzle();	
		
		DepthFirstSearch dfs = new DepthFirstSearch(p);
		dfs.getSolutionPath();
		
		BreadthFirstSearch bfs = new BreadthFirstSearch(p);
		bfs.getSolutionPath();
		
		BestFirstSearch befs = new BestFirstSearch(p, "h1");
		befs.getSolutionPath();
		
		AStarSearch ass = new AStarSearch(p, "h1");
		ass.getSolutionPath();
	}
}
