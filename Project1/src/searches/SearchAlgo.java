package searches;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import gui.model.Cell.CellType;
import gui.model.Grid;

public abstract class SearchAlgo {
	Node[] path; // path found by algorithm
	private PriorityQueue<Node> fringe; // fringe used by algorithm
	private Node[][] allNodes; // all explored nodes are stored here
	Node goal;
	Node start;
	Grid grid;
	
	public SearchAlgo(Node start, Node goal, Grid grid) {
		this.fringe = new PriorityQueue<Node>( new Comparator<Node>() {
			public int compare(Node o1, Node o2) {
				return (int) (( (o1.gcost + o1.hcost) - (o2.gcost + o2.hcost) ));
			}
		});
		
		this.allNodes = new Node[grid.getHeight()][grid.getWidth()];
		this.start = start;
		this.goal = goal;
		this.path = null;
		this.grid = grid;
	}
	
	public Node[] run() {
		System.out.println("------");
		
		float cost = 0;
		Node n = start;
		n.gcost = gcostFunc(start, start);
		n.hcost = hcostFunc(start);
		addToFringe(start, n.gcost, n.hcost);
			
		while(!isFringeEmpty()) {
			n = getNextNode();
			cost = n.gcost;
			exploreNode(n); 
			if(n.equals(goal)) {
				return unravelPath();
			}
			
			for(Node nc : n.neighborList) {
				if(nc.celltype == CellType.BLOCKED) {
					continue;
				}
				float childCost = cost + gcostFunc(n, nc); // g(n) + c(n, nc)
				
				if(!isExplored(nc) && !inFringe(nc)) {
					nc.setParent(n);
					addToFringe(nc, childCost, hcostFunc(nc));
				} else if(inFringe(nc) && getNode(nc).gcost > childCost) {
					removeFromFringe(nc);
					nc.setParent(n);
					addToFringe(nc, childCost, hcostFunc(nc));
					getNode(nc).gcost = childCost;
				}
			}
		}
		return null;
	}
	
	public float gcostFunc(Node parent, Node child) {
		return grid.costTo(parent, child);
	}
	
	public float hcostFunc(Node n) {
		return 0f;
	}
	
	public Node[] unravelPath() {
		ArrayList<Node> pathList = new ArrayList<Node>();
		//pathList.add(goal);
		Node n = allNodes[goal.y - 1][goal.x - 1], p;
		
		int i = 1;
		while(n != null) {
			p = n.getParent();
			pathList.add(n);
			n = p;	
			i++;
		}
		System.out.println(pathList.size() + " " + i);
		path = new Node[pathList.size()];
		path = pathList.toArray(path);
		return path;
	}
	
	public void exploreNode(Node n) {
		n.setNeighborList(grid.getNeighbors(n.x, n.y));
		n.isExplored = true;
		this.allNodes[n.y - 1][n.x - 1] = n;
	}
	
	public boolean isExplored(Node n) {
		Node isThere = this.allNodes[n.y - 1][n.x - 1];
		return (isThere == null) ? false : isThere.isExplored;
	}
	
	public void addToFringe(Node n, float gcost, float hcost) {
		if(!fringe.contains(n)) {
			n.gcost = gcost; n.hcost = hcost;
			fringe.add(n);
			n.inFringe = true;
		}
	}
	
	public Node getNextNode() {	
		return fringe.remove();
	}
	
	public boolean isFringeEmpty() {
		return fringe.isEmpty();
	}
	
	public void printFringe() {
		System.out.println(fringe);
	}
	
	public boolean inFringe(Node n) {
		Node isThere = this.allNodes[n.y - 1][n.x - 1];
		return (isThere == null) ? false : isThere.inFringe;
	}
	
	public void removeFromFringe(Node n) {
		fringe.remove(n);
	}
	
	public Node getNode(Node n) {
		return this.allNodes[n.y - 1][n.x - 1];
	}
	
	public float calculateCost() {
		if(path == null) {
			throw new NullPointerException("The path is null. The algorithm may not have run.");
		}
		
		float cost = 0;
		Node p = path[0];
		for(Node n : path) {
			cost += grid.costTo(n, p);
			p = n;
		}
		return cost;
	}
}
