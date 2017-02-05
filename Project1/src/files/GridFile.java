package files;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import gui.model.Grid;
import gui.model.Cell;
import gui.model.Cell.CellType;

public class GridFile {

	public static void exportFile(Grid grid, File file) {
		try{
		    PrintWriter writer = new PrintWriter(file, "UTF-8");
		    writer.println(grid.startCell[0] + "," + grid.startCell[1]);
		    writer.println(grid.endCell[0] + "," + grid.endCell[1]);
		    
		    for(int[] hardCtr : grid.hardCellsCenters) {
		    	writer.println(hardCtr[0] + "," + hardCtr[1]);
		    }
		    
		    for(Cell[] cArr : grid.getGrid()) {
		    	for(Cell c : cArr) {
		    		switch(c.celltype) {
					case BLOCKED:
						writer.print("0");
						break;
					case HARD:
						writer.print("2");
						break;
					case HIGHWAY_HARD:
						writer.print("b");
						break;
					case HIGHWAY_UNBLOCKED:
						writer.print("a");
						break;
					case UNBLOCKED:
						writer.print("1");
						break;
					default:
						break;
		    		
		    		}	
		    	}
		    	writer.println();
		    }
		    
		    writer.close();
		} catch (IOException e) {
		   // do something
		}
	}
	
	private static int linesRead = 0;
	
	public static Grid importFile(String pathName, int rows, int cols) {	
		Grid grid = new Grid(cols, rows);
		
		// number of lines to read before reading the matrix cell
		int numNonCellMatrix = grid.hardCellsCenters.length + 2; 
		
		try(Stream<String> stream = Files.lines(Paths.get(pathName))) {
			
			stream.forEach(s -> {
				if(++linesRead <= numNonCellMatrix) {
					String[] sarr = s.split(",");
					int a = Integer.parseInt(sarr[0]), b = Integer.parseInt(sarr[1]);
					
					if(linesRead == 1) { // read start cell
						grid.startCell[0] = a; grid.startCell[1] = b;
					} else if(linesRead == 2) { // read end cell
						grid.endCell[0] = a; grid.endCell[1] = b;
					} else { // read hard cells centers
						grid.hardCellsCenters[linesRead - 3][0] = a;
						grid.hardCellsCenters[linesRead - 3][1] = b;
					}
				} else {
					char[] carr = s.toCharArray();
					for(int i = 1; i <= carr.length; i++) {						
						int col = linesRead - numNonCellMatrix;
						Cell c = grid.getCell(i, col);
						switch(carr[i - 1]) {
						case '0':
							c.convertTo(CellType.BLOCKED);
							break;
						case '1':
							c.convertTo(CellType.UNBLOCKED);
							break;
						case '2':
							c.convertTo(CellType.HARD);
							break;
						case 'a':
							c.convertTo(CellType.HIGHWAY_UNBLOCKED);
							break;
						case 'b':
							c.convertTo(CellType.HIGHWAY_HARD);
							break;
						default:
							break;
						}
					}
				}
			});
			linesRead = 0;
			
			Cell startCell = grid.getCell(grid.startCell[0], grid.startCell[1]);
			Cell endCell = grid.getCell(grid.endCell[0], grid.endCell[1]);
			
			if(startCell.celltype == CellType.UNBLOCKED) {
				startCell.convertTo(CellType.START_POINT_UNBLOCKED);
			} else {
				startCell.convertTo(CellType.START_POINT_HARD);
			}
			
			if(endCell.celltype == CellType.UNBLOCKED) {
				endCell.convertTo(CellType.END_POINT_UNBLOCKED);
			} else {
				endCell.convertTo(CellType.END_POINT_HARD);
			}
		} catch(IOException e) {
			linesRead = 0;
			e.printStackTrace();
		}
		
		return grid;
	}
	
}
