package a1;

import java.awt.Color;
import princeton.introcs.StdDraw;

public class SandpileViewer {
	/**
	 * Draws a regular rectangular chess board of the specified size.
	 * 
	 * <p>
	 * Students will need to modify this method to draw irregular boards
	 * if their solutions allows for irregular boards. 
	 * 
	 * @param width the number of squares in the width of the board
	 * @param height the number of squares in the height of the board
	 */
	public static void draw(int[][] cells) {
		int rows = cells.length;
		int cols = cells[0].length;
		
		
		int max = Math.max(rows, cols);
		StdDraw.setScale(0.5, max + 0.5);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				int val = cells[r][c];
				if (val == 0) {
					StdDraw.setPenColor(new Color(18, 72, 249));
				}
				else if (val == 1) {
					StdDraw.setPenColor(new Color(115, 170, 249));
				}
				else if (val == 2) {
					StdDraw.setPenColor(new Color(255, 192, 0));
				}
				else if (val == 3) {
					StdDraw.setPenColor(new Color(124, 0, 0));
				}
				else {
					// unknown cell color
					StdDraw.setPenColor(Color.GRAY);
				}
				StdDraw.filledSquare(c + 1, max - r, 0.5);
			}
			
		}
		
	}
}
