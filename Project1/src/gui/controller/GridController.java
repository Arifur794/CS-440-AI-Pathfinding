package gui.controller;

import gui.model.Cell;
import gui.model.Grid;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GridController {
	@FXML 
	private GridPane gridPane;
	private Grid grid;
	private CellDisplayController cellDisplay;
	
	final double BUTTON_PADDING = 2;
	final int ROWS = 120; 
	final int COLS = 160;
	final int WIDTH = 1400;
	final int HEIGHT = 780;
	final int CELL_SIZE = 10;
	
	public GridController() {
		this.grid = new Grid(COLS, ROWS);
		//this.cellDisplay = new CellDisplayController();
	}
	
	@FXML
	private void initialize() {
		initGridGui(gridPane);
	}
	
	public void setCellDisplayController(CellDisplayController cdc) {
		this.cellDisplay = cdc;
	}
	
	public void initGridGui(GridPane gridPane) {
		gridPane.setPadding(new Insets(BUTTON_PADDING));
	    gridPane.setHgap(BUTTON_PADDING);
	    gridPane.setVgap(BUTTON_PADDING);

	    this.gridPane = gridPane;
		grid.createGrid();
		colorGrid();
	}
	
	private void addClick(Rectangle rect, int c, int r) {
		rect.setOnMouseClicked(e -> {
			Cell cell = grid.getCell(c, r);
			this.cellDisplay.showInfo(cell);
    	});
	}
	
	public Grid getGrid() {
		return this.grid;
	}
	
	private void colorGrid() {
		//System.out.println("Here");
		gridPane.getChildren().clear();
		
		Rectangle rect;
	    Cell cell;
	    Color color;
	    
	    for (int r = 1; r <= ROWS; r++) {
	        for (int c = 1; c <= COLS; c++) {

	            	cell = grid.getCell(c, r);
	            	switch(cell.celltype) {
		            	case UNBLOCKED:
		            		color = Color.WHITE;
		            		break;
		            	case HARD:
		            		color = Color.GREEN;
		            		break;
		            	case HIGHWAY_UNBLOCKED:
		            		color = Color.BLUE;
		            		break;
		            	case HIGHWAY_HARD:
		            		color = Color.PURPLE;
		            		break;
		            	case BLOCKED:
		            		color = Color.BLACK;
		            		break;
		            	case START_POINT_UNBLOCKED:
		            	case START_POINT_HARD:
		            		color = Color.ORANGE;
		            		break;
		            	case END_POINT_UNBLOCKED:
		            	case END_POINT_HARD:
		            		color = Color.RED;
		            		break;
		            	default:
		            		color = Color.WHITE;
	            	}
	            	rect = new Rectangle(CELL_SIZE, CELL_SIZE, color);
	            	addClick(rect, c, r);
	            	gridPane.add(rect, c, r);           
	        }
	    }
	}
	
	public void changeGrid(Grid grid) {
		this.grid = grid;
		colorGrid();
	}
}