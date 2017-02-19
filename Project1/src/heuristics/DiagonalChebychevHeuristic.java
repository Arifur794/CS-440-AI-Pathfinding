
package heuristics;

import searches.Node;
import gui.model.Grid;

public class DiagonalChebychevHeuristic extends Heuristic {

	private int bx, by;

	public DiagonalChebychevHeuristic(Grid g) {
		super(g);
		bx = g.endCell[0];
		by = g.endCell[1];
	}
	
	@Override
	public float getH(Node a) {
		float dx = Math.abs(a.x - bx);
		float dy = Math.abs(a.y - by);

		return (float) (0.25 * (dx + dy) + (0.3535 - 2*0.25) * Math.min(dx, dy));
	}

}