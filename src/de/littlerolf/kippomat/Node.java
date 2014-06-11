package de.littlerolf.kippomat;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {

	private Node<T> parent;

	private T content;
	private int rating;
	private boolean myTurn;

	private List<Node<T>> children = new ArrayList<Node<T>>();

	public Node(T content) {
		this.content = content;
	}

	public Node(T content, Node<T> par) {
		this.content = content;
		this.parent = par;
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public List<Node<T>> getChildren() {
		return children;
	}

	public void setChildren(List<Node<T>> children) {
		this.children = children;
	}

	public void addChild(Node<T> c) {
		children.add(c);
		c.setParent(this);
	}

	public void print() {
		print("-");
	}

	public void print(String prefix) {
		System.out.println(prefix + content.toString() + " (" + rating + ","+ myTurn + ")");
		for(Node<T> c : children) {
			c.print(prefix + "-");
		}
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public boolean isMyTurn() {
		return myTurn;
	}

	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}
}
