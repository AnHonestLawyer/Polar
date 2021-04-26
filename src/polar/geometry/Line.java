package polar.geometry;

import static org.lwjgl.opengl.GL11.*;
import static polar.Util.*;
import java.awt.Color;

public class Line {
	private Color c;
	private Point s;
	private Point e;
	
	public Line(Point s, Point e) {
		this.s = new Point(s.getX(),s.getY());
		this.e = new Point(e.getX(),e.getY());
		this.c = Color.WHITE;
	}
	public Line(Point s, Point e, Color c) {
		this.s = new Point(s.getX(),s.getY());
		this.e = new Point(e.getX(),e.getY());
		this.c=c;
	}
	public Line(Point s, double r, double range) {
		this.s=s;
		this.e = new Point(s.getX()+range*cos(r),s.getY()+range*sin(r));
	}
	public Point getS() {return s;}
	public Point getE() {return e;}
	
	public void translate(Vector v) {
		s.translate(v);
		e.translate(v);
	}
	public void rotate(double theta, Point p) {
		s.rotate(theta,p);
		e.rotate(theta,p);
	}
	public boolean isIntersecting(Line k) {
		return Line.getIntersect(this,k)!=null;
	}
	public static Point getIntersect(Line a, Line b){
		double a_s = a.slope();
		double b_s = b.slope();
		if(equal(a_s,b_s))
			return null;
		double a_c=-a_s * a.getS().getX() + a.getS().getY();
		double b_c=-b_s * b.getS().getX() + b.getS().getY();
		if(a_s==Double.POSITIVE_INFINITY || a_s==Double.NEGATIVE_INFINITY) {
			//System.out.println("A IS VERITCAL");
			double x = a.getS().getX();
			double y = b_s * x + b_c;
			if((y<=a.getS().getY()^y<=a.getE().getY())&&
			   (x<=b.getS().getX()^x<=b.getE().getX())&&
			   (y<=b.getS().getY()^y<=b.getE().getY()))
				return new Point(x,y);
		}
		if(b_s==Double.POSITIVE_INFINITY || b_s==Double.NEGATIVE_INFINITY) {
			//System.out.println("B IS VERITCAL");
			double x = b.getS().getX();
			double y = a_s * x + a_c;
			if((y<=b.getS().getY()^y<=b.getE().getY())&&
			   (x<=a.getS().getX()^x<=a.getE().getX())&&
			   (y<=a.getS().getY()^y<=a.getE().getY()))
				return new Point(x,y);
		}
		double	x = (a_c-b_c)/(b_s-a_s);
		double	y = a_s*x+a_c;
		if(((x<=a.getS().getX()) ^ (x<=a.getE().getX()))&&
		   ((y<=a.getS().getY()) ^ (y<=a.getE().getY()))&&
		   ((x<=b.getS().getX()) ^ (x<=b.getE().getX()))&&
		   ((y<=b.getS().getY()) ^ (y<=b.getE().getY()))) {
			//System.out.println("intersecting");
			return new Point(x,y);
		}
		//System.out.println("not intersecting");
		return null;
	}
	
	public Color getColor() {return c;}
	public void setColor(Color c) {this.c=c;}
	
	public void draw() {
		glBegin(GL_LINES);
		glColor3f(((float)c.getRed())/255,((float)c.getGreen())/255,((float)c.getBlue())/255);
		glVertex2i((int)round(s.getX()),(int)round(s.getY()));
		glVertex2i((int)round(e.getX()),(int)round(e.getY()));
		glColor3f(1.0f,1.0f,1.0f);
		glEnd();
	}
	
	public double slope() {
		return (e.getY()-s.getY())/(e.getX()-s.getX());
	}
	public double dist() {
		return Math.sqrt(Math.pow(e.getX()-s.getX(),2)+Math.pow(e.getY()-s.getY(),2));
	}
	
	@Override
	public String toString() {
		return "["+s+","+e+"]";
	}
}
