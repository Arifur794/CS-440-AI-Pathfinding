package heuristics;

import gui.model.Grid;
import searches.Node;

public class DistanceHeuristic {
	private int ex, ey, sx, sy;
	private float startToGoalDist;
	
	
	@SuppressWarnings("unused")
	private Grid g;
	public DistanceHeuristic(Grid g) {
		this.g = g;
		ex = g.endCell[1];
		ey = g.endCell[0];
		sx = g.startCell[1];
		sy = g.endCell[0];
		
		//the Euclidean distance from start to goal multiplied by 0.25 because in a best case scenario there is a river connecting start and goal
		startToGoalDist = 0.25f * (float) Math.sqrt( ((sx - ex)*(sx- ex)) + ((sy-ey)*(sy-ey)) );
	}
	
	public float getH(Node a) {
		//The Euclidean distance from currNode to goal multiplied by 0.25 because of best case scenario
		//it is then divided by the modified Euclidean Distance from start to goal to see how they compare
		return ( 0.25f * (float) Math.sqrt( ((a.x - ex)*(a.x- ex)) + ((a.y-ey)*(a.y-ey)) ) ) / startToGoalDist;
		
		//same as above but get half of it instead. Not admissable because not a best case scenario for effort required to travel
		//return ( 0.50f * ((float) Math.sqrt( ((a.x - ex)*(a.x- ex)) + ((a.y-ey)*(a.y-ey)) ) ) ) / startToGoalDist; 
		
		//See above
		//return (0.75f * ((float) Math.sqrt( ((a.x - ex)*(a.x- ex)) + ((a.y-ey)*(a.y-ey)) ) ) / startToGoalDist;
		
		//Not admissable because it assumes all travel times are 1.
		//return ((float) Math.sqrt( ((a.x - ex)*(a.x- ex)) + ((a.y-ey)*(a.y-ey)) ) ) / (startToGoalDist/.0.25f);
		
		//Not admissable because it only looks at the distance from currNode to goal without caring about start to goal distance
		//return ((float) Math.sqrt( ((a.x - ex)*(a.x- ex)) + ((a.y-ey)*(a.y-ey)) ) );
	}
}
