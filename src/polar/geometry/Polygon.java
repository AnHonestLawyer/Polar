package polar.geometry;

import java.util.List;
import static org.lwjgl.opengl.GL11.*;
import static polar.Util.*;
import java.util.ArrayList;
import java.awt.Color;

public class Polygon {
	//PRIVATE FIELDS
	private List<Point> points;
	private Color c;
	
	//CONSTRUCTORS
	public Polygon(List<Point> points) {
		this.points = new ArrayList<Point>();
		for(Point p : points) {
			this.points.add(new Point(p));
		}
		this.c = Color.WHITE;
	}
	public Polygon(List<Point> points,Color c) {
		this.points = new ArrayList<Point>();
		for(Point p : points) {
			this.points.add(new Point(p));
		}
		this.c=c;
	}
	public Polygon(Polygon poly) {
		this.points = new ArrayList<Point>();
		for(Point p : poly.getPoints()) {
			this.points.add(new Point(p));
		}
		this.c = Color.WHITE;
	}
	public Polygon(Polygon poly,Color c) {
		this.points = new ArrayList<Point>();
		for(Point p : poly.getPoints()) {
			this.points.add(new Point(p));
		}
		this.c=c;
	}
	public Polygon(Point p, int n, double r) {
		this(p,n,r,Color.WHITE);
	}
	public Polygon(Point p, int n, double r,Color c) {
		double theta = 2*PI/n;
		double offset = n%2==0? theta/2 : 0;
		points = new ArrayList<Point>();
		for(int i=0;i<n;i++) {
			points.add(new Point(p.getX()+r*cos(theta*i+offset),
					             p.getY()+r*sin(theta*i+offset)));
		}
		this.c=c;
	}
	
	//GETTERS/SETTERS
	public List<Line> getLines(){
		List<Line> lines = new ArrayList<Line>();
		for(int i=0;i<points.size()-1;i++) {
			lines.add(new Line(points.get(i),points.get(i+1)));
		}
		lines.add(new Line(points.get(points.size()-1),points.get(0)));
		return lines;
	}
	public List<Point> getPoints(){
		return points;
	}
	public Color getColor() {return c;}
	public void setColor(Color c) {this.c=c;}
	
	//TRANSLATION METHODS
	public void translate(Vector v) {
		for(int i=0;i<points.size();i++) {
			points.get(i).translate(v);
		}
	}
	public void rotate(double theta, Point p) {
		for(int i=0;i<points.size();i++) {
			points.get(i).rotate(theta,p);
		}
	}
	
	//COLLISION METHODS
	public boolean isIntersecting(Polygon p) {
		for(Line l : getLines()) {
			//System.out.print("\nChecking for "+l+": ");
			for(Line k : p.getLines()) {
				//System.out.print(k+" is ");
				if(l.isIntersecting(k)) {
					//System.out.println("Y");
					return true;
				}
				else {
					//System.out.print("N ");
				}
			}
		}
		//System.out.println("no intersection");
		return false;
	}
	public List<Point> getIntersections(Polygon p){
		List<Point> intersects = new ArrayList<Point>();
		for(Line l : getLines()) {
			for(Line k : p.getLines()) {
				Point i = Line.getIntersect(l,k);
				if(i!=null) {intersects.add(i);}
			}
		}
		return intersects;
	}
	
	public void draw() {
		glBegin(GL_LINE_LOOP);
		glColor3f(((float)c.getRed())/255,((float)c.getGreen())/255,((float)c.getBlue())/255);
		for(Point p : points) {
			glVertex2d(p.getX(),p.getY());
		}
		glColor3f(1.0f,1.0f,1.0f);
		glEnd();
	}
}
