package searches;

import gui.model.Grid;
import heuristics.Heuristic;

public class AStar extends SearchAlgo {
	
	private Heuristic h;
	
	public AStar(Node start, Node goal, Grid grid, Heuristic h) {
		super(start, goal, grid);	
		this.h = h;
	}
	
	@Override
	public float hcostFunc(Node n) {
		return h.getH(n);
	}
}
