package polar.geometry;

import java.awt.Color;
import static org.lwjgl.opengl.GL11.*;
import static polar.Util.*;

public class Point {
	private double x;
	private double y;
	private Color c;
	
	//constructors
	public Point() {
		this(0,0);
	}
	public Point(double x, double y) {
		this(x,y,Color.WHITE);
	}
	public Point(double x, double y, Color c) {
		this.x=x;
		this.y=y;
		this.c=c;
	}
	public Point(Point p) {
		this(p,Color.WHITE);
	}
	public Point(Point p, Color c) {
		x=p.getX();
		y=p.getY();
		this.c=c;
	}
	
	//translation methods
	public void translate(Vector v) {
		this.x += v.getX();
		this.y += v.getY();
	}
	
	public void rotate(double theta, Point p) {
		//calculate trig for later
		double s = sin(theta);
		double c = cos(theta);
		//translate to origin
		this.x-=p.getX();
		this.y-=p.getY();
		//rotate method
		double xnew = this.x*c - this.y*s;
		double ynew = this.x*s + this.y*c;
		//re-translate away from origin
		this.x = xnew + p.getX();
		this.y = ynew + p.getY();
	}
	
	
	//getters
	public double getX() {return x;}
	public double getY() {return y;}
	public String toString() {
		return "("+x+","+y+")";
	}
	public void setColor(Color c) {
		this.c=c;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o==this) {return true;}
		if(o instanceof Point) {
			Point p = (Point) o;
			return equal(p.getX(),x)&&equal(p.getY(),y);
		}
		return false;
	}
	
	public void draw(Color c) {
		glBegin(GL_LINE_LOOP);
		glColor3f(((float)c.getRed())/255,((float)c.getGreen())/255,((float)c.getBlue())/255);
	    for(int i = 0; i < 5; i++){
	        double theta = 2 * PI * (float)i / 5;//get the current angle
	        float x = (float)(3 * cos(theta));//calculate the x component
	        float y = (float)(3 * sin(theta));//calculate the y component
	        glVertex2f((float)(x + this.x), (float)(y + this.y));//output vertex
	    }
	    glColor3f(1.0f,1.0f,1.0f);
	    glEnd();
	}
	public void draw() {
		draw(this.c);
	}
}
