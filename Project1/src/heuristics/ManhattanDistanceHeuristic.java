package heuristics;

import gui.model.Grid;
import searches.Node;

public class ManhattanDistanceHeuristic extends Heuristic {
	private int ex, ey;
	
	
	@SuppressWarnings("unused")
	private Grid g;
	public ManhattanDistanceHeuristic(Grid g) {
		super(g);
		ex = g.endCell[1];
		ey = g.endCell[0];
	}
	
	public float getH(Node a) {
		return (float) Math.abs(a.x-ex) + Math.abs(a.y-ey);
	}
}
