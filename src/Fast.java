import java.util.Arrays;


public class Fast {

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

			for(int j=0;j<times;j++) {
				Arrays.sort(points,0,j,points[j].SLOPE_ORDER);
				Arrays.sort(points,j+1,times,points[j].SLOPE_ORDER);
				
				for(int k=0; k<times - 3; k++) {
					if(k!=j) {
						double slope1 = points[j].slopeTo(points[k]);
						double slope2 = points[j].slopeTo(points[k+1]);
						double slope3 = points[j].slopeTo(points[k+2]);
						
						if((slope1 == slope2) && (slope2 == slope3)) {
							System.out.println(points[j] + " -> "
									+ points[k] + " -> " + points[k+1]
									+ " -> " + points[k+2]);
							points[j].drawTo(points[k]);
							points[j].drawTo(points[k+1]);
							points[j].drawTo(points[k+2]);
							
							break;
						}
					}
				}
				/*System.out.println("P : " + points[j]);
				for(int k=0;k<times;k++) {
					if(k!=j)
		    		System.out.println(points[k].toString());
		    	}*/
			}

		}
		StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
	}
}
