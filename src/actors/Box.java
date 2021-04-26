package actors;

import polar.Actor;
import static polar.Util.*;
import polar.geometry.*;
import polar.input.KeyCode;

//yes, you play as Braden in this game
public class Box extends Actor{

	//for the momentum and rocketMove methods
	private Vector vel;
	private double acc = .1;
	//for the flatMove and turnMove methods
	private double speed = 6;
	
	public Box() {
		vel = new Vector();
	}
	
	@Override
	public void act() {
		flatMove();
		if(isTouching(Ball.class)) {
			getScene().removeActor(this);
		}
		if(isOffScreen()) {
			getScene().removeActor(this);
		}
	}
	
	@Override
	public Polygon createHB(Point p) {
		return new Polygon(p,4,15);
	}
	
	//Example movement method types:
	public void momentum() {
		if(isKeyDown(KeyCode.KEY_W)) {
			vel = new Vector(vel.getX(),vel.getY()-acc);
		}
		if(isKeyDown(KeyCode.KEY_S)) {
			vel = new Vector(vel.getX(),vel.getY()+acc);
		}
		if(isKeyDown(KeyCode.KEY_A)) {
			vel = new Vector(vel.getX()-acc,vel.getY());
		}
		if(isKeyDown(KeyCode.KEY_D)) {
			vel = new Vector(vel.getX()+acc,vel.getY());
		}
		//cap the speed to +-6
		vel.set(min(6,max(-6,vel.getX())),
				min(6,max(-6,vel.getY())));
		//move based on new velocity
		translate(vel);
	}
	
	public void rocketMove() {
		//if driving, add "thrust" to the current velocity
		if(isKeyDown(KeyCode.KEY_W)) {
			Vector push = new Vector(acc*cos(getR()),
									 acc*sin(getR()));
			vel = vAdd(vel,push);
		}
		if(isKeyDown(KeyCode.KEY_A)) {
			rotate(-rad(3));
		}
		if(isKeyDown(KeyCode.KEY_D)) {
			rotate(rad(3));
		}
		//cap the speed to +-6
		vel.set(min(6,max(-6,vel.getX())),
				min(6,max(-6,vel.getY())));
		//move based on new velocity
		translate(vel);
	}
	
	public void turnMove() {
		if(isKeyDown(KeyCode.KEY_W)) {
			move(speed);
		}
		if(isKeyDown(KeyCode.KEY_S)) {
			move(-speed);
		}
		if(isKeyDown(KeyCode.KEY_A)) {
			rotate(-rad(2));
		}
		if(isKeyDown(KeyCode.KEY_D)) {
			rotate(rad(2));
		}
	}
	
	public void flatMove() {
		if(isKeyDown(KeyCode.KEY_W)) {
			translate(new Vector(0,-speed));
		}
		if(isKeyDown(KeyCode.KEY_A)) {
			translate(new Vector(-speed,0));
		}
		if(isKeyDown(KeyCode.KEY_S)) {
			translate(new Vector(0,speed));
		}
		if(isKeyDown(KeyCode.KEY_D)) {
			translate(new Vector(speed,0));
		}
	}
}
