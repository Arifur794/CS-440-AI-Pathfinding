package searches;

import java.util.Objects;

import gui.model.Cell;

public class Node extends Cell {
	
	private Node parent;
	public float gcost, hcost;
	public boolean isExplored;
	public Node[] neighborList;
	public boolean inFringe;
	
	public Node(Cell c) {
		super(c.x, c.y, c.celltype, c.dir);
		this.gcost = 0;
		this.hcost = 0;
		this.isExplored = false;	
		this.inFringe = false;
	}
	
	public Node(Cell c, Cell[] n) {
		this(c);
		setNeighborList(n);
	}

	private void setNeighborList(Cell[] n) {
		int len = n.length;
		this.neighborList = new Node[len];
		for(int i = 0; i < len; i++) {
			this.neighborList[i] = new Node(n[i]);
		}
		
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	@Override
	public boolean equals(Object n2) {
		if(n2 == this) {
			return true;
		}
		if(!(n2 instanceof Node)) {
			return false;
		}
		Node c2 = (Node) n2;
		
		return c2.x == this.x && c2.y== this.y;
	}
	
	public int hashCode() {
		return Objects.hash(this.x, this.y);
	}
	
	public String toString() {
		return super.toString() + " " + this.gcost + " " + this.hcost;
	}
}
