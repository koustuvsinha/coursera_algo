import java.util.Arrays;

public class Brute {

	public static void main(String args[]) {

		String fileName = args[0];
		In input = new In(fileName);

		// rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger
		
		if (input.hasNextLine()) {
			int times = input.readInt();
			Point[] points = new Point[times];
			int i = 0;
			while (input.hasNextLine()) {
				int x = 0, y = 0;
				if (!input.isEmpty()) {
					x = input.readInt();
					y = input.readInt();
					points[i] = new Point(x, y);
					points[i].draw();
					i++;
				} else {
					break;
				}

			}
			
			Arrays.sort(points);

			for (int j = 0; j < times; j++) {
				for (int k = j + 1; k < times; k++) {
					for (int l = k + 1; l < times; l++) {
						for (int m = l + 1; m < times; m++) {
							double slope1 = points[j].slopeTo(points[k]);
							double slope2 = points[j].slopeTo(points[l]);
							double slope3 = points[j].slopeTo(points[m]);
							if (Double.compare(slope1, slope2) == 0
									&& Double.compare(slope2, slope3) == 0) {
								System.out.println(points[j] + " -> "
										+ points[k] + " -> " + points[l]
										+ " -> " + points[m]);
								points[j].drawTo(points[k]);
								points[j].drawTo(points[l]);
								points[j].drawTo(points[m]);
							}
						}
					}
				}

			}

		}
		
		StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();

	}

}
