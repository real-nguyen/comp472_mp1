import java.util.ArrayList;

public class Tree {
	private Node root;
	private int depth;
	
	public Tree(Node root) {
		this.root = root;
	}
	
	public Node getRoot() {
		return new Node(root);
	}
	
	//Do you really need this?
//	public int getDepth(Node n, int depth) {
//		if (n.getChildNodes().size() == 0) {
//			return 0;
//		}
//		
//		for (Node child : n.getChildNodes()) {
//			depth = Math.max(depth, getDepth(child, depth) + 1);
//		}
//		
//		return depth;
//	}
}
