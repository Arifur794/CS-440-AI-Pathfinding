package gui.model;

public class GridProperties {

	final int NUM_HARD_CELL_CTR = 8; // number of centers of hard cells used in grid
	final int HARD_CELL_REGION = 31; // number of cells in width and height from center cell to generate hard cells
	final float HARD_CELL_PRB = 0.5f; // probability of cells to become hard cells in the region.
	
	final int NUM_HIGHWAY_CELLS = 20; // number of cells the highway that is laid each iteration
	final float HIGHWAY_SAME_DIR_PRB = 0.6f; // after NUM_HIGHWAY_CELLS probability of staying same direction of highway
	final float HIGHWAY_CHG_DIR1_PRB = 0.2f; // after NUM_HIGHWAY_CELLS probability of changing direction of highway to one perpendicular way(left or up)
	final float HIGHWAY_CHG_DIR2_PRB = 0.2f; // after NUM_HIGHWAY_CELLS probability of changing direction of highway to one perpendicular way(down or right)
	final int MIN_HIGHWAY_LEN = 100; // minimum length of length
	final int NUM_HIGHWAYS = 4; // number of highways in grid
	
	final float BLOCK_CELL_PER = 0.2f; // percentage of cells in grid to be blocked
	
	final int POINT_AREA = 20; // square area denoting number of rows and columns to select start point and end point 
	final int MIN_DIST = 100; // minimum distance between start and end areas
	
	/*
	 * Our implementation for costs will be as follows:
	 * 1. Horizontal/Vertical and Diagonal movement will have two 5x5 arrays
	 * 2. The structure of array:
	 * 				UNBLOCKED   HARD   BLOCKED   HIGHWAY_UNBL   HIGHWAY_BLCK
	 * UNBLOCKED
	 * HARD
	 * BLOCKED
	 * HIGHWAY_UNBL
	 * HIGHWAY_BLCK
	 * 3. The row will correspond to cell a and column will be cell b. 
	 * Hence, the costs are from cell a to cell b
	 * 
	 */
	
	final float[][] HOR_VER_COSTS = {
			{1, 1.5f, -1, 0.625f, 0.875f},
			{1.5f, 2, -1, 0.875f, 1.25f},
			{-1, -1, -1, -1, -1},
			{0.625f, 0.875f, -1, 0.25f, 0.375f},
			{0.875f, 1.25f, -1, 0.375f, 0.5f}
	};
	
	final float[][] DIAG_COSTS = {
			{1.414f, 2.121f, -1, 0.8838f, 1.326f},
			{2.121f, 2.828f, -1, 1.326f, 1.7675f},
			{-1, -1, -1, -1, -1},
			{0.8838f, 1.326f, -1, 0.3535f, 0.5303f},
			{1.326f, 1.7675f, -1, 0.5303f, 0.707f}
	};
}