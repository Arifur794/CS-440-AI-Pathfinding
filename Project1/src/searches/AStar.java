package searches;

import gui.model.Grid;
import heuristics.DistanceHeuristic;

public class AStar extends SearchAlgo {
	
	private DistanceHeuristic h;
	
	public AStar(Node start, Node goal, Grid grid, DistanceHeuristic h) {
		super(start, goal, grid);	
		this.h = h;
	}
	
	@Override
	public float gcostFunc(Node parent, Node child) {
		return grid.costTo(parent, child) * h.getH(child);
	}
	
	/*@Override
	public float hcostFunc(Node n) {
		return h.getH(n);
	}*/
}
