package heuristics;

import gui.model.Grid;
import searches.Node;

public abstract class Heuristic {
	@SuppressWarnings("unused")
	private Grid g;
	public Heuristic(Grid g) {
		this.g = g;
	}
	
	public abstract float getH(Node a);
}
