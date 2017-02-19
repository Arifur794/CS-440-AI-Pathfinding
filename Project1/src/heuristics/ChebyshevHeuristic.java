
package heuristics;

import searches.Node;
import gui.model.Grid;

public class ChebyshevHeuristic extends Heuristic {

	private int bx, by;

	public ChebyshevHeuristic(Grid g) {
		super(g);
		bx = g.endCell[0];
		by = g.endCell[1];
	}
	
	@Override
	public float getH(Node a) {
		float dx = Math.abs(a.x - bx);
		float dy = Math.abs(a.y - by);

		return (float) Math.max(dx, dy);
	}

}