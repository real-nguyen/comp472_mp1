import java.util.Comparator;

public class AStarComparator implements Comparator<HeuristicNode> {
	@Override
	public int compare(HeuristicNode n1, HeuristicNode n2) {
		//compares f(n)
		//f(n) = h(n) + g(n)
		//lower f(n) = better
		int fn1 = n1.getPathCost() + n1.getHeuristicValue();
		int fn2 = n2.getPathCost() + n2.getHeuristicValue();
		
		if (fn1 < fn2) {
			return -1;
		}
		else if (fn1 > fn2) {
			return 1;
		}		
		
		return 0;
	}
}
