package de.littlerolf.kippomat;

public class MinMaxBot extends Bot {

	private Node<Integer> curr;

	public MinMaxBot(String s) {

	}

	@Override
	public void initialize(int winSum, int startNum, boolean isFirst) {
		this.winNum = winSum;
		System.out.println(winSum);
		this.startNum = startNum;

		System.out.println("Building tree...");
		tree.setRoot(new Node<Integer>(startNum));
		buildTree(0, tree.getRoot());

		System.out.println("Finished");

		minMax(tree.getRoot(), -1, !isFirst);

		tree.printTree();
		curr = tree.getRoot();

	}

	private int minMax(Node<Integer> n, int curScore, boolean isOwn) {
		n.setMyTurn(isOwn);
		curScore += n.getContent();
		if (n.getChildren().isEmpty()) { // Game ended here
			if (curScore == this.winNum) {
				int z = (isOwn) ? 1 : -1;
				n.setRating(z); // Game ended with one of them winning
				return z;
			} else {
				n.setRating(0); // Tie
				return 0;
			}

		}
		int score = -1; // By default player lost
		for (Node<Integer> node : n.getChildren()) {
			int s = minMax(node, curScore, !isOwn);
			if (isOwn) {
				if (s == -1) {
					score = -1;
					break; // Ausschluss des aktuellen Knotens
				}

			}
			if (s > score)
				score = s;

		}
		n.setRating(score);
		return score;
	}

	@Override
	public int makeTurn(int sum, int prevNum) {
		log("My turn: s = " + sum + " ; p = " + prevNum);
		// After opponents turn, update the current tree node, so that curr
		// equals the move of the opponent
		if (sum == 0) {
			log("Setting curr to root");
			curr = tree.getRoot();
		} else {
			log("Setting curr");
			for (Node<Integer> n : curr.getChildren()) {
				if (n.getContent() == prevNum) {
					log("Set it to " +n.getContent());
					curr = n;
					break;
				}
			}
		}

		System.out.println("-----------------------");
		curr.print();
		System.out.println("-----------------------");
		// find best move
		// win the game
		for (Node<Integer> n : curr.getChildren()) {
			if ((n.getContent() + sum) == winNum) {
				log("win!");
				curr = n;
				return n.getContent();
			}
		}
		
		for (Node<Integer> n : curr.getChildren()) {
			if (n.getRating() == 1) {
				log("win!");
				curr = n;
				return n.getContent();
			}
		}

		// if no win, try tie
		for (Node<Integer> n : curr.getChildren()) {
			if (n.getRating() == 0) {
				log("tie");
				curr = n;
				return n.getContent();
			}
		}

		// or else loose :(

		for (Node<Integer> n : curr.getChildren()) {
			if (n.getRating() == -1) {
				log("loose");
				curr = n;
				return n.getContent();
			}
		}
		// nope... never be here
		System.out.println("Something went wrong");
		return 1;
	}
}
