package polar;

import java.util.List;
import java.util.Random;

import polar.geometry.*;

/**
 * This is a Utility class, intended to handle things like math formulas, 
 * annoying calculations/comparisons, and other things, in order to save time
 * and code later in the actual game creation
 */
public class Util {
	private static final Random rand = new Random();
	/**
	 * The mathematical constant Pi. (equivalent to Math.PI)
	 */
	public static final double PI = Math.PI;
	
	//COMMON METHODS
	/**
	 * Returns whether two doubles are "equal" (within .00001 of each other)
	 * @param a The first double to compare
	 * @param b The second double to compare
	 * @return A boolean 
	 */
	public static boolean equal(double a, double b) {
		return within(a,b,.00001);
	}
	/**
	 * Returns whether two doubles are within a certain range of each other
	 * @param a The first double to compare
	 * @param b The second double to compare
	 * @param t The desired range to compare to
	 * @return Returns true if a and b are within range t from each other
	 */
	public static boolean within(double a, double b,double t) {
		return Math.abs(a-b)<=t;
	}
	/**
	 * Returns the vector from point <i>a</i> to point <i>b</i>
	 * @param a The start point
	 * @param b The end point
	 * @return A Vector needed to travel from a to b
	 */
	public static Vector getVector(Point a, Point b) {
		return new Vector(b.getX()-a.getX(),b.getY()-a.getY());
	}
	/**
	 * Returns the distance between two points
	 * @param a The first point
	 * @param b The second point
	 * @return The distance (as a double)
	 */
	public static double dist(Point a, Point b) {
		return sqrt(pow(b.getX()-a.getX(),2)+pow(b.getY()-a.getY(),2));
	}
	//INPUT ASSISTANCE
	
	//VECTOR MATH :)
	public static Vector vAdd(Vector... vectors) {
		Vector ans = new Vector(0,0);
		for(Vector v : vectors) {
			ans.add(v);
		}
		return ans;
	}
	public static Vector vSub(Vector a, Vector b) {
		return new Vector(a.getX()-b.getX(),a.getY()-b.getY());
	}
	public static Vector vMult(Vector a, double d) {
		return new Vector(a.getX()*d,a.getY()*d);
	}
	public static Vector vDiv(Vector a, double d) {
		return new Vector(a.getX()/d,a.getY()/d);
	}
	public static Double vMag(Vector v) {
		return sqrt(pow(v.getX(),2)+pow(v.getY(),2));
	}
	//POINT MATH
	public static Point pAvg(Point... points) {
		Point avg=new Point(0,0);
		for(Point p : points) {
			avg = new Point(avg.getX()+p.getX(),avg.getY()+p.getY());
		}
		avg = new Point(avg.getX()/points.length,avg.getY()/points.length);
		return avg;
	}
	public static Point pAvg(List<Point> points) {
		Point avg=new Point(0,0);
		for(Point p : points) {
			avg = new Point(avg.getX()+p.getX(),avg.getY()+p.getY());
		}
		avg = new Point(avg.getX()/points.size(),avg.getY()/points.size());
		return avg;
	}
	
	//REWRITING MATH FOR EASIER USE
	public static double round(double d) {return Math.round(d);}
	public static double abs(double d) {return Math.abs(d);}
	public static int abs(int i) {return Math.abs(i);}
	public static double max(double a, double b) {return Math.max(a,b);}
	public static int max(int a, int b) {return Math.max(a,b);}
	public static double min(double a, double b) {return Math.min(a,b);}
	public static int min(int a, int b) {return Math.min(a,b);}
	public static double sqrt(double d) {return Math.sqrt(d);}
	public static double root(double b, double e) {return Math.pow(b,1/e);}
	public static double pow(double b,double e) {return Math.pow(b,e);}
	public static double sin(double d) {return Math.sin(d);}
	public static double cos(double d) {return Math.cos(d);}
	public static double tan(double d) {return Math.tan(d);}
	public static double asin(double d) {return Math.asin(d);}
	public static double acos(double d) {return Math.acos(d);}
	public static double atan(double d) {return Math.atan(d);}
	public static double atan(double x, double y) {return Math.atan2(x,y);}
	//CONVERT ANGLES (CURRENT SYSTEM WORKS IN RADS)
	/**
	 * Converts a given angle in degrees to radians
	 * @param deg The given angle in degrees
	 * @return The given angle in radians
	 */
	public static double rad(double deg) {
		return deg*PI/180;
	}
	/**
	 * Converts a given angle in radians to degrees
	 * @param rad The given angle in radians
	 * @return The given angle in degrees
	 */
	public static double deg(double rad) {
		return rad*180/PI;
	}
	
	//GAME ASSISTANCE (player pos, etc?)
	
	//RANDOM NUMBERS
	public static int randInt(int n) {
		return rand.nextInt(n);
	}
	public static double rand() {
		return rand.nextDouble();
	}
	public static boolean randBool() {
		return rand.nextBoolean();
	}
}
