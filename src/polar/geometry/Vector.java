package polar.geometry;

import static org.lwjgl.opengl.GL11.*;
import java.awt.Color;
import static polar.Util.*;

public class Vector {
	//private fields
	private double x;
	private double y;
	private Color c;
	
	//constructors
	public Vector() {
		this(0,0,Color.WHITE);
	}
	public Vector(double x, double y) {
		this(x,y,Color.WHITE);
	}
	public Vector(Vector v) {
		this.x = v.getX();
		this.y = v.getY();
		this.c = v.getC();
	}
	public Vector(double x, double y, Color c) {
		this.x = x;
		this.y = y;
	}
	
	//operations
	public void add(Vector v) {
		this.x += v.getX();
		this.y += v.getY();
	}
	public void sub(Vector v) {
		add(v.negator());
	}
	public Vector negator() {
		return new Vector(-this.x,-this.y);
	}
	public void mult(double d) {
		x*=d;
		y*=d;
	}
	public void div(double d) {
		x/=d;
		y/=d;
	}
	//getters
	public double getX() {return x;}
	public double getY() {return y;}
	public Color getC() {return c;}
	public void set(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	@Override
	public String toString() {
		return "<"+x+","+y+">";
	}
	
	public void draw(Point p) {
		double linelen = 20; //would normally be equal to the vector's length or something
		double b = 5*sin(rad(22.5));
		double d = linelen-5*cos(rad(22.5));
		double t = atan(b,d);
		double vt = atan(getY(),getX());
		double len = sqrt(pow(b,2)+pow(d,2));
		Point end = new Point(p.getX()+linelen*cos(vt),p.getY()+linelen*sin(vt));
		glBegin(GL_LINES);
			glColor3f(0.0f,1.0f,0.0f);
			glVertex2d(p.getX(),p.getY());
			glVertex2d(end.getX(),end.getY());
			glVertex2d(p.getX()+len*cos(t+vt),p.getY()+len*sin(t+vt));
			glVertex2d(end.getX(),end.getY());
			glVertex2d(p.getX()+len*cos(-t+vt),p.getY()+len*sin(-t+vt));
			glVertex2d(end.getX(),end.getY());
		glEnd();
	}
	public void draw(Point p,Color c) {
		double linelen = Math.min(40,vMag(this)); //would normally be equal to the vector's length or something
		double b = linelen/4*sin(rad(22.5));
		double d = linelen-linelen/4*cos(rad(22.5));
		double t = atan(b,d);
		double vt = atan(getY(),getX());
		double len = sqrt(pow(b,2)+pow(d,2));
		Point end = new Point(p.getX()+linelen*cos(vt),p.getY()+linelen*sin(vt));
		glBegin(GL_LINES);
			glColor3f(((float)c.getRed())/255,((float)c.getGreen())/255,((float)c.getBlue())/255);
			glVertex2d(p.getX(),p.getY());
			glVertex2d(end.getX(),end.getY());
			glVertex2d(p.getX()+len*cos(t+vt),p.getY()+len*sin(t+vt));
			glVertex2d(end.getX(),end.getY());
			glVertex2d(p.getX()+len*cos(-t+vt),p.getY()+len*sin(-t+vt));
			glVertex2d(end.getX(),end.getY());
		glEnd();
	}
	public void draw(Point p,double scale,Color c) {
		double linelen = Math.min(30,vMag(this)*scale); //would normally be equal to the vector's length or something
		double b = linelen/4*sin(rad(22.5));
		double d = linelen-linelen/4*cos(rad(22.5));
		double t = atan(b,d);
		double vt = atan(getY(),getX());
		double len = sqrt(pow(b,2)+pow(d,2));
		Point end = new Point(p.getX()+linelen*cos(vt),p.getY()+linelen*sin(vt));
		glBegin(GL_LINES);
			glColor3f(((float)c.getRed())/255,((float)c.getGreen())/255,((float)c.getBlue())/255);
			glVertex2d(p.getX(),p.getY());
			glVertex2d(end.getX(),end.getY());
			glVertex2d(p.getX()+len*cos(t+vt),p.getY()+len*sin(t+vt));
			glVertex2d(end.getX(),end.getY());
			glVertex2d(p.getX()+len*cos(-t+vt),p.getY()+len*sin(-t+vt));
			glVertex2d(end.getX(),end.getY());
		glEnd();
	}
}
