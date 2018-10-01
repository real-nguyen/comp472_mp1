import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Puzzle p = new Puzzle();
		
		Node root = new Node(p);
		Node firstChild = new Node(
			root,
			p.moveUp()
		);
		Node secondChild = new Node(
			root,
			p.moveDown()
		);

		Node thirdChild = new Node(
			root,
			p.moveDownLeft()		
		);
		Node fourthChild = new Node(
			root,
			p.moveLeft()		
		);
		Node fifthChild = new Node(
			root,
			p.moveUpLeft()		
		);
		
		root.addChild(firstChild);
		root.addChild(secondChild);
		root.addChild(thirdChild);
		root.addChild(fourthChild);
		root.addChild(fifthChild);
		
		System.out.println("root: " + root.getStateRepresentation());
		for (int i = 0; i < root.getChildNodes().size(); i++) { 
			System.out.println("\tchild " + i + ": " + root.getChildNodes().get(i).getStateRepresentation());
		}
//		
//		Tree tree = new Tree(root);
//		
	}

}
