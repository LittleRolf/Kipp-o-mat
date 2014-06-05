package de.littlerolf.kippomat;

public class Bot implements Player {

	private Tree<Integer> tree = new Tree<Integer>();
	private int winNum;

	private int startNum = 1;
	private String name = "Bot";

	public Bot(String name) {
		this.name = name;
		/*System.out.println("Building tree...");
		tree.setRoot(new Node<Integer>(startNum));
		buildTree(0, tree.getRoot());
		System.out.println("Finished");*/
	}
	public Bot() {
		
	}
	
	private void log(String msg) {
		System.out.println("[" + name + "] " + msg);
	}

	public void buildTree(int sum, Node<Integer> node) {
		for (int i = 1; i <= 6; i++) {
			if (i != node.getContent() && i != (7 - node.getContent())) {
				Node<Integer> n = new Node<Integer>(i);
				node.addChild(n);
				if ((sum + i) < winNum) {
					buildTree(sum + i, n);
				} else {
					//System.out.println("   Finished branch with sum " + (sum + i));
					return;
				}
			}
		}
	}

	public void buildTree(int sum, Node<Integer> node, int depth) {
		if (depth == 0)
			return;
		for (int i = 1; i <= 6; i++) {
			if (i != node.getContent() && i != (7 - node.getContent())) {
				Node<Integer> n = new Node<Integer>(i);
				node.addChild(n);
				if ((sum + i) < winNum) {
					buildTree(sum + i, n, depth - 1);
				} else {
					//System.out.println("   Finished branch with sum " + (sum + i));
					return;
				}
			}
		}
	}

	@Override
	public int makeTurn(int sum, int prevNum) {
		Node<Integer> n = new Node<Integer>(prevNum);
		buildTree(sum, n, 2); //Berechne sowohl die eigenen Möglichkeiten als auch die des gegners beim nächsten zug
		
		log("Find own win");
		for(Node<Integer> c : n.getChildren()) {
			if (sum + c.getContent() == winNum) {
				return c.getContent();
			}
		}
		log("Avoid opponent win");
		for(Node<Integer> c : n.getChildren()) {
			for(Node<Integer> cc : c.getChildren()) {
				if (sum + cc.getContent() < winNum) {
					return cc.getContent();
				}
			}
		}
		log("Choose tie");
		for(Node<Integer> c : n.getChildren()) {
			for(Node<Integer> cc : c.getChildren()) {
				if (sum + cc.getContent() > winNum) {
					return cc.getContent();
				}
			}
		}
		//Fuck, he won... let's try cheating :D
		return 0;
	}

	@Override
	public void initialize(int winSum,int startSum, boolean isFirst) {
		this.winNum = winSum;
		this.startNum = startSum;
	}
}
