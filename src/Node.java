import java.util.ArrayList;
import java.util.Arrays;

public class Node {
	private Node parentNode;
	private ArrayList<Node> childNodes;
	private Puzzle stateRepresentation;
	private boolean isVisited;
	
	public Node(Node parentNode, ArrayList<Node> childNodes, Puzzle stateRepresentation) {
		this.parentNode = parentNode;
		this.childNodes = childNodes;
		this.stateRepresentation = stateRepresentation;
	}		
	
	public Node(Node parentNode, Puzzle stateRepresentation) {
		this.parentNode = parentNode;
		this.stateRepresentation = stateRepresentation;
		this.childNodes = new ArrayList<Node>();
	}
	
	public Node(Puzzle stateRepresentation) {
		this.stateRepresentation = stateRepresentation;
		this.childNodes = new ArrayList<Node>();
	}
	
	public Node(Node n) {
		//for deep copying
		this(n.getParentNode(), n.getChildNodes(), n.getStateRepresentation());
	}
	
	public Node() {
		//TEST PURPOSES ONLY
		this.childNodes = new ArrayList<Node>();
	}
	
	public Node addChild(Node n) {
		if (!childNodes.contains(n)) {
			childNodes.add(n);
			n.parentNode = this;
			return n;
		}
		
		return null;
	}
	
	public void addChildren(ArrayList<Node> nodes) {
		//to avoid adding duplicate states
		for (Node n : nodes) {
			addChild(n);
		}
	}
	
	public Node getParentNode() {
		return parentNode;
	}
	
	public ArrayList<Node> getChildNodes() {
		ArrayList<Node> newList = new ArrayList<Node>();
		
		for (Node n : childNodes) {
			newList.add(new Node(n));
		}
		
		return newList;
	}
	
	public Puzzle getStateRepresentation() {
		return stateRepresentation;
	}

	public boolean isVisited() {
		return isVisited;
	}
	
	public void visit() {
		isVisited = true;
	}
	
	public boolean isLeaf() {
		return childNodes.size() == 0;
	}

	public int[] getPuzzle() {
		return stateRepresentation.getPuzzle();
	}
	
	@Override
	public boolean equals(Object o) {
		//Source: https://www.geeksforgeeks.org/overriding-equals-method-in-java/
		//for collections' contains method
		//will not add to the stack/queue/priority queue if this node's state is in the collection
		if (o == this) {
			return true;
		}
		
		if (!(o instanceof Node)) {
			return false;
		}
		
		Node n = (Node) o;
		
		return Arrays.equals(this.getPuzzle(), n.getPuzzle());
	}
	
	public String toString() {
		return this.getStateRepresentation().toString();
	}
}
