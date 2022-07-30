package a1;

public class Sandpile {

	/**
	 * Print a 2-dimensional array of cells using least 3 spaces for each value.
	 * Values for each row of the array appear on a single line, and each row
	 * appears on its own line.
	 * 
	 * @param cells a two-dimensional array
	 * @throws IllegalArgumentException if the specified array has a dimension equal
	 *                                  to zero
	 */
	public static void printCells(int[][] cells) {
		int rows = cells.length;
		if (rows <= 0) {
			throw new IllegalArgumentException("rows <= 0");
		}

		int cols = cells[0].length;
		if (cols <= 0) {
			throw new IllegalArgumentException("cols <= 0");
		}
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				int val = cells[r][c];
				System.out.printf("%3d", val);
			}
			System.out.println();
		}
	}

	// TRANSLATE THE REMAINING C FUNCTIONS INTO JAVA METHODS HERE
	
	public static void init(int grains, int[][]cells) {
		
		int rows = cells.length;
		if (rows <= 0) {
			throw new IllegalArgumentException("rows <= 0");
		}

		int cols = cells[0].length;
		if (cols <= 0) {
			throw new IllegalArgumentException("cols <= 0");
		}
		
		if (grains <= 0) {
			throw new IllegalArgumentException("grains <= 0");
		}
		
		for (int r = 0; r < rows; r++) {
	        for (int c = 0; c < cols; c++) {
	            cells[r][c] = 0;
	        }
	    }
	    cells[rows / 2][cols / 2] = grains;
	}
	
	
	public static Index2 first_to_topple(int cells[][]) {
		
		int rows = cells.length;
		if (rows <= 0) {
			throw new IllegalArgumentException("rows <= 0");
		}

		int cols = cells[0].length;
		if (cols <= 0) {
			throw new IllegalArgumentException("cols <= 0");
		}
		
		Index2 idx = new Index2(); // constructor automatically sets rows = 0 and cols = 0
		
		for (int r = 0; r < rows; r++) {
	        idx.row = r;
	        for (int c = 0; c < cols; c++) {
	            idx.col = c;
	            int val = cells[r][c];
	            if (val >= 4) {
	                return idx;
	            }
	        }
	    }
	    idx.row = -1;
	    idx.col = -1;
	    return idx;
	}
	
	public static void topple(Index2 i, int cells[][]) {
	
		int rows = cells.length;
		if (rows <= 0) {
			throw new IllegalArgumentException("rows <= 0");
		}

		int cols = cells[0].length;
		if (cols <= 0) {
			throw new IllegalArgumentException("cols <= 0");
		}
		
	    int grains = cells[i.row][i.col];
		
		if (grains < 4) {
			throw new IllegalArgumentException("grains < 4");
		}
		
	    cells[i.row][i.col] -= 4;
	    
	    // move one grain north (if possible)
	    if (i.row > 0) {
	        cells[i.row - 1][i.col]++;
	    }
	    // move one grain east right (if possible)
	    if (i.col < cols - 1) {
	        cells[i.row][i.col + 1]++;
	    }
	    // move one grain south (if possible)
	    if (i.row < rows - 1) {
	        cells[i.row + 1][i.col]++;
	    }
	    // move one grain west (if possible)
	    if (i.col > 0) {
	        cells[i.row][i.col - 1]++;
	    }
	}
	
	
	public static int degree(Index2 i, int[][]cells) {
		
		int rows = cells.length;
		if (rows <= 0) {
			throw new IllegalArgumentException("rows <= 0");
		}

		int cols = cells[0].length;
		if (cols <= 0) {
			throw new IllegalArgumentException("cols <= 0");
		}
		
		if (i.row >= rows) {
			throw new IllegalArgumentException("Invalid row");
		}
		if (i.col >= cols) {
			throw new IllegalArgumentException("Invalid column");
		}
		
		// first condition: need to be on one of the outer edges
		// second condition: need to be in one of the 4 corners
		if ((i.row-1 < 0 || i.row+1 >= rows) || (i.col-1 < 0 || i.col+1 >= cols)) {
			if ((i.row == 0 && i.col == 0) || (i.row == 0 && i.col == 14) || 
					(i.row == 14 && i.col == 0) || (i.row == 14 && i.col == 14)) {
				return 2;
			}
			else return 3;
		}
		else return 4;
	}
	
	/**
	 * Creates a 15x15 sandpile simulation starting with 2 to-the-power 8 grains of
	 * sand on the center cell. The starting configuration of cells is printed to
	 * standard output and then the simulation is run until all cells reach a stable
	 * state (have fewer than 4 grains of sand). The stable configuration of cells
	 * is printed to standard output.
	 * 
	 * <p>
	 * Finally, an image of the stable configuration is shown.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		int[][] cells = new int[15][15];
		// FINISH TRANSLATING THE main FUNCTION HERE
		
		final int ROWS = 15;
		final int COLS = ROWS;
		init((int) Math.pow(2,8), cells);
		System.out.println("Original cells");
		printCells(cells);
		Index2 i = new Index2();
		i = first_to_topple(cells);
		while (i.row >= 0) {
			topple(i, cells);
	        i = first_to_topple(cells);
	    }
	    System.out.println("\nFinal cells");
	    printCells(cells);
		SandpileViewer.draw(cells);
		}
	}
