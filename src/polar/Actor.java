package polar;

import java.awt.Color;
import static polar.Util.*;
import polar.geometry.*;
import polar.input.*;

/**
 * <p>
 * This is the Actor class, the main class to represent the objects on the screen.
 * <p>
 * How Actors work:
 * <br>  -  All actors have a Polygon <b>hitbox</b>. This represents the area designated for collisions with this actor
 * <br>  -  All actors have a Polygon <b>lightbox</b>. This represents the area designated for blocking light rays, handled by the Shadow engine [NOT IMPLEMENTED YET]
 * <br>  -  All actors have a Point <b>pos</b>. This represents their position in the world (Usually the middle of the actor)
 * <br>  -  All actors have a Point <b>raxis</b>. This represents the actors default rotational axis, where the actor will revolve around when rotate() is called
 * <br>  -  All actors have a double <b>r</b>. This represents the rotation of the actors used when methods like draw() or move() are called. Rotation starts facing right at 0.0 and increases counterclockwise
 * <br>  -  All actors have a Texture <b>t</b>. This is the image that will be drawn by default, centered on <b>pos</b> and rotated by <b>r</b>
 * <p>
 * When moving actors (move(), setLocation(), etc.), all parts of the actor (hitbox, lightbox, pos, raxis) will move together, effectively locked together.
 * <p>
 * Currently, it is planned for the user to override the draw() and act() methods in order for custom methods to be called.
 * <p> 
 * ALL ANGLES ARE HANDLED IN RADIANS
 * @author BChan
 *
 */
public abstract class Actor {
	//FIELDS
	private Polygon hitbox;
	private Polygon lightbox;
	private Point pos;
	private Point raxis;
	private double r;
	private Scene scene;
	
	//CONSTRUCTORS
	/**
	 * Creates an actor instance where the hitbox and lightbox are copies of poly, and raxis and pos are copies of p
	 * @param poly The polygon to set as the hitbox and lightbox
	 * @param p The point to set as the raxis and pos
	 */
	
	/*public Actor(Polygon poly, Point p) {
		this(poly,poly,p,p);
	}*/
	
	/**
	 * Creates an actor instance where hitbox and lightbox are copies of poly, pos is a copy of p, and raxis is a copy of r
	 * @param poly The polygon to set as the hitbox and lightbox
	 * @param p The point to set as the pos
	 * @param r The point to set as the raxis
	 */
	/*public Actor(Polygon poly, Point p, Point r) {
		this(poly,poly,p,r);
	}*/
	/**
	 * Creates an actor instance where hitbox is a copy of hit, lightbox is a copy of light, and pos and raxis are copies of p
	 * @param hit The polygon to set as the hitbox
	 * @param light The polygon to set as the lightbox
	 * @param p The point to set as the pos
	 */
	/*public Actor(Polygon hit, Polygon light, Point p) {
		this(hit,light,p,p);
	}*/
	/**
	 * Creates an actor instance where hitbox is a copy of hit, lightbox is a copy of light, pos is a copy of p, and raxis is a copy of r
	 * @param hit The polygon to set as the hitbox
	 * @param light The polygon to set as the lightbox
	 * @param p The point to set as the pos
	 * @param r The point to set as the raxis
	 */
	/*public Actor(Polygon hit, Polygon light, Point p, Point r) {
		pos = new Point(p);
		raxis = new Point(r);
		hitbox = new Polygon(hit);
		lightbox = new Polygon(light);
		hitbox.setColor(Color.BLUE);
		lightbox.setColor(Color.YELLOW);
		pos.setColor(Color.CYAN);
		raxis.setColor(Color.GREEN);
	}*/
	
	
	//MOVEMENT METHODS
	/**
	 * Moves the actor forward (in direction of rotation), <i>d</i> distance
	 * @param d The distance to move the actor
	 */
	public void move(double d) {
		double x = d * cos(r);
		double y = d * sin(r);
		translate(new Vector(x,y));
	}
	/**
	 * Moves the actor in a direction offset of the actor's rotation
	 * <p>
	 * Ex: move(6.0, PI/2); will move the actor 6 pixels to its left
	 * @param d The distance to move the actor
	 * @param off The angle (rad) offset from the actor's rotation to move in
	 */
	public void move(double d, double off) {
		double x = d * cos(r+off);
		double y = d * sin(r+off);
		translate(new Vector(x,y));
	}
	/**
	 * Rotates the actor around its <b>raxis</b>
	 * @param theta The angle (rad) to rotate
	 */
	public void rotate(double theta) {
		r+=theta;
		hitbox.rotate(theta,raxis);
		lightbox.rotate(theta,raxis);
		pos.rotate(theta,raxis);
	}
	/**
	 * Rotates the actor around <i>p</i>
	 * @param theta The angle to rotate
	 * @param p The point to rotate around
	 */
	public void rotate(double theta, Point p) {
		hitbox.rotate(theta,p);
		lightbox.rotate(theta,p);
		pos.rotate(theta,p);
		raxis.rotate(theta,p);
	}
	public void turnTo(Point p) {
		double theta = atan(p.getX()-pos.getX(),pos.getY()-p.getY());
		rotate(-r);
		rotate(theta);
		r=theta;
	}
	/**
	 * Sets the location of the actor
	 * <br> Basically shifts <b>pos</b> to <i>p</i>, then shifts the rest of the actor by the same amount
	 * @param p The point to move to
	 */
	public void setLocation(Point p) {
		Vector v = new Vector(p.getX()-pos.getX(),p.getY()-pos.getY());
		translate(v);
	}
	/**
	 * Translates the actor by a Vector
	 * @param v The vector to move the actor
	 */
	public void translate(Vector v) {
		hitbox.translate(v);
		lightbox.translate(v);
		pos.translate(v);
		raxis.translate(v);
	}
	
