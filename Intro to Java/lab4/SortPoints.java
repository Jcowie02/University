import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import ca.queensu.cs.cisc124.notes.basics.geometry.Point2;

public class SortPoints {

	public static class CompareByX implements Comparator<Point2> {

		@Override
		public int compare(Point2 o1, Point2 o2) {
			Double d1 = o1.x();
			Double d2 = o2.x();
			return Double.compare(d1, d2);
		}
	}

	public static void main(String[] args) {
		Random rng = new Random();
		// list of 8 random points
		List<Point2> t = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			t.add(new Point2(rng.nextDouble() * 20.0 - 10.0, rng.nextDouble() * 20.0 - 10.0));
		}
		t.add(new Point2(-0.0, 0.0));
		t.add(new Point2(0.0, 0.0));
		// sort the list using CompareByX
		Comparator<Point2> c = new CompareByX();
		t.sort(c);
		for (Point2 p : t) {
			System.out.println(p);
		}
		
	}
}
