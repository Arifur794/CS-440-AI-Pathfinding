package searches;

import gui.model.Grid;
import heuristics.DistanceHeuristic;

public class WAStar extends SearchAlgo {
	private DistanceHeuristic h;
	private float weight;
	
	public WAStar(Node start, Node goal, Grid grid, DistanceHeuristic h, float weight) {
		super(start, goal, grid);	
		this.h = h;
		this.weight = weight;
	}

	public float costFunc(Node parent, Node child) {
		return grid.costTo(parent, child) + weight * h.getH(child);
	}
}
