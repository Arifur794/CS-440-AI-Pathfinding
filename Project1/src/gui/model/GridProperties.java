package gui.model;

public class GridProperties {

	final int NUM_HARD_CELL_CTR = 8; // the amount of center for generating hard cells
	final int HARD_CELL_REGION = 31; // number of cells around the center to generate in both the width and the height
	final float HARD_CELL_PRB = 0.5f; //  the chance a cell in a hard cell region is hard
	
	final int NUM_HIGHWAY_CELLS = 20; // number of cells the highway has before turning
	final float HIGHWAY_SAME_DIR_PRB = 0.6f; // probability of highway staying straight 
	final float HIGHWAY_CHG_DIR1_PRB = 0.2f; // probability of highway changing direction to the left or up from its current.
	final float HIGHWAY_CHG_DIR2_PRB = 0.2f; // // probability of highway changing direction to right or down from its current
	final int MIN_HIGHWAY_LEN = 100; // minimum total length of highway
	final int NUM_HIGHWAYS = 4; // number of highways for the grid to generate
	
	final float BLOCK_CELL_PER = 0.2f; // Percentage of total grid cells to make blocked
	
	final int POINT_AREA = 20; // the area which start and end node can be from edges 
	final int MIN_DIST = 100; // minimum distance between start node and the end node
	
	final float easyDiag = (float) Math.sqrt(2.0); //the amount of travel on the diagonal for two easy cells
	final float easyHardDiag = ( (float) Math.sqrt(2.0) + (float) Math.sqrt(8.0) )/2; //the amount of travel on the diagonal for one easy and one hard cell
	final float hardDiag = (float) Math.sqrt(8.0); //the amount of travel on the diagonal for two hard cells
	
	//the arrays below are for how difficult it is to travel from cell a to cell b.
	//Column 0 is easy, column 1 is hard, column 2 is blocked, column 4 is easy highway, and column 5 is hard highway
	//the rows are the same
	//Row is cell A and column is cell B. Since both have a type Grid's To Cost is able to use those to look here and see what the cost should be
	
	final float[][] HOR_VER_COSTS = {
			{1,    1.5f,  -1, 1f,     1.50f},
			{1.5f, 2,     -1, 1.5f,   2f},
			{-1,   -1,    -1, -1,     -1},
			{1f,   1.50f, -1, 0.25f,  0.375f},
			{1.5f, 2f,    -1, 0.375f, 0.5f}
	};
	
	final float[][] DIAG_COSTS = {
			{easyDiag,     easyHardDiag,    -1, easyDiag,          easyHardDiag},
			{easyHardDiag, hardDiag, 	    -1, easyHardDiag,      hardDiag},
			{-1,           -1, 			    -1, -1, 			   -1},
			{easyDiag,     easyHardDiag, 	-1, easyDiag/4.0f,     easyHardDiag/4.0f},
			{easyHardDiag, hardDiag, 	    -1, easyHardDiag/4.0f, hardDiag/4.0f}
	};
}
