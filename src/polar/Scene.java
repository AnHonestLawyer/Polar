package polar;

import polar.geometry.*;
import java.util.Set;
import java.util.HashSet;
import polar.input.KeyCode;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import actors.*;

public abstract class Scene {
	
	List<Actor> actors;
	Vector cameraOffset;
	Set<Actor> toRemove;
	List<AddActorEntry> toAdd;
	
	public Scene() {
		actors = new ArrayList<Actor>();
		cameraOffset= new Vector(0,0);
		toRemove = new HashSet<Actor>();
		toAdd = new ArrayList<AddActorEntry>();
	}
	
	//TODO: FIGURE OUT WHAT ORDER THIS SHIT NEEDS TO BE IN
	protected void step() {
		toRemove.clear();
		for(Actor a : actors) {
			a.act();
		}
		act();
							
		draw();
		for(Actor a : actors) {
			a.translate(cameraOffset);
			a.draw();
			if(Polar.showActorInfo) {
				a.drawInfo();
			}
			a.translate(cameraOffset.negator());
		}
		
		//remove actors that have been selected for removal
		actors = actors.stream()
				       .filter(a -> !toRemove.contains(a))
					   .collect(Collectors.toList());
		//check to see if there are actors to add this frame
		addActorsForFrame();
		//after checking for additional actors, decrement the frame timers by 1
		toAdd.stream().forEach(e -> e.frame=e.frame-1);
	}
	
	protected void setCameraOffset(Vector v) {
		cameraOffset = v;
	}
	boolean isOffScreen(Point p) {
		Point a = new Point(p.getX()+cameraOffset.getX(),p.getY()+cameraOffset.getY());
		return a.getX()<0 || a.getX()>Polar.width || a.getY()<0 || a.getY()>Polar.height;
	}
	
	boolean isTouching(Actor a, Class<?> c) {
		List<Actor> list = actors.stream().filter(b -> c.isInstance(b)).collect(Collectors.toList());
		for(Actor b : list) {
			if(!a.equals(b)&&b.getHB().isIntersecting(a.getHB())) {
				return true;
			}
		}
		return false;
	}
	
	Actor getTouching(Actor a, Class<?> c) {
		List<Actor> list = actors.stream().filter(b -> c.isInstance(b)).collect(Collectors.toList());
		for(Actor b : list) {
			if(!a.equals(b)&&a.getHB().isIntersecting(b.getHB())) {
				return b;
			}
		}
		return null;
	}
	
	public List<Actor> getActors(){return actors;}
	
	void addActorsForFrame() {
		if(!toAdd.isEmpty()) {
			AddActorEntry e = toAdd.get(0);
			while(e.frame==0) {
				e.a.create(e.p,this);
				actors.add(e.a);
				toAdd.remove(0);
				if(!toAdd.isEmpty())
					e=toAdd.get(0);
				else
					return;
			}
		}
	}
	
	public void setScene(Scene s) {
		Polar.sm.setScene(s);
	}
	
	public void addActor(Actor a, Point p) {
		addActor(a,p,0);
	}
	/**
	 * Will add an actor into the world after a specified number of frames
	 * @param a Actor to add
	 * @param p Location to place actor
	 * @param frame how many frames to wait before adding actor
	 */
	public void addActor(Actor a, Point p, int frame) {
		int i=0;
		while(i<toAdd.size()&&toAdd.get(i).frame<=frame) {
			i++;
		}
		toAdd.add(i,new AddActorEntry(a,p,frame));
	}
	private class AddActorEntry{
		public Actor a;
		public Point p;
		public int frame;
		public AddActorEntry(Actor a, Point p, int frame) {
			this.a=a;
			this.p=p;
			this.frame=frame;
		}
	}
	/**
	 * This will tell the scene to queue an actor remove. 
	 * NOTE: The actor will only be removed after all other actors have acted
	 * @param a The actor to remove
	 */
	public void removeActor(Actor a) {
		toRemove.add(a);
	}
	
	protected boolean isKeyDown(KeyCode key) {
		return Polar.im.getKeyInfo().isKeyDown(key);
	}
	protected boolean isKeyPressed(KeyCode key) {
		return Polar.im.getKeyInfo().isKeyPressed(key);
	}
	protected boolean isKeyReleased(KeyCode key) {
		return Polar.im.getKeyInfo().isKeyPressed(key);
	}
	
	void draw() {
		//if you want to draw a background, i guess. This will probably 
		//just be a static image, but hey draw whatever the hell you want
	}
	
	public int getHeight() {return Polar.height;}
	public int getWidth() {return Polar.width;}
	
	abstract public void open();
	abstract public void act();
	abstract public void close();
	
}
