public class Main {

	public static void main(String[] args) {
		Puzzle p = new Puzzle();	
		
		DepthFirstSearch dfs = new DepthFirstSearch(p);
		//dfs.getSolutionPath();
		
		BestFirstSearch bfs = new BestFirstSearch(p, "h1");
		bfs.getSolutionPath();
	}
}
