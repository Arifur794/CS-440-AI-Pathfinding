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
				return Float.compare(o1.gcost + o1.hcost, o2.gcost + o2.hcost);
			}
		});
		
		this.allNodes = new Node[grid.getHeight()][grid.getWidth()];
		this.start = start;
		this.goal = goal;
		this.path = null;
		this.grid = grid;
	}
	
	public Node[] run() {
		long startTime = System.currentTimeMillis();
		Node n = start;
		addToFringe(n, null, gcostFunc(start, start), hcostFunc(start));
		int i = 0;
		while(!isFringeEmpty()) {
			n = getNextNode();
			exploreNode(n);
			i++;
			if(n.equals(goal)) {
				System.out.println(i+ " nodes were looked at before goal was found");
				long totalTime = System.currentTimeMillis() - startTime;
				System.out.println(totalTime + " was the run time in milliseconds");
				return unravelPath();
			}
			for(Node nc : n.neighborList) {
				if(nc.celltype == CellType.BLOCKED) {
					continue;
				}
				float childCost = n.gcost + gcostFunc(n, nc);				
				if(!isExplored(nc)) {
					if(!inFringe(nc)) {
						addToFringe(nc, n, childCost, hcostFunc(nc));
					} else {
						Node nco = getNode(nc);
						if(childCost <= nco.gcost) {
							addToFringe(nc, n, childCost, hcostFunc(nc));
						}
					}
					
				}
			}
		}
		System.out.println("No path to the goal was found");
		long totalTime = System.currentTimeMillis() - startTime;
		System.out.println(totalTime  + " was the run time in milliseconds");
		return null;
	}
	
	public Node[] run(int x) {
		long startTime = System.currentTimeMillis();
		Node n = start;
		addToFringe(n, null, gcostFunc(start, start), hcostFunc(start));
		int i = 0;
		while(!isFringeEmpty()) {
			n = getNextNode();
			exploreNode(n);
			i++;
			if(n.equals(goal)) {
				System.out.print(i + ",");//+ " nodes were looked at before goal was found");
				long totalTime = System.currentTimeMillis() - startTime;
				System.out.print(totalTime + ",");//  + " was the run time in milliseconds");
				return unravelPath();
			}
			for(Node nc : n.neighborList) {
				if(nc.celltype == CellType.BLOCKED) {
					continue;
				}
				float childCost = n.gcost + gcostFunc(n, nc);				
				if(!isExplored(nc)) {
					if(!inFringe(nc)) {
						addToFringe(nc, n, childCost, hcostFunc(nc));
					} else {
						Node nco = getNode(nc);
						if(childCost <= nco.gcost) {
							addToFringe(nc, n, childCost, hcostFunc(nc));
						}
					}
					
				}
			}
		}
		System.out.println("No path to the goal was found");
		long totalTime = System.currentTimeMillis() - startTime;
		System.out.println(totalTime  + " was the run time in milliseconds");
		return null;
	}

	public void addToFringe(Node n, Node p, float gcost, float hcost) {		
		n.gcost = gcost;
		n.hcost = hcost;
		n.setParent(p);
		if(inFringe(n)) {
			fringe.remove(n);
		}
		fringe.add(n);
		n.inFringe = true;
		this.allNodes[n.y][n.x] = n;
	}

	public float gcostFunc(Node parent, Node child) {
		return grid.costTo(parent, child);
	}
	
	public float hcostFunc(Node n) {
		return 0f;
	}
	
	public Node[] unravelPath() {
		ArrayList<Node> pathList = new ArrayList<Node>();
		Node n = allNodes[goal.y][goal.x], p;
		
		while(n != null) {
			p = n.getParent();
			pathList.add(n);
			n = p;
		}
		path = new Node[pathList.size()];
		path = pathList.toArray(path);
		return path;
	}
	
	public void exploreNode(Node n) {
		n.setNeighborList(grid.getNeighbors(n.x, n.y));
		n.isExplored = true;
		this.allNodes[n.y][n.x] = n;
	}
	
	public boolean isExplored(Node n) {
		Node isThere = this.allNodes[n.y][n.x];
		return (isThere == null) ? false : isThere.isExplored;
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
		Node isThere = this.allNodes[n.y][n.x];
		return (isThere == null) ? false : isThere.inFringe;
	}
	
	public void removeFromFringe(Node n) {
		fringe.remove(n);
	}
	
	public Node getNode(Node n) {
		return this.allNodes[n.y][n.x];
	}
	
	public float calculateCost() {
		if(path == null) {
			System.out.println("The path is null. There is no path or the algorithm failed to run");
			return -1f;
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
