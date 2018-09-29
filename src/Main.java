import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Puzzle p = new Puzzle();
		System.out.println(p);
		System.out.println(p.isSolved());
		p.moveUpLeft();
		System.out.println(p);
		
//		Node root = new Node(p.getPuzzle());
//		Node firstChild = new Node(
//			root,
//			new int[] {0, 1, 3, 7, 5, 2, 6, 4, 9, 10, 11, 8}			
//		);
//		Node secondChild = new Node(
//			root,
//			new int[] {1, 5, 3, 7, 0, 2, 6, 4, 9, 10, 11, 8}			
//		);
//		Node thirdChild = new Node(
//			root,
//			new int[] {1, 2, 3, 7, 5, 0, 6, 4, 9, 10, 11, 8}			
//		);
//		Node fourthChild = new Node(
//			root,
//			new int[] {1, 6, 3, 7, 5, 2, 0, 4, 9, 10, 11, 8}			
//		);
//		Node fifthChild = new Node(
//			root,
//			new int[] {1, 3, 0, 7, 5, 2, 6, 4, 9, 10, 11, 8}			
//		);
//		
//		root.addChild(firstChild);
//		root.addChild(secondChild);
//		root.addChild(thirdChild);
//		root.addChild(fourthChild);
//		root.addChild(fifthChild);
//		
//		Tree tree = new Tree(root);
//		
	}

}
