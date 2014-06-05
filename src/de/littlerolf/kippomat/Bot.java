package de.littlerolf.kippomat;

public class Bot {
	
	private Tree<Integer> tree = new Tree<Integer>();
	private int winNum;
	
	private int startNum = 1;
	
	public Bot(int winNum, int start) {
		this.winNum = winNum;
		this.startNum = start;
		
		System.out.println("Building tree...");
		tree.setRoot(new Node<Integer>(startNum));
		buildTree(0, tree.getRoot());
		System.out.println("Finished");
	}
	
	public void buildTree(int sum, Node<Integer> node) {
		for(int i = 1; i<=6;i++) {
			if(i != node.getContent() && i != (7-node.getContent())) {
				Node<Integer> n = new Node<Integer>(i);
				node.addChild(n);
				if ((sum + i) < winNum) {
					buildTree(sum+i, n);
				} else {
					System.out.println("Finished branch with sum " + (sum+i));
				}
			}
		}
		
		
		
	}
	
	public static void main(String[] args) {
		new Bot(8, 1);
	}
}
