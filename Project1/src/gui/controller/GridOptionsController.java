package gui.controller;

import java.io.File;

import files.GridFile;
import gui.model.Grid;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GridOptionsController {

	@FXML
	private Button importMap, exportMap;
	
	private GridController gridCtrl;
	private FileChooser fileChoose = new FileChooser();
	private Stage stage;
	
	@FXML
	public void initialize() {
		importMap.setOnMouseClicked(e -> {
			File file = fileChoose.showOpenDialog(stage);
			if(file != null) {
				Grid grid = GridFile.importFile(file.getAbsolutePath(), gridCtrl.ROWS, gridCtrl.COLS);
				gridCtrl.changeGrid(grid);
			}
		});
		
		exportMap.setOnMouseClicked(e -> {
			Grid grid = gridCtrl.getGrid();
			File file = fileChoose.showSaveDialog(stage);
			if(file != null) {
				GridFile.exportFile(grid, file);
			}
		});
	}
	
	public void setGridController(GridController gc) {
		this.gridCtrl = gc;
	}
	
	public void setStage(Stage s) {
		this.stage = s;
	}
}
