import java.util.Comparator;

public class BFSComparator implements Comparator<HeuristicNode> {
	@Override
	public int compare(HeuristicNode n1, HeuristicNode n2) {
		//only compares h(n)
		//lower h(n) = better
		if (n1.getHeuristicValue() < n2.getHeuristicValue()) {
			return -1;
		}
		else if (n1.getHeuristicValue() > n2.getHeuristicValue()) {
			return 1;
		}		
		
		return 0;
	}

}
