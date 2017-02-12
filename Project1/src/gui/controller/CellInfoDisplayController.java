package gui.controller;

import java.text.DecimalFormat;

import gui.model.Cell;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class CellInfoDisplayController {
	@FXML
	public Label coordinates;
	@FXML
	public Label cellType, bot, botRight, botLeft, left, right, topLeft, top, topRight;
	
	
	public CellInfoDisplayController(Cell c) {
		this.showInfo(c, -1f, -1f, -1f, -1f, -1f, -1f, -1f, -1f);
	}
	
	public CellInfoDisplayController() { }
	
	@FXML
	public void initialize() {}
	
	public void showInfo(Cell c, float topLeft, float top, float topRight, float left, float right, float downLeft, float down, float downRight) {
		coordinates.setText(c.y + ", " + c.x);
		cellType.setText(""+c.celltype);
		this.top.setText(new DecimalFormat("##.##").format(top));
		this.topRight.setText(new DecimalFormat("##.##").format(topRight));
		this.topLeft.setText(new DecimalFormat("##.##").format(topLeft));
		this.right.setText(new DecimalFormat("##.##").format(right));
		this.left.setText(new DecimalFormat("##.##").format(left));
		this.botLeft.setText(new DecimalFormat("##.##").format(downLeft));
		this.botRight.setText(new DecimalFormat("##.##").format(downRight));
		this.bot.setText(new DecimalFormat("##.##").format(down));
		
	}
}
