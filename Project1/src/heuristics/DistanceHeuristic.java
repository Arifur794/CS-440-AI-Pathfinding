package heuristics;

import gui.model.Grid;
import searches.Node;

public class DistanceHeuristic {
	private int bx, by, sx, sy;
	private Grid g;
	public DistanceHeuristic(Grid g) {
		this.g = g;
		bx = g.endCell[1];
		by = g.endCell[0];
		sx = g.startCell[1];
		sy = g.endCell[0];
	}
	
	public float getH(Node a) {
		return 0.1f;
	}
}
