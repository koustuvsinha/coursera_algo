/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Arrays;
import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {

		@Override
		public int compare( Point o1,  Point o2) {
			
			if(o1==null || o2 == null) {
				throw new NullPointerException();
			}
			
			Double slope1 = slopeTo(o1);
			Double slope2 = slopeTo(o2);
			
			return slope1.compareTo(slope2);
		}
    	
    };       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point( int x,  int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo( Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo( Point that) {
        /* YOUR CODE HERE */
    	Double m;
    	
    	if((that.y != this.y) && (that.x == this.x)) {
    		return Double.POSITIVE_INFINITY;
    	}
    	else if ((that.y == this.y) && (that.x == this.x)) {
    		return Double.NEGATIVE_INFINITY;
    	}
    	else {
    		m = ((double) (that.y - this.y)/(that.x - this.x));
    		if(m == -0.0) {
				m = 0.0;
			}
    		return m;
    	}
    	
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo( Point that) {
        /* YOUR CODE HERE */
    	
    	if (that == null) {
    		throw new NullPointerException();
    	}
    	
    	if(this.y<that.y) {
    		return -1;
    	}
    	else if(this.y == that.y) {
    		if(this.x < that.x) {
    			return -1;
    		}
    		else if(this.x > that.x) {
    			return 1;
    		} else {
    			return 0;
    		}
    	}
    	else {
    		return 1;
    	}
    	
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main( String[] args) {
    	
    	Point[] points = new Point[2];
    	points[0] = new Point(339,328);
    	points[1] = new Point(339,328);
    	
    	System.out.println("Starting point " + points[0]);
    	
    	for (int i = 1; i < points.length; i++) {
    		System.out.println("Slope to " + points[i]);
    		System.out.println(points[0].slopeTo(points[i]));
    	}
    	
    }
   
}
