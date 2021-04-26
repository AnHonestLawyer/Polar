package actors;

import polar.*;
import static polar.Util.*;
import java.awt.Color;
import polar.geometry.*;


public class Ball extends Actor{
	
	private Vector vel;
	private double radius;
	
	public Ball(double r, double speed) {
		this.radius=r;
		//pick a random angle
		double a = rand()*2*PI;
		//set velocity to speed at angle
		vel = new Vector(speed*cos(a),speed*sin(a));
	}
	
	@Override
	public void act() {
		//if the ball is on edge of screen, bounce
		if(getX()<radius || getX()>getScene().getWidth()-radius)
			vel.set(-vel.getX(),vel.getY());
		if(getY()<radius || getY()>getScene().getHeight()-radius)
			vel.set(vel.getX(),-vel.getY());
		translate(vel);
	}
	
	@Override
	public Polygon createHB(Point p) {
		Polygon poly = new Polygon(p,12,radius);
		poly.setColor(Color.RED);
		return poly;
	}
}
