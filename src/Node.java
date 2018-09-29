import java.util.ArrayList;

public class Node {
	private Node parentNode;
	private ArrayList<Node> childNodes;
	private int[] stateRepresentation;
	private char emptyTilePosition;
	private boolean isVisited;
	
	public Node(Node parentNode, ArrayList<Node> childNodes, int[] stateRepresentation, char emptyTilePosition) {
		this.parentNode = parentNode;
		this.childNodes = childNodes;
		this.stateRepresentation = stateRepresentation;
		this.emptyTilePosition = emptyTilePosition;
	}		
	
	public Node(Node parentNode, int[] stateRepresentation, char emptyTilePosition) {
		this.parentNode = parentNode;
		this.stateRepresentation = stateRepresentation;
		this.childNodes = new ArrayList<Node>();
		this.emptyTilePosition = emptyTilePosition;
	}
	
	public Node(int[] stateRepresentation, char emptyTilePosition) {
		this.stateRepresentation = stateRepresentation;
		this.childNodes = new ArrayList<Node>();
		this.emptyTilePosition = emptyTilePosition;
	}
	
	public Node(Node n) {
		//for deep copying
		this(n.getParentNode(), n.getChildNodes(), n.getStateRepresentation(), n.getEmptyTilePosition());
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
	
	public int[] getStateRepresentation() {
		return stateRepresentation;
	}
	
	public char getEmptyTilePosition() {
		return emptyTilePosition;
	}
	
	public boolean getIsVisited() {
		return isVisited;
	}
	
	public void visit() {
		isVisited = true;
	}
}
