public class Main {

	public static void main(String[] args) {
		Puzzle p = new Puzzle();	
		
		DepthFirstSearch dfs = new DepthFirstSearch(p);
		dfs.getSolutionPath();
		
		BestFirstSearch bfs = new BestFirstSearch(p, "hx");
		bfs.getSolutionPath();
		
		AStarSearch ass = new AStarSearch(p, "hx");
		ass.getSolutionPath();
	}
}