	//DRAWING METHODS
	/**
	 * Draws the <b>hitbox</b>
	 */
	void drawHB() {
		hitbox.draw();
	}
	/**
	 * Draws the  <b>lightbox</b>
	 */
	void drawLB() {
		lightbox.draw();
	}
	/**
	 * Draws the <b>pos</b>
	 */
	void drawPos() {
		pos.draw();
	}
	/**
	 * Draws the <b>raxis</b>
	 */
	void drawRAxis() {
		raxis.draw();
	}
	/**
	 * Intended to be overridden by the Actor, this method will be called every frame to draw the actor to the screen
	 * <p>
	 * By default, this method will draw this actor's
	 *  <br>1. <b>t</b> (the image set as this actor's texture)
	 *  <br>2. <b>lightbox</b> (in yellow, on bottom)
	 *  <br>3. <b>hitbox</b> (in blue)
	 *  <br>4. <b>raxis</b> (in green)
	 *  <br>5. <b>pos</b> (in cyan, on top)
	 *  <br>in order. Items at the end of the list will be drawn on top of items at the top.
	 *  </p>
	 */
	void draw() {
		//need to fix this garbage to be an if statement
		drawHB();
	}
	void draw(double offX, double offY) {
		translate(new Vector(offX,offY));
		draw();
		translate(new Vector(-offX,-offY));
	}
	void drawInfo() {
		drawLB();
		drawHB();
		drawRAxis();
		drawPos();
	}
	
	//INTERSECTION
	public boolean isTouching(Class<?> c) {
		return getScene().isTouching(this,c);
	}
	public Actor getTouching(Class<?> c) {
		return getScene().getTouching(this,c);
	}
	public boolean isOffScreen() {
		return getScene().isOffScreen(getPos());
	}
	
	//ACTOR CREATION (HAPPENS WHEN ADDED TO WORLD)
	protected void create(Point p, Scene scene) {
		setPos(p);
		setRAxis(p);
		setHB(createHB(p));
		setLB(createLB(p));
		hitbox.setColor(Color.BLUE);
		lightbox.setColor(Color.YELLOW);
		pos.setColor(Color.CYAN);
		raxis.setColor(Color.GREEN);
		this.scene=scene;
	}
	protected Polygon createHB(Point p) {
		return new Polygon(p,4,10);
	}
	protected Polygon createLB(Point p) {
		return createHB(p);
	}
	
	//GETTERS FOR NON-ACTOR THINGS:
	protected boolean isKeyDown(KeyCode key) {
		return Polar.im.getKeyInfo().isKeyDown(key);
	}
	protected boolean isKeyPressed(KeyCode key) {
		return Polar.im.getKeyInfo().isKeyPressed(key);
	}
	protected boolean isKeyReleased(KeyCode key) {
		return Polar.im.getKeyInfo().isKeyPressed(key);
	}
	
	protected Scene getScene() {
		return scene;
	}
	
	//ABSTRACT/UNFILLED METHODS
	public abstract void act();
	
	//GETTERS/SETTERS
	public Polygon getHB() {
		return hitbox;
		}
	public Polygon getLB() {
		return lightbox;
	}
	public Point getPos() {
		return pos;
	}
	public Point getRAxis() {
		return raxis;
	}
	public double getR() {
		return r;
	}
	public double getX() {
		return pos.getX();
	}
	public double getY() {
		return pos.getY();
	}
	
	public void setHB(Polygon p) {
		hitbox=new Polygon(p);
		}
	public void setLB(Polygon p) {
		lightbox=new Polygon(p);
	}
	public void setPos(Point p) {
		pos=new Point(p);
		}
	public void setRAxis(Point p) {
		raxis=new Point(p);
		}
	public void setR(double r) {
		this.r=r;
		}
}
