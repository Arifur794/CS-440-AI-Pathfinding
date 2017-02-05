package gui.controller;

import gui.model.Cell;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class CellDisplayController {
	@FXML
	public Label coordinates;
	@FXML
	public Label cellType;
	
	
	public CellDisplayController(Cell c) {
		this.showInfo(c);
	}
	
	public CellDisplayController() { }
	
	@FXML
	public void initialize() {}
	
	public void showInfo(Cell c) {
		coordinates.setText(c.y + ", " + c.x);
		cellType.setText(""+c.celltype);
	}
}
