package de.littlerolf.kippomat;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
	
	private Node<T> parent;
	
	private T content;
	
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
}
